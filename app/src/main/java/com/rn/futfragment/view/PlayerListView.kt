package com.rn.futfragment.view

import com.rn.futfragment.model.Player

interface PlayerListView {

    fun showPlayers(players: List<Player>)
    fun showPlayerDetails(player: Player)

}