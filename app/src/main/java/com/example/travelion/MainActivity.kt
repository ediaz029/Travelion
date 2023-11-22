package com.example.travelion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.travelion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Plan())

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){

                R.id.nav_plan -> replaceFragment(Plan())
                R.id.nav_explore -> replaceFragment(Explore())
                R.id.nav_profile -> replaceFragment(Profile())
                R.id.nav_logs -> replaceFragment(Logs())

                else ->{

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}