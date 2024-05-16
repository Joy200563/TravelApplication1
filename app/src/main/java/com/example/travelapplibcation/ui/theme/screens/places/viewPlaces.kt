package com.example.travelapplibcation.ui.theme.screens.places

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.travelapplibcation.data.PlaceViewModel
import com.example.travelapplibcation.models.Place
import com.example.travelapplibcation.navigation.ROUTE_ADD_PLACE


@Composable
fun ViewPlaces(navController:NavHostController) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val context = LocalContext.current
        val placeRepository = PlaceViewModel(navController, context)
        val emptyPlaceState = remember { mutableStateOf(Place("","","","", "")) }
        val emptyPlaceListState = remember { mutableStateListOf<Place>() }

        val places = placeRepository.viewPlaces(emptyPlaceState, emptyPlaceListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All places",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(places){
                    ProductItem(
                        name = it.name,
                        description = it.description,
                        location = it.location,
                        price = it.price,
                        id = it.id,
                        navController = navController,
                        placeRepository = placeRepository
                    )
                }
            }
        }
    }

}



@Composable
fun ProductItem(
    name:String,
    description:String,
    location:String,
    price: String,
    id:String,
    navController:NavHostController,
    placeRepository:PlaceViewModel
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = description)
        Text(text = location)
        Text(text = price)
//        Button(onClick = {
//            placeRepository.deleteProduct(id)
//        }) {
//            Text(text = "Delete")
//        }
//        Button(onClick = {
//            navController.navigate(ROUTE_UPDATE_PRODUCT+"/$id")
//        }) {
//            Text(text = "Update")
//        }
    }
    @Composable
    fun Placeitem(name:String, description: String, location: String,price:String, id:String,
                    navController:NavHostController, placeRepository: PlaceViewModel) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = name)
            Text(text = description)
            Text(text = location)
            Text(text = price)
            Button(onClick = {
                placeRepository.deletePlaceRecord(id)
            }) {
                Text(text = "Delete")
            }
            Button(onClick = {
                navController.navigate(ROUTE_ADD_PLACE+"/$id")
            }) {
                Text(text = "Add")
            }
        }

    }

}
@Preview
@Composable
fun view() {
    ViewPlaces(rememberNavController())

}



