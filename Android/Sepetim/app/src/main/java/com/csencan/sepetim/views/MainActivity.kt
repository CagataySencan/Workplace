package com.csencan.sepetim.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.csencan.sepetim.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val homeScreenFragment = HomeScreenFragment()
    private val favoritesFragment = FavoritesFragment()
    private val accountFragment = AccountFragment()
    private lateinit var bottomNavigationView : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.main_bottom_menu)
        if (savedInstanceState == null) {
            inflateFragment(homeScreenFragment)
        }
        initChangeMenuItem()
    }

    private fun inflateFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .commit()
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }

    private fun initChangeMenuItem() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    changeFragment(homeScreenFragment)
                    true
                }
                R.id.favorites_menu -> {
                    changeFragment(favoritesFragment)
                    true
                }
                R.id.account_menu -> {
                    // TODO Loggedin ise ikon değişecek ve burada logine gidecek
                    changeFragment(accountFragment)
                    true
                }
                else -> false
            }
        }
    }
}