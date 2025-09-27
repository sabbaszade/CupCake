package com.example.redstackapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redstackapp.R
import com.example.redstackapp.`interface`.HomeFrgClickListener
import com.example.redstackapp.model.Article
import kotlinx.android.synthetic.main.layout_rv_home.view.*

class HomeAdapter(val articleList: List<Article>, var clicklistener: HomeFrgClickListener? = null) :
    RecyclerView.Adapter<HomeAdapter.myViewHolder>() {

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleTitle: TextView = itemView.title_related
        val writer: TextView = itemView.writer_related
        val date: TextView = itemView.date_related
        val pic: ImageView = itemView.profile_related
        val bookmark: ImageView = itemView.share_image

        init {
            itemView.share_image.setOnClickListener {
                clicklistener?.OnBookMarkClick(layoutPosition,articleList[layoutPosition])
            }
            itemView.setOnClickListener {
                clicklistener?.OnRowClick(layoutPosition,articleList[layoutPosition])
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_rv_home,
            parent, false
        )
        return myViewHolder(
            itemView
        )
    }

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = articleList[position]
        holder.articleTitle.text = currentItem.title
        holder.writer.text = currentItem.writer
        holder.date.text = currentItem.date
        holder.pic.setImageResource(currentItem.profilePic)
        if (!currentItem.IsBookmarked)
            holder.bookmark.setImageResource(R.drawable.ic_bookmark_2)
        else
            holder.bookmark.setImageResource(R.drawable.ic_bookmark_fill)

    }

}