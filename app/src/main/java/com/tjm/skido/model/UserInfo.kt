package com.tjm.skido.model

class UserInfo {
    var image:String?=null
    var name:String?=null
    var email:String?=null
    var password:String?=null
    var bio:String?=null

    constructor(image: String?, name: String?, email: String?, password: String?) {
        this.image = image
        this.name = name
        this.email = email
        this.password = password
    }

    constructor(name: String?, email: String?, password: String?) {
        this.name = name
        this.email = email
        this.password = password
    }
    constructor()

    constructor(email: String?, password: String?) {
        this.email = email
        this.password = password
    }


}