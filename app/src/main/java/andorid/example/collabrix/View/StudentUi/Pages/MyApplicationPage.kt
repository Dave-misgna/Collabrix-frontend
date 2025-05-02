package andorid.example.collabrix.View.StudentUi.Pages

import andorid.example.collabrix.R
import andorid.example.collabrix.View.StudentUi.PendingProjects
import andorid.example.collabrix.View.StudentUi.SideBar
import android.example.collabrix.ViewModel.BrowseResearchesViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApplications(
    navController: NavHostController,
    viewModel: BrowseResearchesViewModel = viewModel()
){
    // for the side bar navigation
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //selected button
    var selected by remember { mutableStateOf("pending") }

    //data from the view model
    val choosenOption by viewModel.pending.collectAsState()

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
                    .padding(horizontal = 30.dp, vertical = 12.dp)
                    .fillMaxSize()
            ){
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
                            modifier = Modifier.fillMaxWidth().background(Color.Gray),

                            ) {

                            val options = listOf("pending", "Approved", "Rejected")
                            options.forEach { option ->
                                Button(
                                    onClick = { selected = option },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (selected == option) Color.White else Color.Transparent,
                                        contentColor = Color.Black
                                    ),
                                    modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
                                ) {
                                    Text(option)
                                }

                            }
                        }
                    }



                    Spacer(modifier = Modifier.height(26.dp))

                    // displaying the pages i built for each buttons
                    when (selected) {
                        "pending" ->  PendingProjects(choosenOption)
                        "Approved" -> PendingProjects(choosenOption)
                        "Rejected" -> PendingProjects(choosenOption)
                    }
                }
            }
        }



    }
}