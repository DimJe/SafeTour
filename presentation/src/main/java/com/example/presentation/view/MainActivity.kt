package com.example.presentation.view


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.ui.theme.SafeTourTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SafeTourTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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
@Composable
fun WaveCanvas() {
    Canvas(modifier = Modifier.fillMaxWidth().height(70.dp)) {

        val path = Path().apply {
            moveTo(0f, size.height-70.dp.toPx())
            cubicTo(x1 = size.width/10, y1 = size.height-70.dp.toPx(), x2 = size.width/3, y2 = size.height-110.dp.toPx(), x3 = size.width/2, y3 = size.height-110.dp.toPx())
            cubicTo(x1 = size.width*2/3, y1 = size.height-110.dp.toPx(), x2 = size.width-size.width/10, y2 = size.height-70.dp.toPx(), x3 = size.width, size.height-70.dp.toPx())
            lineTo(size.width,300.dp.toPx())
            lineTo(0f,300.dp.toPx())
            close()
        }
        drawPath(path = path, color = Color.Black)
    }
}
@Composable
fun CustomBottomNavigation() {
    val items = listOf("Home", "Search", "Profile","Setting")
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        WaveCanvas()

        // Bottom Navigation Icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                if (index == (items.size/2)){
                    Spacer(modifier = Modifier.size(70.dp))
                }
                NavigationBarItem(
                    modifier = Modifier.wrapContentSize(),
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color.White, Color.White,Color.Black,Color.Gray,Color.White,Color.White,Color.White
                    ),
                    label = { Text(text = item) },
                    icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    alwaysShowLabel = true
                )
            }
        }

        // Circular Button in the Middle
        FloatingActionButton(
            onClick = { /* TODO */ },
            shape = CircleShape,
            modifier = Modifier
                .size(45.dp)
                .offset(y = (-50).dp), // Offset to position the button above the navigation bar
            contentColor = Color.Red,
            containerColor = Color.Red,
            content = { Text("+", fontSize = 24.sp, color = Color.White) }
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SafeTourTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {CustomBottomNavigation()}
        ) {
            val data = List(1000){"$it"}
            Column(modifier = Modifier.fillMaxSize()){
                LazyColumn(modifier = Modifier.fillMaxWidth().background(Color.Gray)) {
                    items(data){
                        Text("testText $it", color = Color.Red)
                    }
                }
            }
        }
    }
}