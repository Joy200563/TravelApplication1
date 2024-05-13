import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelapplibcation.R
import com.example.travelapplibcation.navigation.ROUTE_ADD_PLACE
import com.example.travelapplibcation.navigation.ROUTE_VIEW_PLACES


@Composable
fun HomeScreen(navController: NavController) {
    Box {
        Image(
            painter = painterResource(
                id = R.drawable.girrafe
            ), contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column {


            Text(
                text = "Travel data storage made easy!!!",
                color = Color.Black
            )
            Button(
                onClick = { navController.navigate(ROUTE_ADD_PLACE) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add Places")
            }
            Button(
                onClick = { navController.navigate(ROUTE_VIEW_PLACES) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "View Places")
            }
        }
    }
}
    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun HomeScreenPreview() {
        HomeScreen(rememberNavController())
    }

