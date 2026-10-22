package com.example.evaluacionformativa2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.evaluacionformativa2.ui.screens.TaskFormScreen
import com.example.evaluacionformativa2.ui.screens.TaskListScreen
import com.example.evaluacionformativa2.ui.theme.EvaluacionFormativa2Theme
import com.example.evaluacionformativa2.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EvaluacionFormativa2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "taskList"
    ) {
        // Pantalla de Lista de Tareas
        composable("taskList") {
            TaskListScreen(
                viewModel = taskViewModel,
                onAddTask = {
                    navController.navigate("taskForm")
                },
                onEditTask = { taskId ->
                    navController.navigate("taskForm?taskId=$taskId")
                }
            )
        }

        // Pantalla Formulario (Crear / Editar)
        composable(
            route = "taskForm?taskId={taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStateEntry ->
            val taskIdArg = backStateEntry.arguments?.getInt("taskId") ?: -1
            val taskId = if (taskIdArg == -1) null else taskIdArg

            TaskFormScreen(
                taskId = taskId,
                viewModel = taskViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
