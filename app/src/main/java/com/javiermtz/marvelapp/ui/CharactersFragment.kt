package com.javiermtz.marvelapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharactersFragment : Fragment() {
  private lateinit var binding : FragmentCharactersBinding
  private val viewModel : CharacteresViewModel by viewModels()
  private lateinit var adapter: CharacterAdapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentCharactersBinding.inflate(layoutInflater,container,false)
    adapter = CharacterAdapter()
    observers()
    return binding.root
  }


  private fun observers(){
    lifecycleScope.launch {
      viewModel.getData()
    }

    viewModel.characteresMarvel.observe(viewLifecycleOwner, {
      val data = it
      adapter.submitList(data)
      binding.recyclerViewCharacters.adapter = adapter
    })
  }
}
