package andorid.example.collabrix.View.StudentUi

import andorid.example.collabrix.Model.StudentModel.StudentProfile
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
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
                    Icon(profile.image, contentDescription = "profile image", modifier = Modifier.fillMaxSize())
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
        Column(modifier = Modifier.padding(horizontal = 20.dp), ) {
            Text("Danger Zone", fontSize = 30.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(5.dp))
            Text("Actions are dangerous.....")


        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {},
            shape = RoundedCornerShape(8.dp,),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp))
        {
            Text("Delete Account", fontSize = 24.sp)
        }


    }
}

