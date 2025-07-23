package app.notes.appinf

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import app.notes.R

@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("home"){
            popUpTo("splash"){inclusive = true}
        }
    }
    
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 35.dp, bottom = 35.dp),
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null,
            modifier = Modifier.size(200.dp)
            .align(Alignment.Center))

        Text(text = "Notes", modifier = Modifier.align(Alignment.BottomCenter))
    }

}


@Preview(showBackground = true)
@Composable
fun splashPrev(){
    SplashScreen(rememberNavController())
}