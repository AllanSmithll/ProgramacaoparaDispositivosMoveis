package com.photosgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.photosgallery.ui.theme.PhotosGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotosGalleryTheme {
                PhotosGalleryScreen()
            }
        }
    }
}

data class Art(
    val image: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtInfo(art: Art, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = art.image),
            contentDescription = "Imagem da obra de arte",
            modifier = Modifier
                .size(300.dp)
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = art.title,
            fontSize = 20.sp
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = art.artist,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(text = "(" + art.year + ")")
        }
    }
}

@Composable
fun PhotosGalleryScreen() {
    val arts = listOf(
        Art(R.drawable.pexels_badis_benkhelil_1135505371_21430948, "Titulo 1", "Badis", "2024"),
        Art(R.drawable.pexels_antoine_1518705150_27133692, "Titulo 1", "Antoine", "2024"),
    )
    var currentIndex = 0
    val currentArt = arts[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtInfo(art = currentArt)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                currentIndex = if (currentIndex > 0) currentIndex - 1 else arts.size - 1
            }) {
                //Text(text = "Previous")
            }
            Button(onClick = {
                currentIndex = if (currentIndex < arts.size - 1) currentIndex + 1 else 0
            }) {
                //Text(text = "Next")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PhotosGalleryPreview() {
    PhotosGalleryTheme {
        PhotosGalleryScreen()
    }
}
