package com.example.just_task.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.just_task.databinding.ItemTaskBinding
import com.example.just_task.model.Book

class BookAdapter : Adapter<BookAdapter.BookViewHolder>() {

    private val list = arrayListOf<Book>()

    fun addBooks(newList: List<Book>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class BookViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.tvTitle.text = book.name
            binding.tvDescription.text = book.author

        }
    }
}