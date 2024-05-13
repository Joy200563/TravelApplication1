package com.example.travelapplibcation.models

class Place{
    var name:String=""
    var description:String=""
    var location:String=""
    var price:String=""
    var id: String = ""


    constructor(name:String,description:String,location:String, price:String, id: String){
        this.name=name
        this.description=description
        this.location=location
        this.price=price
        this.id = id



    }
    constructor()
}