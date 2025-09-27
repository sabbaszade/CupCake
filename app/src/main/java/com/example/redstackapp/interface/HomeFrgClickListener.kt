package com.example.redstackapp.`interface`

import com.example.redstackapp.model.Article

interface HomeFrgClickListener {
    fun OnBookMarkClick(pos : Int,article :Article)
    fun OnRowClick(pos: Int, article: Article)
}