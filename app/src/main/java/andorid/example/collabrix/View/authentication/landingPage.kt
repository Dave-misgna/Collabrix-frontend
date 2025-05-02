package andorid.example.collabrix.View.authentication

import andorid.example.collabrix.R
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPage(navController: NavHostController) {
    Scaffold(
        topBar = {
            Card(modifier = Modifier.background(Color.Cyan)) {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Collabrix") },
                    navigationIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.collabrixlogo),
                            contentDescription = "logo"
                        )
                    }
                )
            }

        }
    ){innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Collabrix", fontWeight = FontWeight.Bold, fontSize = 32.sp)
            Divider()
            Column(modifier = Modifier
                .background(Color.Cyan)
                .fillMaxWidth()
                .height(35.dp)
                .clickable { navController.navigate("studentdashboard") })
            {
                Text(text = "ClickMe")
            }

        }

    }
}
