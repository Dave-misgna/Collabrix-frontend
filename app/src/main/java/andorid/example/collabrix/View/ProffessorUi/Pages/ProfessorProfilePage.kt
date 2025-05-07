package andorid.example.collabrix.View.ProffessorUi.Pages

import andorid.example.collabrix.R
import andorid.example.collabrix.View.StudentUi.DeleteAccount
import andorid.example.collabrix.View.StudentUi.EducationalHistory
import andorid.example.collabrix.View.Common.SideBar
import andorid.example.collabrix.View.StudentUi.StudentDescription
import andorid.example.collabrix.View.StudentUi.UserProfile
import andorid.example.collabrix.View.StudentUi.UserSkills
import andorid.example.collabrix.ViewModel.ProfileViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessorProfile(
    navController: NavHostController,
    viewModel: ProfileViewModel = viewModel()
){
    //side bar navigation
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //importing from view model

    val studentinfo by viewModel.studentcard.collectAsState()
    val educationhistory by viewModel.educationHistory.collectAsState()
    val aboutstudent by viewModel.about.collectAsState()




    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                SideBar(
                    scope = scope,
                    drawerState = drawerState,
                    onMenuItemClick = { route->
                        navController.navigate(route)
                    }
                )

            }
        },

        ){
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Collabrix",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },

                    actions = {
                        Image(
                            painter = painterResource(id = R.drawable.collabrixlogo),
                            contentDescription = "logo",
                            modifier = Modifier.size(50.dp)
                        )
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Icon",
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                        )
                    },
                    modifier = Modifier.shadow(
                        elevation = 8.dp,
                        ambientColor = Color.Black,
                        spotColor = Color.Black
                    )

                )

            }
        ){innerpadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerpadding)
                    .padding(horizontal = 30.dp)
                    .fillMaxSize()
            ){
                item{
                    Text("My Profile", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Profile Setting", fontSize = 26.sp, fontWeight = FontWeight.W700)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Manage your personal information and account")

                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { navController.navigate("ProfileEdit")},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                    ) {
                        Text("Edit Profile")
                    }
                    Spacer(modifier=Modifier.height(18.dp))

                    UserProfile(studentinfo)

                    Spacer(modifier=Modifier.height(18.dp))

                    DeleteAccount()

                    Spacer(modifier=Modifier.height(18.dp))

                    StudentDescription(aboutstudent)

                    Spacer(modifier=Modifier.height(18.dp))

                    UserSkills(studentinfo.skills)

                    Spacer(modifier=Modifier.height(18.dp))

                    EducationalHistory(educationhistory)






                }
            }
        }

    }
}