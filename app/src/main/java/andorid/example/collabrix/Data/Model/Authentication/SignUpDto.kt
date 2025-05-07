package andorid.example.collabrix.Data.Model.Authentication

data class SignUpDto(
    val email: String,
    val password: String,
    val name: String,
    val role: String
)
