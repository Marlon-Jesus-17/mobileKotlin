package com.marlebas.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marlebas.newsapp.PostDetailScreen
import com.marlebas.newsapp.PostListScreen

@Composable
fun NavGraph(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ){
        composable("list"){
            PostListScreen(navController)
        }

        composable(
            "detail/{postId}",
            listOf(
                navArgument("postId"){
                    type = NavType.StringType
                }
            )
        ) {
            backStackEntry -> val postId = backStackEntry.arguments?.getString("postId")?.toIntOrNull()

            PostDetailScreen(postId)

        }
    }
}