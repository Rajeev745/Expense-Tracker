package com.example.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.expensetracker.databinding.ActivityMainBinding
import com.example.expensetracker.fragments.HomeFragment
import com.example.expensetracker.fragments.SettingsFragment


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment(), true)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            val id = it.itemId
            if (id == R.id.settingsFragment) {
                loadFragment(SettingsFragment(), false)
            } else {
                loadFragment(HomeFragment(), false)
            }
        }
    }

    private fun loadFragment(fragment: Fragment, boolean: Boolean): Boolean {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (boolean) {
            fragmentTransaction.add(R.id.fragment_container, fragment).commit()
        } else {
            fragmentTransaction.replace(R.id.fragment_container, fragment).commit()
        }
        return true
    }
}