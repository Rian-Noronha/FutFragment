package com.rn.futfragment.view

import com.rn.futfragment.model.Player

interface PlayerDetailsView {


    fun showPlayerDetails(player:Player)
    fun errorPlayerNotFound()


}