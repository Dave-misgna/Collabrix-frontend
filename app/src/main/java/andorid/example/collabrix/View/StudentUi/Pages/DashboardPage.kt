package andorid.example.collabrix.View.StudentUi.Pages

import andorid.example.collabrix.R
import andorid.example.collabrix.View.StudentUi.MyProjects
import andorid.example.collabrix.View.StudentUi.SideBar
import andorid.example.collabrix.ViewModel.DashboardViewModel

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
fun StudentDashboard(
    navController: NavHostController,
    viewModel: DashboardViewModel = viewModel()

) {
    //the username who loged in
    var name by remember { mutableStateOf("Dawit") }

    // for the Side bar navigation
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //viewmodel for the dashboard
    val activeProjects by viewModel.projects.collectAsState()
    val totalProjects by viewModel.totalAppliedProjects.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                SideBar(
                    scope = scope,
                    drawerState = drawerState,
                    onMenuItemClick = {route->
                        navController.navigate(route)}
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
//                Divider(color = Color.Black.copy(alpha = 0.3f), thickness = 1.dp)
            }
        ) { innerpadding ->
            LazyColumn(

                modifier = Modifier
                    .padding(innerpadding)
                    .padding(horizontal = 30.dp)
                    .fillMaxSize()
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Text(
                            text = "Student Dashboard",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Welcome back, $name",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                    Spacer(modifier = Modifier.height(18.dp))

                    //Applied projects box
                    Card(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxSize()
                                .padding(horizontal = 10.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "Applied Projects",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${totalProjects.size}",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    //Active Projects indicator Box
                    Card(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        shape = RoundedCornerShape(22.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxSize()
                                .padding(horizontal = 10.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "Active Projects",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${activeProjects.size}",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = "Your Active Projects",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    //list of Active projects
                    MyProjects(activeProjects)


                }
            }

        }
    }
}