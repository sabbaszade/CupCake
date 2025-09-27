package com.example.redstackapp.`interface`

import com.example.redstackapp.model.ShortArticle

interface ShortArticleClickHandler {

    fun onBookmarkClick(position: Int)
    fun onLikeClick(position: Int)
    fun onMoreClick(position: Int)
    fun OnRowClick(pos: Int, article: ShortArticle)
}