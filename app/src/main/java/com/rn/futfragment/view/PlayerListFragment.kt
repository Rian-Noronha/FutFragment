package com.rn.futfragment.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import com.rn.futfragment.model.MemoryRepository
import com.rn.futfragment.model.Player
import com.rn.futfragment.presenter.PlayerListPresenter

class PlayerListFragment : ListFragment(), PlayerListView{

    private val presenter = PlayerListPresenter(this, MemoryRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchPlayers("")
    }


    override fun showPlayers(players: List<Player>) {
        val adapter = ArrayAdapter<Player>(requireContext(), android.R.layout.simple_list_item_1, players)
        listAdapter = adapter
    }

    override fun showPlayerDetails(player: Player) {
       if(activity is OnPlayerClickListener){
           val listener = activity as OnPlayerClickListener
           listener.onPlayerClick(player)
       }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val player = l?.getItemAtPosition(position) as Player
        presenter.showPlayerDetails(player)
    }

    interface OnPlayerClickListener{
        fun onPlayerClick(player:Player)
    }


}