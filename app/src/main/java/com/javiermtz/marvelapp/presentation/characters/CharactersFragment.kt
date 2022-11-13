package com.javiermtz.marvelapp.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.javiermtz.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {
  private lateinit var binding: FragmentCharactersBinding
  private val viewModel: CharacteresViewModel by viewModels()
  private lateinit var adapter: CharacterAdapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentCharactersBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setAdapter()
    observers()

  }

  private fun observers() {

    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.characters.collectLatest {
            adapter.submitList(it)
          }
        }
      }
    }

    /*viewModel.characteresMarvel.observe(viewLifecycleOwner, {
      if (adapter.currentList.isEmpty()) {
        adapter.submitList(it)
      } else {
        val list = adapter.currentList.toMutableList()
        list.addAll(it)
        adapter.submitList(list)
      }
    })
    viewModel.loading.observe(viewLifecycleOwner, {
      if (it) {
        binding.imgLoading.visibility = View.VISIBLE
        binding.imgError.visibility = View.GONE
      } else binding.imgLoading.visibility = View.GONE
    })
    viewModel.error.observe(viewLifecycleOwner, {
      if (!it.isNullOrEmpty()) {
        binding.imgError.visibility = View.VISIBLE
        binding.errorText.text = requireContext().getString(R.string.error_characters, it)
      } else {
        binding.imgError.visibility = View.GONE
        binding.errorText.visibility = View.GONE
      }
    }*/

  }

  private fun setAdapter() {
    adapter = CharacterAdapter { dataCharacter ->
      val action =
        CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(dataCharacter)
      findNavController().navigate(action)
    }
    binding.recyclerViewCharacters.adapter = adapter
    binding.recyclerViewCharacters.layoutManager = setLayoutManayer()
  }

  private fun setLayoutManayer(): LinearLayoutManager {
    return LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
  }
}
