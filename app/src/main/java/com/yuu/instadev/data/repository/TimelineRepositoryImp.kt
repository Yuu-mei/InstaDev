package com.yuu.instadev.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yuu.instadev.data.response.MediaType
import com.yuu.instadev.data.response.ReelResponse
import com.yuu.instadev.data.response.toDomain
import com.yuu.instadev.domain.entity.ReelsFirebaseEntity
import com.yuu.instadev.domain.repository.TimelineRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch


class TimelineRepositoryImp @Inject constructor(
    val fireStore: FirebaseFirestore,
    val firebaseAuth: FirebaseAuth
) : TimelineRepository {

    override suspend fun doRetrieveReels(): List<ReelsFirebaseEntity> {
        return try {
            val snapshot = fireStore.collection("posts")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .limit(20)
                .get()
                .await()

            snapshot.documents.mapNotNull { reel ->
                val timestamp = reel.getTimestamp("createdAt")
                val userID = reel.getString("userID")!!

                val userDoc = fireStore
                    .collection("users")
                    .document(userID)
                    .get()
                    .await()

                ReelResponse(
                    postID = reel.id,
                    userID = userDoc.data!!["username"] as String,
                    mediaURL = reel.getString("mediaURL")!!,
                    mediaType = if (reel.getString("mediaType") == "image") MediaType.IMAGE else MediaType.VIDEO,
                    text = reel.getString("text")!!,
                    commentCount = reel.getLong("commentCount")?.toInt()!!,
                    likeCount = reel.getLong("likeCount")?.toInt()!!,
                    createdAt = timestamp!!.toDate().time
                ).toDomain()
            }
        } catch (e: Exception) {
            Log.e("REEL", e.message ?: "Error 500")
            emptyList()
        }
    }

    override suspend fun doLikeReel(reelID: String): Boolean {
        try {
            val currentUserID =
                firebaseAuth.currentUser?.uid ?: throw Exception("Error retrieving user")

            val alreadyLikedDoc = fireStore
                .collection("likes")
                .whereEqualTo("postID", reelID)
                .whereEqualTo("userID", currentUserID)
                .get()
                .await()
                .firstOrNull()

            val reelDoc = fireStore
                .collection("posts")
                .document(reelID)
                .get()
                .await()

            if (alreadyLikedDoc != null) {
                alreadyLikedDoc.reference.delete().await()
                val newLikeCount = (reelDoc.getLong("likeCount")?.minus(1L)) ?: 0L
                reelDoc.reference.update("likeCount", newLikeCount).await()

                return false
            } else {
                val newLikeCount = (reelDoc.getLong("likeCount")?.plus(1L)) ?: 0L
                reelDoc.reference.update("likeCount", newLikeCount).await()

                val likeData = hashMapOf(
                    "postID" to reelID,
                    "userID" to currentUserID
                )

                fireStore
                    .collection("likes")
                    .add(likeData)
                    .await()

                return true
            }
        } catch (e: Exception) {
            Log.e("REEL", e.message ?: "Error retrieving the current user and likes")
            return false
        }
    }

    override suspend fun doGetlikedReels(): Map<String, Boolean> {
        val likedReels: MutableMap<String, Boolean> = mutableMapOf()

        try {
            val currentUserID =
                firebaseAuth.currentUser?.uid ?: throw Exception("Error retrieving user")

            val likedReelsSnapshot = fireStore
                .collection("likes")
                .whereEqualTo("userID", currentUserID)
                .get()
                .await()

            likedReelsSnapshot.documents.forEach { doc ->
                likedReels[doc.id] = true
            }
        } catch (e: Exception) {
            Log.e("REEL", e.message ?: "Error retrieving the liked reels")
        }

        return likedReels
    }

    override suspend fun doObserveReels(): Flow<List<ReelsFirebaseEntity>> = callbackFlow{
        val listener = fireStore
            .collection("posts")
            .addSnapshotListener { snapshots, exception ->
                if(exception != null){
                    close(exception)
                    return@addSnapshotListener
                }

                launch {
                    val reels = snapshots?.documents?.map { reelDoc ->
                        val userID = reelDoc.getString("userID")!!
                        val timestamp = reelDoc.getTimestamp("createdAt")

                        val userDoc = fireStore
                            .collection("users")
                            .document(userID)
                            .get()
                            .await()

                        ReelResponse(
                            postID = reelDoc.id,
                            userID = userDoc.data!!["username"] as String,
                            mediaURL = reelDoc.getString("mediaURL")!!,
                            mediaType = if (reelDoc.getString("mediaType") == "image") MediaType.IMAGE else MediaType.VIDEO,
                            text = reelDoc.getString("text")!!,
                            commentCount = reelDoc.getLong("commentCount")?.toInt()!!,
                            likeCount = reelDoc.getLong("likeCount")?.toInt()!!,
                            createdAt = timestamp!!.toDate().time
                        ).toDomain()
                    }
                    trySendBlocking(reels!!)
                }

            }

        awaitClose{ listener.remove() }
    }

}