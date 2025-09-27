package com.example.redstackapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.redstackapp.R
import com.example.redstackapp.model.KeyWord
import com.google.android.material.chip.Chip

class KeyWordAdapter(val wordlist: List<KeyWord>) :
    RecyclerView.Adapter<KeyWordAdapter.myViewHolder>() {

    inner  class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Title: Chip=itemView.findViewById(R.id.chip_word)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.row_key_word,
            parent, false
        )
        return myViewHolder(
            itemView
        )
    }

    override fun getItemCount() = wordlist.size

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = wordlist[position]
        holder.Title.text = currentItem.word
    }

}