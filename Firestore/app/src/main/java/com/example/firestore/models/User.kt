package com.example.firestore.models

class User {
    var name: String? = null
    var age: Int? = null

    constructor()
    constructor(name: String?, age: Int?) {
        this.name = name
        this.age = age
    }

    override fun toString(): String {
        return "User(name=$name, age=$age)"
    }
}