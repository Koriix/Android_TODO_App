package com.example.todoapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.TodoEntity
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.example.todoapp.databinding.TodoItemBinding

class TodoAdapter (private val onItemClicked: (TodoEntity) -> Unit) : ListAdapter<TodoEntity, TodoAdapter.TodoViewHolder>(TodoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = getItem(position)

        holder.bind(currentTodo) // plusz

        holder.itemView.setOnClickListener {
            Log.d("Click", currentTodo.toString())
            onItemClicked(currentTodo)
        }
        holder.bind(currentTodo)
    }

    class TodoViewHolder(private var binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: TodoEntity) {
            binding.titleTextView.text = todo.title
            binding.descriptionTextView.text = todo.description
        }
    }

    companion object {
        private val TodoDiffCallback = object : DiffUtil.ItemCallback<TodoEntity>() {
            override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
                return oldItem.id === newItem.id
            }

            override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}