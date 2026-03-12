package com.yuu.instadev.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yuu.instadev.data.response.MediaType
import com.yuu.instadev.data.response.ReelResponse
import com.yuu.instadev.data.response.toDomain
import com.yuu.instadev.domain.entity.ReelsFirebaseEntity
import com.yuu.instadev.domain.repository.TimelineRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TimelineRepositoryImp @Inject constructor(val fireStore: FirebaseFirestore): TimelineRepository {
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

                val userSnapshot = fireStore
                    .collection("users")
                    .document(userID)
                    .get()
                    .await()

                ReelResponse(
                    postID = reel.id,
                    userID = userSnapshot.data?.get("username") as String,
                    mediaURL = reel.getString("mediaURL")!!,
                    mediaType = if(reel.getString("mediaType") == "image") MediaType.IMAGE else MediaType.VIDEO,
                    text = reel.getString("text")!!,
                    commentCount = reel.getLong("commentCount")?.toInt()!!,
                    likeCount = reel.getLong("likeCount")?.toInt()!!,
                    createdAt = timestamp!!.toDate().time
                ).toDomain()
            }
        } catch (e: Exception){
            Log.e("REEL", e.message ?: "Error 500")
            emptyList<ReelsFirebaseEntity>()
        }
    }

}