package com.rn.futfragment.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rn.futfragment.R
import com.rn.futfragment.databinding.ActivityPlayerDetailsBinding

class PlayerDetailsActivity : AppCompatActivity() {
    private val playerId:Long by lazy { intent.getLongExtra(EXTRA_PLAYER_ID, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)

        if(savedInstanceState == null){
            showPlayerDetailsFragment()
        }
    }

    private fun showPlayerDetailsFragment(){
        val fragment = PlayerDetailsFragment.newInstance(playerId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.details, fragment, PlayerDetailsFragment.TAG_DETAILS)//the fragment is added to FrameLayout with id @+id/details
            .commit()
    }


    companion object{
        private const val EXTRA_PLAYER_ID = "player_id"
        fun open(context:Context, playerId:Long){
            context.startActivity(Intent(context, PlayerDetailsActivity::class.java).apply {
                putExtra(EXTRA_PLAYER_ID, playerId)
            })
        }

    }

}