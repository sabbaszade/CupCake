package com.example.redstackapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShortArticle(
    val userImage: Int,
    val postTitle: String,
    val postDesc: String,
    val postWriter: String,
    val postDate: String,
    var isBookmark: Boolean,
    var isLiked: Boolean,
    val likeNumber: String,
    val commentNumber: String
) : Parcelable