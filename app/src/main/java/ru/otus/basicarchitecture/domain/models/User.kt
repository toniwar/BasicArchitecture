package ru.otus.basicarchitecture.domain.models

class User(
    var name:String,
    var surname:String,
    var age:Int,
    var country:String,
    var city:String,
    var address:String,
    var hobby:UserHobby
)