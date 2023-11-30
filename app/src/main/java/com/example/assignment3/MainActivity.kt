package com.example.assignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Navigate to WelcomeFragment in the initial launch
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, WelcomeFragment())
            .commit()

        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)

        topAppBar.setNavigationOnClickListener { view ->
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.nav_item_welcome -> {
                    // Respond to nav_item_welcome click
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, WelcomeFragment())
                        .commit()
                }
                R.id.nav_item_game -> {
                    // Respond to nav_item_game click
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, GameFragment())
                        .commit()
                }
                R.id.nav_item_high_score -> {
                    // Respond to nav_item_high_score click
                    // Respond to nav_item_game click
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, HighScoreFragment())
                        .commit()
                }
                else -> {
                    Toast.makeText(this.applicationContext, getString(R.string.error_invalid_selection), Toast.LENGTH_SHORT).show()
                    false
                }
            }
            // Handle menu item selected
            menuItem.isChecked = true
            drawerLayout.close()
            true
        }
    }
}