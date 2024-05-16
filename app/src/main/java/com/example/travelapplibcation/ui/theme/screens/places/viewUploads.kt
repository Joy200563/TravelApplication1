package com.example.travelapplibcation.ui.theme.screens.places

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import coil.compose.rememberAsyncImagePainter
import com.example.travelapplibcation.data.PlaceViewModel
import com.example.travelapplibcation.models.Upload


@Composable
fun ViewUploadsScreen(navController:NavHostController) {

    Column(modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {

        val context = LocalContext.current
        val placeRepository = PlaceViewModel(navController, context)


        val emptyUploadState = remember { mutableStateOf(Upload("","","","","","")) }
        val emptyUploadsListState = remember { mutableStateListOf<Upload>() }

        val uploads = placeRepository.viewUploads(emptyUploadState, emptyUploadsListState)


        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All uploads",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(uploads){
                    UploadItem(
                        name = it.name,
                        description = it.description,
                        location = it.location,
                        price = it.price,
                        imageUrl = it.imageUrl,
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
fun UploadItem(
    name:String,
    description:String,
    location:String,
    price: String,
    imageUrl:String,
    id:String,
    navController:NavHostController,
    placeRepository:PlaceViewModel
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = description)
        Text(text = location)
        Text(text = price)
//        TextField(value = name,
//            onValueChange = {}
//        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxWidth()
        ){
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Row {
                Button(onClick = {
                    placeRepository.deletePlaceRecord(id)
                }
                ) {
                    Text(text = "Delete")
                }
//                Button(onClick = {
//                    navController.navigate("ROUTE_UPDATE_PRODUCT+/$id")
//                }) {
//                    Text(text = "Update")
//                }
            }
        }
    }
}

