package com.example.androidlesson3.cabinet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson3.R

class UserButtonAdapter : ListAdapter<UserButton, UserButtonAdapter.ViewHolder>(UserButtonDiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val userIcon: ImageView = view.findViewById(R.id.user_icon)
        private val userText: TextView = view.findViewById(R.id.user_text)
        private val divider: View = view.findViewById(R.id.divider)
        private val context: Context = view.context

        fun bind(userButton: UserButton, isLastItem: Boolean) {
            userIcon.setImageResource(userButton.icon)
            userText.text = userButton.text

            if (isLastItem){
                divider.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], position == (itemCount - 1))
    }

}

class UserButtonDiffCallback : DiffUtil.ItemCallback<UserButton>() {
    override fun areItemsTheSame(oldItem: UserButton, newItem: UserButton): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: UserButton, newItem: UserButton): Boolean = oldItem == newItem
}

