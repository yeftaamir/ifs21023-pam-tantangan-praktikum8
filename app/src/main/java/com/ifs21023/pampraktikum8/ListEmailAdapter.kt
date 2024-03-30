package com.ifs21023.pampraktikum8

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21023.pampraktikum8.databinding.ItemRowMailBinding


class ListEmailAdapter(private val listEmail: ArrayList<EmailItem>) :
    RecyclerView.Adapter<ListEmailAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowMailBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val email = listEmail[position]
        holder.binding.ivIkonEmail.setImageResource(email.icon)
        holder.binding.tvItemEmail.text = email.nama
        holder.binding.tvItemSubjek.text = email.subjek
        holder.binding.tvItemTeks.text = email.teks
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listEmail[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listEmail.size

    class ListViewHolder(var binding: ItemRowMailBinding) : RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: EmailItem)
    }

}