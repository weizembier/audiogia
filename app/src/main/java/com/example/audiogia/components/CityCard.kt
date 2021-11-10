package com.example.audiogia.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.audiogia.R
import com.example.audiogia.model.City


@Composable
fun CityCard(city: City, selectedItem: (City) -> Unit, ){
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .clickable{ selectedItem(city) },
        elevation = 8.dp,
    ) {

        Column() {
            city.featuredImage.let { url ->
                Image(
                    painter = painterResource (id = R.drawable.warning),
                    contentDescription = "",
                    modifier = Modifier
                        .requiredHeight(225.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
            }
            city.title?.let { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=12.dp, bottom=12.dp, start = 8.dp, end=8.dp)
                ){
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start)
                        ,
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }
    }
}