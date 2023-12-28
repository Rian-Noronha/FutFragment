package com.rn.futfragment.presenter

import com.rn.futfragment.model.Player
import com.rn.futfragment.model.PlayerRepository
import com.rn.futfragment.view.PlayerListView

class PlayerListPresenter(

    private val view: PlayerListView,
    private val repository: PlayerRepository


) {

    fun searchPlayers(term:String){
        repository.search(term){players -> view.showPlayers(players)}
    }

    fun showPlayerDetails(player:Player){
        view.showPlayerDetails(player)
    }

}