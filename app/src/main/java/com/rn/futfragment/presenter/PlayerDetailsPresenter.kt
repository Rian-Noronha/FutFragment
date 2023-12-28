package com.rn.futfragment.presenter

import com.rn.futfragment.model.PlayerRepository
import com.rn.futfragment.view.PlayerDetailsView

class PlayerDetailsPresenter(

    private val view: PlayerDetailsView,
    private val repository: PlayerRepository

) {

    fun loadPlayerDetails(id:Long){

        repository.playerById(id){ player ->
            if(player != null){
                view.showPlayerDetails(player)
            }else{
                view.errorPlayerNotFound()
            }
        }


    }

}