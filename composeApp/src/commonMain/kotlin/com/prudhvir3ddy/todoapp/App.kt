package com.prudhvir3ddy.todoapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import todoapp.composeapp.generated.resources.Res
import todoapp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {

            TodoHeading()

            val todos by Todo.todos.collectAsState()

            todos.forEach {
                Column {
                    TodoUi(it, onTodoToggle =  {
                        Todo.toggleTodo(it)
                    })
                    Divider()
                }
            }
        }
    }
}

@Composable
fun TodoHeading(modifier: Modifier = Modifier) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "✅ Checklist for the day",
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun TodoUi(
    todo: Todo,
    onTodoToggle: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Checkbox(checked = todo.isDone, onCheckedChange = {
            onTodoToggle(todo.id)
        })
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(text = todo.name)
            Text(text = todo.description)
        }
    }
}