package com.example.week2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.week2.domain.Task
import com.example.week2.domain.mockTasks

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(listOf<Task>())
    private var allTasks = listOf<Task>()

    init {
        allTasks = mockTasks
        tasks = allTasks
    }

    fun addTask(task: Task) {
        allTasks = allTasks + task
        tasks = allTasks
    }

    fun removeTask(id: Int) {
        allTasks = allTasks.filter { it.id != id }
        tasks = allTasks
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
        tasks = allTasks
    }

    fun filterByDone(done: Boolean) {
        tasks = allTasks.filter { it.done == done }
    }

    fun showAll() {
        tasks = allTasks
    }

    fun sortByDueDate() {
        tasks = tasks.sortedBy { it.dueDate }
    }
}