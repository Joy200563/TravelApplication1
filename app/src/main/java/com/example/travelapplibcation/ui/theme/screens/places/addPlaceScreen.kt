package com.example.travelapplibcation.ui.theme.screens.places

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.travelapplibcation.R
import com.example.travelapplibcation.data.PlaceViewModel
import com.example.travelapplibcation.navigation.ROUTE_VIEW_UPLOADS

@Composable
fun AddPlaceScreen(navController: NavHostController){
    Box { Image(
            painter = painterResource(id = R.drawable.elephant2),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        val context = LocalContext.current
        Text(
            text = "Add Place",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var name by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var description by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var location by remember {
            mutableStateOf(TextFieldValue(""))
        }
        var price by remember {
            mutableStateOf(TextFieldValue(""))
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Place name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Place description *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text(text = "Place Location *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text(text = "Place price *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {

            val placeRepository = PlaceViewModel(navController,context)
            placeRepository.savePlace(
                name.text.trim(),
                description.text.trim(),
                location.text.trim(),
                price.text.trim()
            )
//            navController.navigate(ROUTE_VIEW_PLACES)


        }) {
            Text(text = "Save")
        }
        ImagePicker(
            context = context,
            navController = navController,
            name = name.text.trim(),
            description = description.text.trim(),
            location = location.text.trim(),
            price = price.text.trim()
        )
    }
}

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    context: Context,
    navController: NavHostController,
    name: String,
    description: String,
    location: String,
    price: String
) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Selected image",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                }
            ){
                Text(
                    text = "Select Image"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick =  {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                val placeRepository = PlaceViewModel(navController,context)
                placeRepository.savePlaceWithImage(
                    name,
                    description,
                    location,
                    price,
                    imageUri!!
                )
            }) {
                Text(text = "Upload")
            }

            Button(onClick = { navController.navigate(ROUTE_VIEW_UPLOADS) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "View Uploads")
            }
        }
    }
}