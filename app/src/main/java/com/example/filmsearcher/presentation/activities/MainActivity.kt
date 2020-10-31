package com.example.filmsearcher.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.filmsearcher.NotificationReceiver
import com.example.filmsearcher.R
import com.example.filmsearcher.presentation.fragments.RemindFragment
import com.example.filmsearcher.presentation.fragments.AllFragment
import com.example.filmsearcher.presentation.fragments.FiltersFragment
import com.example.filmsearcher.presentation.fragments.LikedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    companion object{
        const val ID = "ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

/*        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()*/

        supportFragmentManager.commit {
            add<AllFragment>(R.id.container_frame)
            addToBackStack(null)
        }

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_all -> {
                    supportFragmentManager.commit {
                        replace<AllFragment>(R.id.container_frame)
                    }
                }
                R.id.navigation_add -> {
                    supportFragmentManager.commit {
                        replace<RemindFragment>(R.id.container_frame)
                    }
                }
/*              R.id.navigation_filter ->{
                    supportFragmentManager.commit {
                        replace<FiltersFragment>(R.id.container_frame)

                    }
                }*/
                R.id.navigation_liked -> {
                    supportFragmentManager.commit {
                        replace<LikedFragment>(R.id.container_frame)
                    }
                }
            }
            true
        }


/*        nav_view.setNavigationItemSelectedListener{
            when (it.itemId){
                R.id.nav_main -> {
                    supportFragmentManager.commit {
                        replace<AllFragment>(R.id.container_frame)
                    }
                }
                R.id.nav_about_app ->{}
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }*/

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.navigation_filter -> {
                supportFragmentManager.commit {
                    replace<FiltersFragment>(R.id.container_frame)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
