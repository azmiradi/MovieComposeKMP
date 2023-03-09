
package com.azmiradi.moviecomposekmp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.azmiradi.moviecomposekmp.data.model.ResultItem
import com.azmiradi.moviecomposekmp.data.utils.IMAGE_BASE_URL
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun MovieItem(movie: ResultItem, onClick:()->Unit) {
    val painter =
        rememberAsyncImagePainter(IMAGE_BASE_URL + movie.posterPath)
    Column(Modifier.fillMaxWidth().clickable { onClick() }){
        Card(
            modifier = Modifier.height(241.dp), shape = RoundedCornerShape(6.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                Image(
                    contentScale = ContentScale.FillBounds,
                    painter = painter,
                    contentDescription = "picture_of_cat",
                    modifier = Modifier
                        .fillMaxSize(),
                )

                Card(
                    shape = RoundedCornerShape(
                        topStart = 0.dp, bottomEnd = 6.dp, bottomStart = 0.dp, topEnd = 0.dp
                    ), modifier = Modifier.align(Alignment.TopStart), backgroundColor = Color.Gray
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color.White
                        )

                        Text(
                            text = (movie.voteAverage ?: 0).toString(), color = Color.White
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = movie.title ?: "",
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color.Gray.copy(alpha = 0.5f))
                .padding(5.dp).align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}