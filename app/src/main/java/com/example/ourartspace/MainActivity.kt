package com.example.ourartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ourartspace.ui.theme.OurArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OurArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OurArtSpace()
                }
            }
        }
    }
}

@Composable
fun OurArtSpace(modifier:Modifier = Modifier) {
    val images = listOf(
        Triple(R.drawable.mercury, "Mercury", "NASA"),
        Triple(R.drawable.venus, "Venus", "NASA"),
        Triple(R.drawable.earth, "Earth", "NASA"),
        Triple(R.drawable.mars, "Mars", "NASA"),
        Triple(R.drawable.jupiter, "Jupiter", "NASA"),
        Triple(R.drawable.saturn, "Saturn", "NASA"),
        Triple(R.drawable.uranus, "Uranus", "NASA"),
        Triple(R.drawable.neptune, "Neptune", "NASA"),
    )
    Column(
        modifier = Modifier
//            .fillMaxSize()
            .border(width = 4.dp, Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        Image(contentScale = ContentScale.FillWidth, painter = painterResource(id = R.drawable.image01), contentDescription = null)
//        var index = 6
        var index by remember { mutableStateOf(6) }

        val (image, title, artist) = images[index]
        val painter = painterResource(id = image /* R.drawable.uranus */)

        Image(
            painter = painter, // = painterResource(id = R.drawable.mercury),
            contentDescription = title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .border(width = 8.dp, color = Color.Red)
                .fillMaxWidth()
                .then(
//                    (painter.state as? AsyncImagePainter.State.Success)
                    painter
                        ?.intrinsicSize
                        ?.let { intrinsicSize ->
                            Modifier.aspectRatio(intrinsicSize.width / intrinsicSize.height)
                        } ?: Modifier
                ),
//            modifier = Modifier
////                .aspectRatio(1.0f, true),
//                .fillMaxSize()
//                .border(width = 8.dp, color = Color.Red),
        )
        Text(title)
        Text(artist)
        BoxWithConstraints {
            val maxWidth = maxWidth

//            Row(horizontalArrangement = Arrangement.SpaceEvenly)
////            modifier = Modifier.fillMaxWidth())
//            {
//
//                Button(
//                    onClick = {},
//                    modifier = Modifier.width(maxWidth / 4)
//                ) {Text("Previous")}
//
//                Spacer(Modifier.width(40.dp))
//
//                Button(
//                    onClick = {},
//                    modifier = Modifier.width(maxWidth / 4)
//                ) {Text("Next")}
//            }
            Row(horizontalArrangement = Arrangement.spacedBy(40.dp),
            modifier = Modifier
                .offset(y = -(6).dp)
                .align(Alignment.BottomCenter))
            {

                Button(
                    onClick = { index = (--index).mod(images.size)},
                    modifier = Modifier.width(maxWidth / 4)
                ) {Text("Previous")}
                
                Button(
                    onClick = { index = (++index).mod(images.size)},
                    modifier = Modifier.width(maxWidth / 4)
                ) {Text("Next")}
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OurArtSpaceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            OurArtSpace()
        }
    }
}