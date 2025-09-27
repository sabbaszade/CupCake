package com.example.redstackapp.`interface`

import android.view.View
import com.example.redstackapp.model.Article

interface ClickListener {
    fun onClick(layoutPosition: Article)
}