package com.example.audiogia.monument_info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.audiogia.R
import com.example.audiogia.model.City
import com.example.audiogia.text_to_speech.TextToSpeech

class MonumentInfo : ComponentActivity() {

    val tts: TextToSpeech = TextToSpeech()


    companion object{
        private const val CITYID = "cityID"
        fun intent(context: Context, cityIntent: City)=
            Intent(context,MonumentInfo::class.java).apply {
                putExtra(CITYID,cityIntent)
            }
    }

    private val city : City by lazy {
        intent?.getSerializableExtra(CITYID) as City
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonumentInfo(city = city)

            Log.d("=====> OnCreate", city.description.toString())
            tts.speakAux(city.description.toString(),this)
            tts.onInit(1)

        }
    }
}

@Composable
fun MonumentInfo(city: City) {
    val scrollState = rememberScrollState()

    Scaffold(bottomBar = {BottomBar()}
    ) {
        Box(
            modifier = Modifier.padding(
                start = 10.dp,
                bottom = 50.dp,
                end = 10.dp,
                top = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.warning),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp))
                        .requiredHeight(225.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(16.dp))
                city.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h3
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                city.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Justify
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    var isPlaying by remember { mutableStateOf(false) }
    val image = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow
    val playOrStop = if (isPlaying) "Pause" else "Play"

    BottomNavigation(elevation = 10.dp) {
        BottomNavigationItem(icon = {
            Icon(imageVector = image,"")
        },
            label = { Text(text = playOrStop) },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
                isPlaying = !isPlaying
            }

        )






        // EJEMPLO PARA AÑADIR MÁS BOTONES A LA  SCAFFOLD TOPBAR
        /*
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.*,"")
        },
            label = { Text(text = "") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.*,"")
        },
            label = { Text(text = "") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })

         */
    }
}