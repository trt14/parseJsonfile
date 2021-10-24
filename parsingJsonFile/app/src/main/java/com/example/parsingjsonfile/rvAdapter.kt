package com.example.parsingjsonfile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.img.view.*

class rvAdapter(private val imagesList: ArrayList<jsonfile>, val context: Context?) :
    RecyclerView.Adapter<rvAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.imgv

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.img,
            parent,
            false
        )
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val imgUrl = imagesList[position].url!!

        Glide.with(context!!).load(imgUrl).into(holder.imageView)


    }


    override fun getItemCount() = imagesList.size
}