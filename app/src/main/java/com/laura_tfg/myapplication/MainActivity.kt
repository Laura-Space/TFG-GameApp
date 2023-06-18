package com.laura_tfg.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import android.content.res.Configuration
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.laura_tfg.myapplication.activities.CentroActivity
import com.laura_tfg.myapplication.activities.ContactoActivity
import com.laura_tfg.myapplication.activities.JuegoActivity
import com.laura_tfg.myapplication.activities.JuegoRandomActivity
import com.laura_tfg.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        binding.cardView01.setOnClickListener { navigationToJuegos() }
        binding.cardView02.setOnClickListener { navigationToRandom() }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nav_twitter -> {
                val url = "https://twitter.com/FedMAIN?t=1upsMycEzikI0NWwxTEObg&s=09"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            R.id.nav_instagram -> {
                val url = "https://www.instagram.com/federacionmain/?utm_medium=copy_link"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            R.id.nav_facebook -> {
                val url = "https://www.facebook.com/FederacionMAIN/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            R.id.nav_web -> {
                val url = "https://federacionmain.org/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            R.id.nav_centros -> {
                val intent = Intent(this, CentroActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_dev -> {
                val intent = Intent(this, ContactoActivity::class.java)
                startActivity(intent)
            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigationToJuegos() {
        val intent = Intent(this, JuegoActivity::class.java)
        startActivity(intent)
    }

    private fun navigationToRandom() {
        val intent = Intent(this, JuegoRandomActivity::class.java)
        startActivity(intent)
    }
}