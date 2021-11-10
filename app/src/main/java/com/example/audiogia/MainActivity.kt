package com.example.audiogia


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.audiogia.city_repository.CityRepository
import com.example.audiogia.components.CityCard
import com.example.audiogia.model.City
import com.example.audiogia.monument_info.MonumentInfo


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DisplayCities {
                startActivity(MonumentInfo.intent(this,it))
            }

        }
    }


    @Composable
    fun DisplayCities(selectedItem: (City) -> Unit) {

        val cityDisplay = remember { CityRepository.getAllCity }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp,vertical = 8.dp)
        ) {
            items(
                items = cityDisplay,
                itemContent = {
                    CityCard(city = it, selectedItem)
                }
            )
        }
    }


}
