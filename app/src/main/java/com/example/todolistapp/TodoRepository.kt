package com.example.todolistapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoRepository(private val apiService: TodoApiService) {

    fun getTodos(callback: (List<TodoItem>) -> Unit) {
        apiService.getTodos().enqueue(object : Callback<List<TodoItem>> {
            override fun onResponse(call: Call<List<TodoItem>>, response: Response<List<TodoItem>>) {
                response.body()?.let { callback(it) }
            }

            override fun onFailure(call: Call<List<TodoItem>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun addTodo(todoItem: TodoItem, callback: (TodoItem) -> Unit) {
        apiService.addTodo(todoItem).enqueue(object : Callback<TodoItem> {
            override fun onResponse(call: Call<TodoItem>, response: Response<TodoItem>) {
                response.body()?.let { callback(it) }
            }

            override fun onFailure(call: Call<TodoItem>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun updateTodo(id: Long, todoItem: TodoItem, callback: (TodoItem) -> Unit) {
        apiService.updateTodo(id, todoItem).enqueue(object : Callback<TodoItem> {
            override fun onResponse(call: Call<TodoItem>, response: Response<TodoItem>) {
                response.body()?.let { callback(it) }
            }

            override fun onFailure(call: Call<TodoItem>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun deleteTodo(id: Long, callback: (Boolean) -> Unit) {
        apiService.deleteTodo(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
