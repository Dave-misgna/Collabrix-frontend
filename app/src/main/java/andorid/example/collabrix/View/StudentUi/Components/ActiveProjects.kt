package andorid.example.collabrix.View.StudentUi.Components


import andorid.example.collabrix.data.model.ActiveProjects
import andorid.example.collabrix.data.model.ProjectStatus
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyProjects(
    projects: List<ActiveProjects>,
    isLoading: Boolean,
    error: String?,
    onProjectClick: (String) -> Unit
) {
    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }
        projects.isEmpty() -> {
            Text(
                text = "No active projects",
                modifier = Modifier.padding(16.dp)
            )
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                projects.forEach { project ->
                    ProjectCard(project = project, onClick = { onProjectClick(project.id.toString()) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectCard(
    project: ActiveProjects,
    onClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = project.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Led by ${project.professorName}")
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when (project.status) {
                        ProjectStatus.IN_PROGRESS -> Color.Green
                        ProjectStatus.COMPLETED -> Color.Blue
                        ProjectStatus.OPEN -> Color(0xFF4CAF50) // Light Green
                        ProjectStatus.CLOSED -> Color.Gray
                    }
                ),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = project.status.name.replace("_", " "),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = project.description)
            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = "View Details")
            }
        }
    }
}
