package andorid.example.collabrix.View.StudentUi.Components

import andorid.example.collabrix.data.model.ActiveProjects
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PendingProjects(
    projects: List<ActiveProjects>,
    isLoading: Boolean,
    error: String?,
    onWithdrawClick: (String) -> Unit
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
                text = "No pending applications",
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
                    PendingResearches(
                        project = project,
                        onWithdrawClick = { onWithdrawClick(project.id.toString()) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingResearches(
    project: ActiveProjects,
    onWithdrawClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = project.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Professor: ${project.professorName}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Status: ${project.status}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = project.description)
            Spacer(modifier = Modifier.height(18.dp))

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = onWithdrawClick,
                    modifier = Modifier.padding(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text(text = "Withdraw Application")
                }
            }
        }
    }
}