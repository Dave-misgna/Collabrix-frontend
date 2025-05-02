package andorid.example.collabrix.View.StudentUi

import andorid.example.collabrix.Model.ActiveProjects.ActiveProjects
import android.text.Layout.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyProjects(projects: List<ActiveProjects>) {
    projects.forEach { project ->
        ProjectCard(project)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ProjectCard(project: ActiveProjects) {
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
            Text(text = "Led by ${project.author}")
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Green),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(text = project.status, modifier = Modifier.padding(4.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = project.description)
            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(5.dp)


            ) {
                Text(text = "view detail")
            }
        }
    }
}
