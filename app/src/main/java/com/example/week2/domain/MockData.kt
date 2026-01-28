package com.example.week2.domain

import com.example.week2.model.Priority
import com.example.week2.model.Task
import java.time.LocalDate

val mockTasks: List<Task> = listOf(
    Task(1, "Kotlin perusteet", "Data class ja funktiot", Priority.HIGH, LocalDate.now().plusDays(1), false),
    Task(2, "Compose aloitus", "Column, Row ja Modifier", Priority.MEDIUM, LocalDate.now().plusDays(2), false),
    Task(3, "Listat", "List ja filter, map", Priority.MEDIUM, LocalDate.now().plusDays(3), false),
    Task(4, "GitHub", "Push ja week1 tag", Priority.HIGH, LocalDate.now().plusDays(4), false),
    Task(5, "YouTube demo", "1 minuutin video", Priority.MEDIUM, LocalDate.now().plusDays(5), false))
