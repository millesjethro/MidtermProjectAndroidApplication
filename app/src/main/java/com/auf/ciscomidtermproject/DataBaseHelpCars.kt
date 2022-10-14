package com.auf.ciscomidtermproject

class DataBaseHelpCars {
    var id : Int = 0
    var carName : String = ""
    var carModel : String = ""
    var carPrice : String = ""


    constructor(carN:String, carModel:String, carPrice:String){
        this.carName = carN
        this.carModel = carModel
        this.carPrice = carPrice
    }

    constructor(){

    }
}