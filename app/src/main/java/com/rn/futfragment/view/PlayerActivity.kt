package com.rn.futfragment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.rn.futfragment.R
import com.rn.futfragment.model.Player

class PlayerActivity : AppCompatActivity(), PlayerListFragment.OnPlayerClickListener, SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    private var lastSearchTerm: String = ""
    private var searchView:SearchView? = null

    private val listFragment:PlayerListFragment by lazy{
        supportFragmentManager.findFragmentById(R.id.fragmentList) as PlayerListFragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putString(EXTRA_SEARCH_TERM, lastSearchTerm)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastSearchTerm = savedInstanceState?.getString(EXTRA_SEARCH_TERM)?:""
    }


    override fun onPlayerClick(player: Player) {
       if(isTablet()){
           showDetailsFragment(player.id)
       }else{
           showDetailsActivity(player.id)
       }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.player, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.setOnActionExpandListener(this)
        searchView = searchItem?.actionView as SearchView
        searchView?.queryHint = getString(R.string.hint_search)
        searchView?.setOnQueryTextListener(this)
        if(lastSearchTerm.isNotEmpty()){
            Handler().post(){
                val query = lastSearchTerm
                searchItem.expandActionView()
                searchView?.setQuery(query, true)
                searchView?.clearFocus()
            }
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun showDetailsActivity(playerId:Long){
        PlayerDetailsActivity.open(this, playerId)
    }

    private fun isTablet() = resources.getBoolean(R.bool.tablet)
    private fun isSmartphone() = resources.getBoolean(R.bool.smartphone)

    private fun showDetailsFragment(playerId: Long){
        searchView?.setOnQueryTextListener(null)
        val fragment = PlayerDetailsFragment.newInstance(playerId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.details, fragment, PlayerDetailsFragment.TAG_DETAILS)
            .commit()
    }

    override fun onQueryTextSubmit(query: String?): Boolean = true

    override fun onQueryTextChange(newText: String?): Boolean {
       lastSearchTerm = newText?:""
        listFragment.search(lastSearchTerm)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem): Boolean = true

    override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
        lastSearchTerm = ""
        listFragment.clearSearch()
        return true
    }


    companion object{
        const val EXTRA_SEARCH_TERM = "lastSearch"
    }

}