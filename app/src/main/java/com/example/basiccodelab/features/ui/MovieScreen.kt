package com.example.basiccodelab.features.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.Coil
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.basiccodelab.R
import com.example.basiccodelab.data.model.Movie
import com.example.basiccodelab.data.network.ApiServiceClient

@Composable
fun MovieScreen(
    viewModel: MovieViewModel = hiltViewModel()
) {
    val res = viewModel.res.value

    if(res.isLoading) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//            CircularProgressIndicator()
        }
    }

    if(res.error.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(text = res.error)
        }
    }

    if(res.data.isNotEmpty()) {
        LazyColumn {
            items(res.data, key = { it.id!! }) { res ->
                EachRow(
                    res = res
                )

            }
        }
    }
}

@Composable
fun EachRow(
    res: Movie.Result
) {
    MaterialTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${ApiServiceClient.IMAGE_URL}${res.poster_path}")
                            .placeholder(R.drawable.load_circle)
                            .crossfade(true)
                            .transformations(CircleCropTransformation())
                            .build()
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(10.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(
                            Alignment.CenterVertically
                        )
                ) {
                    Text(text = res.original_title.toString(), style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
                    Text(text = res.overview.toString(), style = TextStyle(fontSize = 12.sp), textAlign = TextAlign.Center)
                }

            }
        }
    }
}