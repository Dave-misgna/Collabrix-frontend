package andorid.example.collabrix

import andorid.example.collabrix.View.ProffessorUi.Pages.ProfessorBrowseResearch
import andorid.example.collabrix.View.ProffessorUi.Pages.ProfessorDashboard
import andorid.example.collabrix.View.ProffessorUi.Pages.ProfessorProfile
import andorid.example.collabrix.View.ProffessorUi.Pages.ProfessorProfileEdit
import andorid.example.collabrix.View.StudentUi.Pages.BrowseResearch
import andorid.example.collabrix.View.StudentUi.Pages.MyApplications
import andorid.example.collabrix.View.StudentUi.Pages.ProfileEdit
import andorid.example.collabrix.View.StudentUi.Pages.ProfilePage
import andorid.example.collabrix.View.StudentUi.Pages.StudentDashboard
import andorid.example.collabrix.View.authentication.Login
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController, userRole: String) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) { Login(navController) }

        // Student routes
        if (userRole == "student") {
            composable(Screen.StudentDashboard.route) { StudentDashboard(navController) }
            composable(Screen.StudentBrowseResearch.route){ BrowseResearch(navController) }
            composable(Screen.StudentProfile.route) { ProfilePage(navController) }
            composable(Screen.StudentApplication.route){ MyApplications(navController) }
            composable(Screen.StudentProfileEdit.route){ ProfileEdit(navController) }
        }

         //Professor routes
        if (userRole == "professor") {
            composable(Screen.ProfessorDashboard.route) {ProfessorDashboard(navController) }
            composable(Screen.ProfessorBrowseResearch.route){ProfessorBrowseResearch(navController)}
            composable(Screen.ProfessorProfile.route){ProfessorProfile(navController)}
            composable(Screen.ProfessorProfileEdit.route){ProfessorProfileEdit(navController)}

        }

        // Admin routes


        //in my login after successful login i will do
//        when (role) {
//            "student" -> navController.navigate(Screen.StudentDashboard.route)
//            "professor" -> navController.navigate(Screen.ProfessorDashboard.route)
//            "admin" -> navController.navigate(Screen.AdminDashboard.route)
//        }
    }
}
