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

@Composable
fun RecommendedResearches(
    projects: List<ActiveProjects>,
    isLoading: Boolean,
    error: String?,
    onApplyClick: (String) -> Unit
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
                text = "No recommended researches available",
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
                    DisplayedResearches(
                        project = project,
                        onApplyClick = { onApplyClick(project.id.toString()) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayedResearches(
    project: ActiveProjects,
    onApplyClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Professor: ${project.professorName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when (project.status) {
                        ProjectStatus.OPEN -> Color.Green
                        ProjectStatus.CLOSED -> Color.Red
                        else -> Color.Gray
                    }
                ),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = "Status: ${project.status}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = project.description)
            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = onApplyClick,
                modifier = Modifier.padding(5.dp),
                enabled = project.status == ProjectStatus.OPEN
            ) {
                Text(text = "Apply Now")
            }
        }
    }
}