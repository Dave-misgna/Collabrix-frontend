package andorid.example.collabrix.View.StudentUi.Pages

import andorid.example.collabrix.R
import andorid.example.collabrix.View.StudentUi.Components.PendingProjects
import andorid.example.collabrix.View.StudentUi.Components.SideBar
import andorid.example.collabrix.ViewModel.MyApplicationsViewModel
import andorid.example.collabrix.ViewModel.MyApplicationsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun MyApplicationPage(
    navController: NavHostController,
    viewModel: MyApplicationsViewModel = viewModel()
) {
    // for the side bar navigation
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // selected button
    var selected by remember { mutableStateOf("pending") }

    // data from the view model
    val state by viewModel.state.collectAsState()

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
                    .padding(horizontal = 30.dp, vertical = 12.dp)
                    .fillMaxSize()
            ) {
                item {
                    Text("My Applications", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Research Applications", fontSize = 26.sp, fontWeight = FontWeight.W700)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Track the status of your research Project Applications")

                    Spacer(modifier = Modifier.height(15.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray)
                        ) {
                            val options = listOf("pending", "Approved", "Rejected")
                            options.forEach { option ->
                                Button(
                                    onClick = { selected = option },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (selected == option) Color.White else Color.Transparent,
                                        contentColor = Color.Black
                                    ),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 4.dp)
                                ) {
                                    Text(option)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(26.dp))

                    when (state) {
                        is MyApplicationsState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is MyApplicationsState.Error -> {
                            Text(
                                text = (state as MyApplicationsState.Error).message,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        is MyApplicationsState.Success -> {
                            val successState = state as MyApplicationsState.Success
                            when (selected) {
                                "pending" -> PendingProjects(
                                    projects = successState.pendingProjects,
                                    isLoading = false,
                                    error = null,
                                    onWithdrawClick = { projectId ->
                                        viewModel.withdrawApplication(projectId)
                                    }
                                )
                                "Approved" -> PendingProjects(
                                    projects = successState.approvedProjects,
                                    isLoading = false,
                                    error = null,
                                    onWithdrawClick = { /* No action for approved projects */ }
                                )
                                "Rejected" -> PendingProjects(
                                    projects = successState.rejectedProjects,
                                    isLoading = false,
                                    error = null,
                                    onWithdrawClick = { /* No action for rejected projects */ }
                                )
                            }
                        }
                        else -> {
                            Text("No applications available")
                        }
                    }
                }
            }
        }
    }
}