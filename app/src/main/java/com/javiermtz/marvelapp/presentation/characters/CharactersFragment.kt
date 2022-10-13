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
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.FragmentCharactersBinding
import com.javiermtz.marvelapp.model.local.Character
import com.javiermtz.marvelapp.presentation.characters.CharacterAdapter.OnClickListener
import com.javiermtz.marvelapp.presentation.characters.CharacterAdapter.OnClickListener2
import com.javiermtz.marvelapp.util.Constans
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
    binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
        launch {
          viewModel.getCharacters.collectLatest {
            Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_LONG).show()
          }
        }
      }
    }
    observers()
    adapter = CharacterAdapter(OnClickListener { dataCharacter ->
      val characterData = Character(
        id = dataCharacter.id,
        name = dataCharacter.name,
        image = dataCharacter.thumbnail.path + Constans.MARVELDIMENSIONLARGE + dataCharacter.thumbnail.extension,
        description = dataCharacter.description,
        numComics = dataCharacter.comics.available,
        numSeries = dataCharacter.series.available,
        url = dataCharacter.urls.firstOrNull()?.url ?: ""
      )
      val action =
        CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(characterData)
      findNavController().navigate(action)
    }, OnClickListener2 {
      viewModel.getCharacters(viewModel.limite)
      viewModel.loading()
    })
    binding.recyclerViewCharacters.adapter = adapter

  }

  private fun observers() {


    viewModel.characteresMarvel.observe(viewLifecycleOwner, {
      if(adapter.currentList.isEmpty()){
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
    })
  }
}
