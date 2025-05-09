package andorid.example.collabrix

import andorid.example.collabrix.View.StudentUi.Pages.BrowseResearch
import andorid.example.collabrix.View.StudentUi.Pages.DashboardPage
import andorid.example.collabrix.View.StudentUi.Pages.MyApplicationPage
import andorid.example.collabrix.View.StudentUi.Pages.ProfileEditPage
import andorid.example.collabrix.View.StudentUi.Pages.ProfilePage
import andorid.example.collabrix.ViewModel.DashboardViewModel
import andorid.example.collabrix.ViewModel.MyApplicationsViewModel
import andorid.example.collabrix.ui.theme.CollabrixTheme
import android.example.collabrix.ViewModel.BrowseResearchesViewModel
import android.example.collabrix.ViewModel.StudentViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollabrixTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Dashboard.route
                    ) {
                        composable(Screen.Dashboard.route) { 
                            val viewModel: DashboardViewModel = hiltViewModel()
                            DashboardPage(navController, viewModel) 
                        }
                        composable(Screen.BrowseResearch.route) { 
                            val viewModel: BrowseResearchesViewModel = hiltViewModel()
                            BrowseResearch(navController, viewModel) 
                        }
                        composable(Screen.MyApplication.route) { 
                            val viewModel: MyApplicationsViewModel = hiltViewModel()
                            MyApplicationPage(navController, viewModel)
                        }
                        composable(Screen.Profile.route) { 
                            val viewModel: StudentViewModel = hiltViewModel()
                            ProfilePage(navController, viewModel) 
                        }
                        composable(Screen.ProfileEdit.route) { 
                            val viewModel: StudentViewModel = hiltViewModel()
                            ProfileEditPage(navController, viewModel) 
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Dashboard : Screen("studentdashboard")
    object BrowseResearch : Screen("BrowseResearch")
    object MyApplication : Screen("my_application")
    object Profile : Screen("profile")
    object ProfileEdit : Screen("profile_edit")
}





