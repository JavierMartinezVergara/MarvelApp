package com.javiermtz.marvelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.javiermtz.marvelapp.ui.CharacteresViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val viewModel : CharacteresViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    lifecycleScope.launch {
      viewModel.getData()
    }

    viewModel.characteresMarvel.observe(this, {
      Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
    })

  }
}
