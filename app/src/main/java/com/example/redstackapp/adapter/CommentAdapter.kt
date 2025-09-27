package com.example.redstackapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redstackapp.R
import com.example.redstackapp.model.Comment
import kotlinx.android.synthetic.main.row_recycler_comments.view.*

class CommentAdapter(val commentList: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.myVHolder>() {

    class myVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val picture: ImageView = itemView.image_comment
        val name: TextView = itemView.name_comment
        val comment: TextView = itemView.text_comment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myVHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_recycler_comments, parent, false)

        return myVHolder(itemView)
    }

    override fun getItemCount() = commentList.size

    override fun onBindViewHolder(holder: myVHolder, position: Int) {

        val currentItem = commentList[position]
        holder.picture.setImageResource(currentItem.userPic)
        holder.name.text = currentItem.userName
        holder.comment.text = currentItem.UserComment
    }
}