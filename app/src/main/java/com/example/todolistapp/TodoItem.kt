package com.example.todolistapp


import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class TodoItem(
    @PrimaryKey var id: Long = 0,
    var title: String = "",
    var description: String = ""
) : RealmObject()


