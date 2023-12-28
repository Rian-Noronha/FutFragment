package com.rn.futfragment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rn.futfragment.R
import com.rn.futfragment.databinding.FragmentPlayerDetailsBinding
import com.rn.futfragment.model.MemoryRepository
import com.rn.futfragment.model.Player
import com.rn.futfragment.presenter.PlayerDetailsPresenter

class PlayerDetailsFragment : Fragment(), PlayerDetailsView {

    private lateinit var binding:FragmentPlayerDetailsBinding
    private val presenter = PlayerDetailsPresenter(this, MemoryRepository)
    private var player:Player? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadPlayerDetails(arguments?.getLong(EXTRA_PLAYER_ID, -1)?:-1)
    }

    override fun showPlayerDetails(player: Player) {
       this.player = player

        binding.txtName.text = player.name
        binding.txtScoredGoals.text = player.scoredGoals.toString()
        binding.rtbRatingForTheSeason.rating = player.ratingForTheSeason


    }

    override fun errorPlayerNotFound() {

        binding.txtName.text = getString(R.string.error_player_not_found)
        binding.txtScoredGoals.visibility = View.GONE
        binding.rtbRatingForTheSeason.visibility = View.GONE

    }

    companion object{
        const val TAG_DETAILS = "tagDetalhe"
        private const val EXTRA_PLAYER_ID = "playerId"

        fun newInstance(id:Long) =
            PlayerDetailsFragment().apply {
                arguments = Bundle().apply { //By arguments I use the values of Bundle and the View functionality is created.
                    putLong(EXTRA_PLAYER_ID, id)
                }
            }

    }


}