package andorid.example.collabrix.Data.Model.StudentModel

import androidx.compose.ui.graphics.vector.ImageVector

data class StudentProfile(
    val id: String,
    val name: String?=null,
    val department: String?= null,
    val email: String,
    val college: String?=null,
    val year: String? = null,
    val description: String? = null,
    val skills: List<Skills>? = null

)
