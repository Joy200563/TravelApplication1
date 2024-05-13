//package com.example.travelapplibcation.screens.places
//
//
//
//
//import android.content.Context
//import android.net.Uri
//import android.provider.MediaStore
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.travelapplibcation.data.PlaceViewModel
//import com.example.travelapplibcation.models.Place
//import com.example.travelapplibcation.navigation.ViewPlaceScreen
//
////import com.example.travelapplibcation.navigation.ROUTE_UPDATE_PRODUCT
////import androidx.activity.result.contract.ActivityResultContracts
////import androidx.compose.foundation.Image
////import androidx.compose.foundation.layout.*
////import androidx.compose.material.Button
////import androidx.compose.material3.Button
////import androidx.compose.runtime.*
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.asImageBitmap
////import androidx.compose.ui.platform.LocalContext
////import androidx.compose.ui.res.painterResource
////import androidx.compose.ui.tooling.preview.Preview
////import androidx.compose.ui.unit.dp
////import com.example.travelapplibcation.R
////import com.google.auto.glide.rememberGlidePainter
//
//
//
//
//@Composable
//fun ViewPlacesScreen(navController:NavHostController) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        val places = listOf(
//            Place(
//                name = "Nairobi National Park",
//                description = "Nairobi National Park is a national park in Kenya that is located just 7 kilometers south of Nairobi, Kenya's capital city.",
//                location = "Langata Rd, Nairobi, Kenya",
//                price = "Entrance fee: KES 430 (Adult), KES 215 (Child)",
//                id =""
//            ),
//            Place(
//                name = "Maasai Mara National Reserve",
//                description = "Maasai Mara National Reserve is one of the most popular tourism destinations in Kenya, known for its exceptional population of lions, leopards, and cheetahs.",
//                location = "Narok County, Kenya",
//                price = "Varies based on accommodation and activities",
//                id =""
//            ),
//            Place(
//                name = "Amboseli National Park",
//                description = "Amboseli National Park is a national park in Kenya that is well known for large number of elephnats and view of Mt Kilimanjaro in Tanzania",
//                location = "southern Kenya, Kajiado County",
//                price = "Entrance fee: KES 600 (Adult), KES 215 (Child) for citizens and residents. Non-residents: (Adult), 40US$ (Child), 20US$",
//                id =""
//            ),
//            Place(
//                name = "Tsavo East National Park",
//                description = "Tsavo National Park is well known for sprawling wildlife in Kenya",
//                location = "Near Voi town in Taita Taveta County",
//                price = "Entrance fee: KES 515 (Adult), KES 215 (Child) for citizens and residents, 52US$(Adult), 35US$(Child) for non residents",
//                id =""
//            ),
//            Place(
//                name = "Lake Nakuru and Lake Nakuru National Park",
//                description = "Lake Nakuru is well known for flamingos while the national park is well known for bird watching , hiking , picnic and game drives",
//                location = "Nakuru County ",
//                price = "Entrance fee: KES 860 (Adult), KES 215 (Child) for citizens and residents, 60US$(Adult), 35US$(Child) for non residents",
//                id =""
//            ),
//            Place(
//                name = "Hells Gate National Park",
//                description = "Hells Gate National park is well known for Savannah ecosystem with rock climbing ",
//                location = "Nakuru County South of Lake Naivasha",
//                price = "Entrance fee: KES 300 (Adult), KES 215 (Child) for citizens and residents, 26US$(Adult), 3517US$(Child) for non residents",
//                id =""
//            ),
//            Place(
//                name = "Samburu  National Park",
//                description = " Conservation area teeming with wildlife its near the banks of Ewaso Ngiiiro river and on the other side of the river there is the Bufallo Springs National Reserve ",
//                location = "Near Voi town in Taita Taveta County",
//                price = "Entrance fee: KES 500 (Adult), KES 215 (Child) for citizens and residents, 70US$(Adult), 40US$(Child) for non residents",
//                id =""
//            ),
//            Place(
//                name = "Aberdare National Park",
//                description = " Aberdare National park having the green vegetation attraction is applicable form game viewing, hiking, bird watching, camping, fishing and pickining. it is also the source of river Ewaso Ngiiro",
//                location = "It si loacted in Nyeri County and Nyandarua County",
//                price = "Entrance fee: KES 300 (Adult), KES 215 (Child) for citizens and residents, 52US$(Adult), 26US$(Child) for non residents",
//                id =""
//            ),
//            Place(
//                name = "Nairobi National Museum",
//                description = "Nairobi National Museum runs under 4 pillars which i",
//                location = "Near Voi town in Taita Taveta County",
//                price = "Entrance fee: KES 500 (Adult), KES 215 (Child) for citizens and residents, 70US$(Adult), 40US$(Child) for non residents",
//                id =""
//            )
//        )
//
//        val context = LocalContext.current
//        val placeRepository = PlaceViewModel(navController, context)
//        val emptyPlaceState = remember { mutableStateOf(Place("", "", "", "","")) }
//        val emptyPlacesListState = remember { mutableStateListOf<Place>() }
//
//        val place = placeRepository.viewPlaces(emptyPlaceState, emptyPlacesListState)
//
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "All places",
//                fontSize = 30.sp,
//                fontFamily = FontFamily.Cursive,
//                color = Color.Red
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            LazyColumn() {
//               items(places){
//                   PlaceItem(place = Place())
//               }
//            }
//        }
//    }
//
//
//// Sample list of places
//
//        // Add more places as needed)
//}
//
//@Composable
//fun PlaceItem(place: Place) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = Color.White)
//            .padding(16.dp)
//    ) {
//        Text(text = place.name)
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(text = place.description)
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(text = "Location: ${place.location}")
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(text = "Price: ${place.price}")
//    }
//}
//@Composable
//fun ImagePicker(
//    modifier: Modifier = Modifier,
//    context: Context,
//    navController: NavHostController,
////        roomName: String,
////        roomType: String,
////        roomPrice: String
//) {
//    var hasImage by remember { mutableStateOf(false) }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//
//    val imagePicker = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent(),
//        onResult = { uri ->
//            hasImage = uri != null
//            imageUri = uri
//        }
//    )
//
//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        if (hasImage && imageUri != null) {
//            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
//            Image(
//                bitmap = bitmap.asImageBitmap(),
//                contentDescription = "Selected image",
//                modifier = Modifier.padding(
//                    start = 20.dp,
//                    end = 20.dp,
//                    top = 0.dp,
//                    bottom = 0.dp
//                )
//            )
//        }
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 10.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Button(
//                onClick = {
//                    imagePicker.launch("image/*")
//                }
//            ) {
//                Text(
//                    text = "Select Image"
//                )
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewViewPlaceScreen() {
//    ViewPlaceScreen(rememberNavController())
//}
//
//
//
//
