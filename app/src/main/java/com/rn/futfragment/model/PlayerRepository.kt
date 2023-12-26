package com.rn.futfragment.model

interface PlayerRepository {

    fun save(player:Player)
    fun remove(vararg players:Player)
    fun playerById(id:Long, callback:(Player?) -> Unit)
    fun search(term:String, callback: (List<Player>) -> Unit)

}