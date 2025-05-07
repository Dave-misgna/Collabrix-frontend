package andorid.example.collabrix

sealed class Screen(val route: String) {
    //student pages
    object StudentDashboard: Screen("StudentDashboard")
    object StudentBrowseResearch: Screen("BrowseResearch")
    object StudentApplication:Screen("MyApplication")
    object StudentProfile: Screen("MyProfile")
    object StudentProfileEdit: Screen("ProfileEdit")

    //Authentication Pages
    object Login: Screen("Login")
    object SignUp: Screen("SignUp")
    object ForgotPassword: Screen("ForgotPassword")

    //Professor pages
    object ProfessorDashboard: Screen("ProfessorDashboard")
    object ProfessorBrowseResearch: Screen("ProfessorBrowseResearch")
    object ProfessorProfile: Screen("ProfessorProfile")
    object ProfessorProfileEdit: Screen("ProfessorProfileEdit")

}