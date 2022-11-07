package com.example.passportnew.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

class User {

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null



    var name:String?=null
    var surname:String?=null
    var data:String?=null
    var imagePath:String?=null
    var seriya:String?=null

    constructor(name: String?, surname: String?, data: String?, imagePath: String?, seriya: String?) {
        this.name = name
        this.surname = surname
        this.data = data
        this.imagePath = imagePath
        this.seriya = seriya
    }

    override fun toString(): String {
        return "User(id=$id, name=$name, surname=$surname, data=$data, imagePath=$imagePath, seriya=$seriya)"
    }


}