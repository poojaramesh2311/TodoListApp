package com.example.todolistapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoApiService {
    @GET("/todos")
    fun getTodos(): Call<List<TodoItem>>

    @POST("/todos")
    fun addTodo(@Body todoItem: TodoItem): Call<TodoItem>

    @PUT("/todos/{id}")
    fun updateTodo(@Path("id") id: Long, @Body todoItem: TodoItem): Call<TodoItem>

    @DELETE("/todos/{id}")
    fun deleteTodo(@Path("id") id: Long): Call<Void>
}
