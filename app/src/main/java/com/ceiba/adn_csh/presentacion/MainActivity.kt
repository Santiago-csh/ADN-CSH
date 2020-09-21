package com.ceiba.adn_csh.presentacion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.ActivityMainBinding
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

}