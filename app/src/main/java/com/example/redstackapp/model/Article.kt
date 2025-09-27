package com.example.redstackapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val profilePic: Int,
    var title: String,
    val writer: String,
    val date: String,
    var IsBookmarked: Boolean = false
) : Parcelable