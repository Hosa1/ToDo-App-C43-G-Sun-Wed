package com.route.todoappc43gsunwed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.todoappc43gsunwed.database.Task
import com.route.todoappc43gsunwed.databinding.ItemTaskBinding

class TaskListAdapter(var tasks: List<Task>? = null) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {
    // 1
    var onTaskClickListener: ((Task) -> Unit)? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        val task = tasks?.get(position) ?: return
        holder.binding.root.setOnClickListener {
            // 2-
            onTaskClickListener?.invoke(task)
        }
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return tasks?.size ?: 0
    }

    fun updateTasks(tasks: List<Task>?) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task?) {
            binding.taskTitle.text = task?.title
            binding.taskDate.text = task?.date?.toString()
        }
    }
}
