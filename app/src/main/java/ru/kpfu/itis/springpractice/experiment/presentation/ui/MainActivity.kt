package ru.kpfu.itis.springpractice.experiment.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.kpfu.itis.springpractice.experiment.R

class MainActivity : AppCompatActivity() {

    val fragmentContainerId: Int = R.id.fragment_container


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(fragmentContainerId) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.setupWithNavController(navController)

        bottomNav.setOnItemReselectedListener { item ->
            if (item.itemId == R.id.notes_fragment) {
                navController.popBackStack(R.id.notes_fragment, false)
            }
        }

        bottomNav.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController)
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
