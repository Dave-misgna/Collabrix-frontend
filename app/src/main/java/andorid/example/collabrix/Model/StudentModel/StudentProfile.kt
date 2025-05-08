package andorid.example.collabrix.Model.StudentModel

import androidx.compose.ui.graphics.vector.ImageVector

data class StudentProfile(
    val id: String,
    val name: String,
    val department: String,
    val email: String,
    val college: String,
    val year: String,
    val skill: List<String>,
    val description: String
)
