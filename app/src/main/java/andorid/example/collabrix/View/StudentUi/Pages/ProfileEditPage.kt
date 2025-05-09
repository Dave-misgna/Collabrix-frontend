package andorid.example.collabrix.View.StudentUi.Pages

import andorid.example.collabrix.R
import andorid.example.collabrix.View.StudentUi.Components.EducationalHistoryEdit
import andorid.example.collabrix.View.StudentUi.Components.SideBar
import andorid.example.collabrix.View.StudentUi.Components.StudentDescriptionEdit
import andorid.example.collabrix.View.StudentUi.Components.UserProfileEdit
import andorid.example.collabrix.View.StudentUi.Components.UserSkillsEdit
import android.example.collabrix.ViewModel.StudentState
import android.example.collabrix.ViewModel.StudentViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun ProfileEditPage(
    navController: NavHostController,
    viewModel: StudentViewModel = viewModel()
) {
    // for the side bar navigation
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // for the viewmodel data
    val studentState by viewModel.state.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                SideBar(
                    scope = scope,
                    drawerState = drawerState,
                    onMenuItemClick = { route ->
                        navController.navigate(route)
                    }
                )
            }
        }
    ) {
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
        ) { innerpadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerpadding)
                    .padding(horizontal = 30.dp)
                    .fillMaxSize()
            ) {
                item {
                    Text(text = "Edit Profile", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(20.dp))

                    when (studentState) {
                        is StudentState.Initial -> {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is StudentState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is StudentState.Success -> {
                            val profile = (studentState as StudentState.Success).profile
                            if (profile != null) {
                                UserProfileEdit(
                                    profile = profile,
                                    onProfileUpdate = { updatedProfile ->
                                        viewModel.updateProfile(updatedProfile)
                                    }
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                
                                StudentDescriptionEdit(
                                    profile = profile,
                                    onProfileUpdate = { updatedProfile ->
                                        viewModel.updateProfile(updatedProfile)
                                    }
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                
                                UserSkillsEdit(
                                    profile = profile,
                                    onProfileUpdate = { updatedProfile ->
                                        viewModel.updateProfile(updatedProfile)
                                    }
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                
                                EducationalHistoryEdit(
                                    profile = profile,
                                    onProfileUpdate = { updatedProfile ->
                                        viewModel.updateProfile(updatedProfile)
                                    }
                                )
                            }
                        }
                        is StudentState.Error -> {
                            Text(
                                text = (studentState as StudentState.Error).message,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}