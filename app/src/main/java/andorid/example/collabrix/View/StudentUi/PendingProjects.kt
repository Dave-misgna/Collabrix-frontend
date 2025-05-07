package andorid.example.collabrix.View.StudentUi

import andorid.example.collabrix.Data.Model.StudentModel.ActiveProjects
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PendingProjects(pending: List<ActiveProjects>){

    pending.forEach { project ->
        PendingResearches(project)
        Spacer(modifier = Modifier.height(16.dp))
    }

}
@Composable
fun PendingResearches(project: ActiveProjects) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                ) {
                Text(text = "Led by ${project.author}")
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Yellow),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(text = project.status, modifier = Modifier.padding(4.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = project.description)
            Spacer(modifier = Modifier.height(18.dp))

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(10.dp).fillMaxWidth()
                ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .padding(5.dp)


                ) {
                    Text(text = "Withdraw Application")
                }
            }

        }
    }
}