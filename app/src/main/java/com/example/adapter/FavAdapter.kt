package com.example.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.room.Enitity
import com.example.ui.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_rv.view.*


class FavAdapter(var context: Context) : RecyclerView.Adapter<FavAdapter.FavViewHolder>() {

    private var profiles: List<Enitity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.layout_rv,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = profiles?.size ?: 0

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        profiles?.let {
            holder.itemView.tv_name.text = it[position].results?.user?.name?.first
            holder.itemView.tv_email.text = it[position].results?.user?.email
            holder.itemView.tv_no.text = it[position].results?.user?.phone
            Glide.with(context).load(it[position].results?.user?.picture).into(holder.itemView.iv_image)


            /*Picasso.get()
                .load(it[0].results?.user?.picture)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.itemView.iv_image)*/

        }
    }


    fun setProfiles(profiles: List<Enitity>) {
        this.profiles = profiles
        notifyDataSetChanged()
    }


    inner class FavViewHolder(view: View) :
        RecyclerView.ViewHolder(view)


}