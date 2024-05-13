package com.example.travelapplibcation.models

class Upload {
    var name: String = ""
    var description: String = ""
    var location: String = ""
    var price: String = ""
    var imageUrl: String = ""
    var id: String = ""

    constructor(
        name: String,
        description: String,
        location: String,
        price: String,
        imageUrl: String,
        id: String
    ){
        this.name = name
        this.description = description
        this.location = location
        this.price = price
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()
}