package com.example.networking_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.models.User
import com.squareup.picasso.Picasso
import kotlin.with as with

class UserAdapter(val data: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    lateinit var usernameTv: TextView
    lateinit var userImgView: ImageView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {

        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )

    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: User) = with(itemView) {
            usernameTv = findViewById(R.id.usernameTv)
            userImgView = findViewById(R.id.userImgView)
            usernameTv.text = item.login
            Picasso.get().load(item.avatarUrl).into(userImgView)
        }

    }
}