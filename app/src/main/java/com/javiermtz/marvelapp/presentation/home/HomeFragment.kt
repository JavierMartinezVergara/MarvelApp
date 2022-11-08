package com.javiermtz.marvelapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.javiermtz.marvelapp.databinding.FragmentHomeBinding
import com.javiermtz.marvelapp.presentation.detailcharacter.ComicsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding
  private lateinit var adapterCharacters: CharactersHomeAdapter

  private lateinit var adapterComics: ComicsAdapter
  private val viewModel: HomeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    viewModel.getComics()
    setAdapter()
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.getCharacters.collectLatest {
            adapterCharacters.submitList(it)
          }
        }

        launch {
          viewModel.comics.collectLatest {
            adapterComics.submitList(it)
          }
        }
      }
    }

  }

  private fun setAdapter() {
    adapterCharacters = CharactersHomeAdapter()
    adapterComics = ComicsAdapter{

    }

    binding.comicsRecycler.layoutManager =
      LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    binding.comicsRecycler.adapter = adapterComics
    binding.charatersRecycler.layoutManager =
      LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    binding.charatersRecycler.adapter = adapterCharacters
  }

}
