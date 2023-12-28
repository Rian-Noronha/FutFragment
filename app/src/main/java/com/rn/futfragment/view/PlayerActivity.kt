package com.rn.futfragment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rn.futfragment.R
import com.rn.futfragment.model.Player

class PlayerActivity : AppCompatActivity(), PlayerListFragment.OnPlayerClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
    }

    override fun onPlayerClick(player: Player) {
        showDetailsActivity(player.id)

    }

    private fun showDetailsActivity(playerId:Long){
        PlayerDetailsActivity.open(this, playerId)
    }
}