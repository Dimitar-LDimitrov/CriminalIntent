package com.dimitrov.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimitrov.criminalintent.fragments.CrimeDetailFragment
import com.dimitrov.criminalintent.fragments.CrimeListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val crimeListFragment = CrimeListFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, crimeListFragment)
            .commit()
    }
}