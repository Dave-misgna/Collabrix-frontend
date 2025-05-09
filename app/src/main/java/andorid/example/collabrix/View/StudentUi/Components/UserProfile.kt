package andorid.example.collabrix.View.StudentUi.Components

import andorid.example.collabrix.data.model.StudentProfile
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserProfile(profile: StudentProfile){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ){
                Card(
                    shape = CircleShape,
                    modifier = Modifier.size(120.dp)
                ) {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "profile image", modifier = Modifier.fillMaxSize())
                }
                Spacer(modifier = Modifier.height(12.dp))

                Text(profile.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(70.dp).height(24.dp)
                ) {
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text("Student", fontWeight = FontWeight.W900)
                    }

                }
                Spacer(modifier = Modifier.height(12.dp))
                Column(horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(profile.email, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier=Modifier.height(8.dp))
                    Text(profile.college, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier=Modifier.height(8.dp))
                    Text(profile.year, fontSize = 15.sp, fontWeight = FontWeight.Bold)

                }





            }
        }
    }
}

@Composable
fun DeleteAccount(){
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8B7B7)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.padding( 20.dp), ) {
            Text("Danger Zone", fontSize = 30.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(5.dp))
            Text("Actions  here cannot be undone")

            Spacer(modifier = Modifier.height(15.dp))

            Button(onClick = {},
                shape = RoundedCornerShape(8.dp,),
                modifier = Modifier.fillMaxWidth())
            {
                Text("Delete Account", fontSize = 24.sp)
            }


        }




    }
}

@Composable
fun StudentDescription(aboutme: StudentProfile){
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Text("About Me", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.height(6.dp))
        Text(aboutme.description, modifier = Modifier.padding(10.dp))



    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserSkills(skill: StudentProfile) {
    val skill = skill.skill
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text("Skills", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
        FlowRow(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skill.forEach { skills ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = skills,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun EducationalHistory(education: StudentProfile){
    Card (
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Text("Education", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(education.college, fontSize = 24.sp)
            Text(education.department,fontSize = 24.sp)
            Text(education.year,fontSize = 24.sp)


        }

    }
}


