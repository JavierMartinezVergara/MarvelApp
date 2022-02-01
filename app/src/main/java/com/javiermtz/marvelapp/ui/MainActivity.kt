package com.javiermtz.marvelapp.ui

import android.content.res.Resources.Theme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.R.layout
import com.javiermtz.marvelapp.databinding.ActivityMainBinding
import com.javiermtz.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding : ActivityMainBinding
  private val viewModel : MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setTheme(R.style.Theme_MarvelApp)
    setContentView(binding.root)


  }

}
