package ru.kpfu.itis.springpractice.experiment.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.kpfu.itis.springpractice.experiment.AdventurerApp
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.presentation.extention.hide
import ru.kpfu.itis.springpractice.experiment.presentation.extention.show
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.AuthCheckViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.AuthCheckViewModelFactory

class MainActivity : AppCompatActivity() {

    val navHostFragment: Int = R.id.nav_host_fragment_container

    private val viewModel: AuthCheckViewModel by viewModels {
        val app = application as AdventurerApp
        AuthCheckViewModelFactory(
            app.checkAuthorizationUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        viewModel.isAuthorized.observe(this) { isAuthorized ->
            val graph = navController.navInflater.inflate(
                if (!isAuthorized) R.navigation.login_nav_graph
                else R.navigation.main_nav_graph
            )
            navController.graph = graph

            if (isAuthorized) {
                setBottomNav(navController)
            }
        }

        viewModel.checkAuthorization()
    }

    fun setToMainNavGraph() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val graph = navController.navInflater.inflate(
            R.navigation.main_nav_graph
        )
        navController.graph = graph
        setBottomNav(navController)
    }

    fun setToLoginNavGraph() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val graph = navController.navInflater.inflate(
            R.navigation.login_nav_graph
        )
        navController.graph = graph
        hideBottomNav()
    }

    fun setToLoggedInState() {
        setToMainNavGraph()
    }

    fun setToLoggedOutState() {
        setToLoginNavGraph()
    }

    private fun setBottomNav(navController: NavController) {
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
        bottomNav.show()
    }

    private fun hideBottomNav() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.hide()
    }
}
