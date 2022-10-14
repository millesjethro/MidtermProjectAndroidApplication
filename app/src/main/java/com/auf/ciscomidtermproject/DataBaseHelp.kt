package com.auf.ciscomidtermproject

import com.auf.ciscomidtermproject.Fragments.Settings

class DataBaseHelp{
    var id : Int = 0
    var Name : String = ""
    var Email : String = ""
    var Password : String = ""
    var age : Int = 0


    constructor(name:String, email:String, password:String, age: Int){
        this.Name = name
        this.Password = password
        this.Email = email
        this.age = age
    }

    constructor(){

    }

}