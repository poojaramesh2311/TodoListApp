package com.example.todolistapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R

class MainActivity : AppCompatActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoTitle: EditText = findViewById(R.id.todoTitle)
        val todoDescription: EditText = findViewById(R.id.todoDescription)
        val addButton: Button = findViewById(R.id.addButton)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TodoAdapter(emptyList()) { todoItem ->
            // Handle item click for editing or deleting
        }
        recyclerView.adapter = adapter

        todoViewModel.todoItems.observe(this, Observer { todos ->
            adapter.todos = todos
            adapter.notifyDataSetChanged()
        })

        addButton.setOnClickListener {
            val title = todoTitle.text.toString()
            val description = todoDescription.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                val newTodo = TodoItem(
                    id = System.currentTimeMillis(), // Simple unique ID generation
                    title = title,
                    description = description
                )
                todoViewModel.addTodo(newTodo)
                todoTitle.text.clear()
                todoDescription.text.clear()
            }
        }

        todoViewModel.fetchTodos()
    }
}

