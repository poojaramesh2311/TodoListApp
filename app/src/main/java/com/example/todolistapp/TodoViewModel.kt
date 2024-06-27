package com.example.todolistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm

class TodoViewModel : ViewModel() {

    private val realm: Realm by lazy { Realm.getDefaultInstance() }
    private val _todoItems = MutableLiveData<List<TodoItem>>()
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    private val repository = TodoRepository(RetrofitClient.instance.create(TodoApiService::class.java))

    fun fetchTodos() {
        repository.getTodos { todos ->
            _todoItems.postValue(todos)
            realm.executeTransaction { transactionRealm ->
                transactionRealm.copyToRealmOrUpdate(todos)
            }
        }
    }

    fun addTodo(todoItem: TodoItem) {
        repository.addTodo(todoItem) { newItem ->
            fetchTodos()
        }
    }

    fun updateTodo(todoItem: TodoItem) {
        repository.updateTodo(todoItem.id, todoItem) { updatedItem ->
            fetchTodos()
        }
    }

    fun deleteTodo(todoItem: TodoItem) {
        repository.deleteTodo(todoItem.id) { success ->
            if (success) fetchTodos()
        }
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}
