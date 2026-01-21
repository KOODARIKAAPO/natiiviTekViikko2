package com.example.week2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week2.domain.Priority
import com.example.week2.domain.Task
import java.time.LocalDate

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel = viewModel()
) {
    val tasks = taskViewModel.tasks
    var newTaskTitle by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Tasks",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Enter task name...") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(16.dp))


            Button(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        val newTask = Task(
                            id = (tasks.maxOfOrNull { it.id } ?: 0) + 1,
                            title = newTaskTitle,
                            description = "",
                            priority = Priority.MEDIUM,
                            dueDate = LocalDate.now().plusDays(7),
                            done = false
                        )
                        taskViewModel.addTask(newTask)
                        newTaskTitle = ""
                    }
                },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(56.dp), // Neliömäinen/Pyöreä koko
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(onClick = { taskViewModel.sortByDueDate() }) { Text("Sort Date") }
            OutlinedButton(onClick = { taskViewModel.filterByDone(false) }) { Text("Todo") }
            OutlinedButton(onClick = { taskViewModel.showAll() }) { Text("All") }
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp) // Välit korttien välissä
        ) {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onToggle = { taskViewModel.toggleDone(task.id) },
                    onDelete = { taskViewModel.removeTask(task.id) }
                )
            }
        }
    }
}
@Composable
fun TaskItem(task: Task, onToggle: () -> Unit, onDelete: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (task.done) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = task.done,
                    onCheckedChange = { onToggle() }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    // Otsikko yliviivauksella jos tehty
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium,
                        textDecoration = if (task.done) TextDecoration.LineThrough else TextDecoration.None,
                        color = if (task.done) Color.Gray else MaterialTheme.colorScheme.onSurface
                    )

                    // Päivämäärä ikonin kanssa
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = task.dueDate.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }

            // Roskakori-ikoni napin sijaan
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}