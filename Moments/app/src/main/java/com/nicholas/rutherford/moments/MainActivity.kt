package com.nicholas.rutherford.moments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nicholas.rutherford.moments.data.CategoryTag
import com.nicholas.rutherford.moments.data.Note
import com.nicholas.rutherford.moments.home.HomeScreen
import com.nicholas.rutherford.moments.ui.theme.MomentsTheme
import com.nicholas.rutherford.moments.ui.theme.OffWhite

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MomentsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Moments") }
                        )
                    }
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).background(OffWhite)) {
                        HomeScreen(listOf(Note("Title", CategoryTag.Family)))
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MomentsTheme {
        Greeting("Android")
    }
}