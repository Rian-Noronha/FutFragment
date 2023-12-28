package com.rn.futfragment.model

data class Player(
    var id:Long = 0,
    var name:String = "",
    var scoredGoals:Int = 0,
    var givenAssistence:Int = 0,
    var numberShirt:Int = 0,
    var nacionality:String = "",
    var currentClub:String = "",
    var position:String = "",
    var weight:Float = 0.0F,
    var height:Float = 0.0F,
    var ratingForTheSeason: Float = 0.0F

){
    override fun toString(): String = name
}
