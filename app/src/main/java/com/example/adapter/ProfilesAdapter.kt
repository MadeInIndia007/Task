package com.example.adapter

import android.annotation.SuppressLint
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Model
import com.example.ui.R
import com.example.ui.databinding.RecyclerViewProfileBinding

import java.util.*


class ProfilesAdapter( var mn: getData) : RecyclerView.Adapter<ProfilesAdapter.ProfileViewHolder>() {

    private var profiles: List<Model.Result>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_profile,
            parent,
            false
        )
    )

    override fun getItemCount() = profiles?.size ?: 0

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        profiles?.let {
            holder.binding.profile = it[position]
            holder.binding.executePendingBindings()
            val pos = profiles!![position]

            holder.binding.tv1.text = "My name is"
            holder.binding.tv2.text = pos.user.name.first

            holder.binding.ivName.setOnClickListener {
                holder.binding.tv1.text = "My name is"
                holder.binding.tv2.text = pos.user.name.first
            }
            holder.binding.ivContact.setOnClickListener {
                holder.binding.tv1.text = "My DOB is"
                holder.binding.tv2.text = getDate(pos.user.dob.toLong())
            }
            holder.binding.ivLoc.setOnClickListener {
                holder.binding.tv1.text = "My Address is"
                holder.binding.tv2.text = pos.user.location.city + pos.user.location.state
            }
            holder.binding.ivMob.setOnClickListener {
                holder.binding.tv1.text = "My Mobile No is"
                holder.binding.tv2.text = pos.user.phone
            }
            holder.binding.ivLock.setOnClickListener {
                holder.binding.tv1.text = "My SSN is"
                holder.binding.tv2.text = pos.user.SSN
            }

        }
    }

    fun getObj(position: Int) {
        profiles?.get(position)?.let { mn.abc(it) }
    }

    fun setProfiles(profiles: List<Model.Result>?) {
        this.profiles = profiles
        notifyDataSetChanged()
    }

    fun getDate(timestamp: Long): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp * 1000L
        val date = DateFormat.format("dd-MM-yyyy", calendar).toString()
        return date
    }

    inner class ProfileViewHolder(val binding: RecyclerViewProfileBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface getData{
        fun abc(result: Model.Result)
    }

}