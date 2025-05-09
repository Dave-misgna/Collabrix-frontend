package andorid.example.collabrix.data.model

data class StudentProfile(
    val id: Int,
    val name: String,
    val department: String,
    val email: String,
    val college: String,
    val year: String,
    val skill: List<String>,
    val description: String
)
