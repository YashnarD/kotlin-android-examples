package com.example.chatappfirebaserealtimedatabase.models

class Message {
    var from: String? = null
    var to: String? = null
    var message: String? = null
    var date: String? = null

    constructor(from: String?, to: String?, message: String?, date: String?) {
        this.from = from
        this.to = to
        this.message = message
        this.date = date
    }

    constructor()
}