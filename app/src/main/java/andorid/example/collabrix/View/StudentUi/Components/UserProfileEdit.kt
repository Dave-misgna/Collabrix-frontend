package andorid.example.collabrix.View.StudentUi.Components

import andorid.example.collabrix.data.model.StudentProfile
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserProfileEdit(
    profile: StudentProfile,
    onProfileUpdate: (StudentProfile) -> Unit
) {
    var email by remember { mutableStateOf(profile.email) }
    var college by remember { mutableStateOf(profile.college) }
    var year by remember { mutableStateOf(profile.year) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier.size(120.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "profile image",
                        modifier = Modifier.fillMaxSize()
                    )
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Student", fontWeight = FontWeight.W900)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    Column {
                        Text("Email:", fontWeight = FontWeight.Bold)
                        OutlinedTextField(
                            value = email,
                            onValueChange = { 
                                email = it
                                onProfileUpdate(profile.copy(email = it))
                            },
                            placeholder = { Text(email) },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                            shape = RoundedCornerShape(8.dp),
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column {
                        Text("University/College:")
                        OutlinedTextField(
                            value = college,
                            onValueChange = { 
                                college = it
                                onProfileUpdate(profile.copy(college = it))
                            },
                            placeholder = { Text(college) },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                            shape = RoundedCornerShape(8.dp),
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column {
                        Text("Year:")
                        OutlinedTextField(
                            value = year,
                            onValueChange = { 
                                year = it
                                onProfileUpdate(profile.copy(year = it))
                            },
                            placeholder = { Text(year) },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                            shape = RoundedCornerShape(8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StudentDescriptionEdit(
    profile: StudentProfile,
    onProfileUpdate: (StudentProfile) -> Unit
) {
    var description by remember { mutableStateOf(profile.description) }

    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Text("About Me", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { 
                description = it
                onProfileUpdate(profile.copy(description = it))
            },
            placeholder = { Text("Write something about your self.....") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            maxLines = 10,
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserSkillsEdit(
    profile: StudentProfile,
    onProfileUpdate: (StudentProfile) -> Unit
) {
    var myskill by remember { mutableStateOf("") }
    var skills by remember { mutableStateOf(profile.skill) }

    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Skills", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
        FlowRow(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            skills.forEach { skill ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = skill,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = myskill,
                    onValueChange = { myskill = it },
                    modifier = Modifier.width(200.dp).height(50.dp),
                    placeholder = { Text("Add new skills") }
                )
                Button(
                    onClick = {
                        if (myskill.isNotBlank()) {
                            skills = skills + myskill
                            onProfileUpdate(profile.copy(skill = skills))
                            myskill = ""
                        }
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("+")
                }
            }
        }
    }
}

@Composable
fun EducationalHistoryEdit(
    profile: StudentProfile,
    onProfileUpdate: (StudentProfile) -> Unit
) {
    var college by remember { mutableStateOf(profile.college) }
    var department by remember { mutableStateOf(profile.department) }
    var year by remember { mutableStateOf(profile.year) }

    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Text("Education", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = college,
                onValueChange = { 
                    college = it
                    onProfileUpdate(profile.copy(college = it))
                },
                label = { Text("College/University") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = department,
                onValueChange = { 
                    department = it
                    onProfileUpdate(profile.copy(department = it))
                },
                label = { Text("Department") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = year,
                onValueChange = { 
                    year = it
                    onProfileUpdate(profile.copy(year = it))
                },
                label = { Text("Year") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
