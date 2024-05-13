package com.example.travelapplibcation.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.view.Gravity
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.travelapplibcation.models.Place
import com.example.travelapplibcation.models.Upload
import com.example.travelapplibcation.navigation.ROUTE_ADD_PLACE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class PlaceViewModel(var navController: NavHostController, var context: Context) {
    var authRepository: AuthViewModel
    var progress: ProgressDialog

    init {
        authRepository = AuthViewModel(navController, context)
//        if (!authRepository.isloggedin()) {
//            navController.navigate(ROUTE_LOGIN)
//        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun viewPlaces(
        place: MutableState<Place>,
        places: SnapshotStateList<Place>
    ): SnapshotStateList<Place> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Places")

        //progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //progress.dismiss()
                places.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Place::class.java)
                    place.value = value!!
                    places.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return places
    }

    fun savePlace(
        name: String,
        description: String,
        location: String,
        price: String
    ) {
        val id = System.currentTimeMillis().toString()
        val placeData = Place(
            name,
            description,
            location,
            price,
            id
        )
        val placeRef = FirebaseDatabase.getInstance().getReference().child("Places/$id")
        progress.show()
        placeRef.setValue(placeData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_ADD_PLACE)
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun savePlaceWithImage(
        name:String,
        description:String,
        location:String,
        price: String,
        filePath: Uri
    ){
        val id = System.currentTimeMillis().toString()
        val storageReference = FirebaseStorage.getInstance().getReference().child("Uploads/$id")
        progress.show()
        storageReference.putFile(filePath).addOnCompleteListener{
            if (name.isBlank() || location.isBlank() || description.isBlank() || price.isBlank()){
                progress.dismiss()
                Toast.makeText(context, "Fill all the fields please", Toast.LENGTH_LONG).apply {
                    setGravity(Gravity.CENTER, 0, 0)
                    show()
                }
                // making a toast appear at the center of screen without using a variable
                navController.navigate(ROUTE_ADD_PLACE)
            } else if (it.isSuccessful){
                progress.dismiss()
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    val imageUrl = it.toString()
                    val houseData = Place(
                        name,
                        description,
                        location,
                        imageUrl,
                        id
                    )
                    val dbRef = FirebaseDatabase.getInstance().getReference().child("Uploads/$id")
                    dbRef.setValue(houseData)
                    val toast = Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    // this is for making a toast centered on screen using variable
                    navController.navigate(ROUTE_ADD_PLACE)
                }
            }else{
                progress.dismiss()
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun viewUploads(
        upload:MutableState<Upload>,
        uploads:SnapshotStateList<Upload>
    ): SnapshotStateList<Upload> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Uploads")
        progress = ProgressDialog(context)
        progress.setTitle("Loading ")
        progress.setMessage("Please wait...")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                uploads.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Upload::class.java)
                    upload.value = value!!
                    uploads.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        }
        )
        return uploads
    }

    fun deletePlaceRecord(id: String) {
        val delRef = FirebaseDatabase.getInstance().getReference().child("Uploads/$id")
//        progress.show()
        delRef.removeValue().addOnCompleteListener {
//            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


}
