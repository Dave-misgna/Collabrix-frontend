package andorid.example.collabrix.data.model

data class ActiveProjects(
    val id: Int,
    val title: String,
    val professorName: String,
    val professorEmail: String,
    val status: ProjectStatus,
    val description: String,
    val department: String,
    val requirements: List<String>,
    val positionsAvailable: Int,
    val currentApplicants: Int,
    val applicationDeadline: String,
    val startDate: String? = null,
    val endDate: String? = null,
    val createdAt: String,
    val updatedAt: String
)

enum class ProjectStatus {
    OPEN,
    CLOSED,
    IN_PROGRESS,
    COMPLETED
}
