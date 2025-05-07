package andorid.example.collabrix

import andorid.example.collabrix.View.StudentUi.Pages.BrowseResearch
import andorid.example.collabrix.View.StudentUi.Pages.MyApplications
import andorid.example.collabrix.View.StudentUi.Pages.ProfileEdit
import andorid.example.collabrix.View.StudentUi.Pages.ProfilePage
import andorid.example.collabrix.View.StudentUi.Pages.StudentDashboard
import andorid.example.collabrix.View.authentication.ForgotPassword
import andorid.example.collabrix.View.authentication.LandingPage
import andorid.example.collabrix.View.authentication.Login
import andorid.example.collabrix.View.authentication.SignUp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import andorid.example.collabrix.ui.theme.CollabrixTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollabrixTheme {
                setContent {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "StudentDashboard"
                    ) {
                        composable("landingpage") { LandingPage(navController) }
                        composable("signup") { SignUp(navController) }
                        composable("signin") { Login(navController) }
                        composable("forgotpassword") { ForgotPassword(navController) }
                        composable("StudentDashboard") { StudentDashboard(navController) }
                        composable("BrowseResearch"){ BrowseResearch(navController) }
                        composable("MyApplication"){ MyApplications(navController) }
                        composable("MyProfile"){ ProfilePage(navController) }
                        composable("ProfileEdit"){ ProfileEdit(navController) }
                    }
                }




            }
        }
    }
}





