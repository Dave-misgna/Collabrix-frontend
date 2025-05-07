package andorid.example.collabrix.View.Common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SideBar(
    scope: CoroutineScope,
    drawerState: DrawerState,
    onMenuItemClick: (String) -> Unit,


){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Collabrix",
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "close nav menu",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        scope.launch {
                            drawerState.close()
                        }
                    }
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(horizontal = 24.dp).clickable { onMenuItemClick("studentdashboard") }
        ) {
            Text(
                text = ("Dashboard"),
                fontSize = 25.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(horizontal = 24.dp).clickable { onMenuItemClick("BrowseResearch") }

        ) {
            Text(
                text = ("Browse Research"),
                fontSize = 25.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )
        }

        Spacer(modifier = Modifier.height(15.dp))


        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clickable {  onMenuItemClick("MyApplication")}
        ) {
            Text(
                text = ("My Applications"),
                fontSize = 25.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )
        }

        Spacer(modifier = Modifier.height(15.dp))


        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clickable { onMenuItemClick("MyProfile") }
        ) {
            Text(
                text = ("Profile"),
                fontSize = 25.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )
        }

        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Log out")
            Text(
                text = ("Logout"),
                fontSize = 25.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )
        }
    }


}