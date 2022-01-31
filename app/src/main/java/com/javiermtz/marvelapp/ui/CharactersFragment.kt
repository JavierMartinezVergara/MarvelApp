package com.javiermtz.marvelapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.FragmentCharactersBinding
import com.javiermtz.marvelapp.model.local.Character
import com.javiermtz.marvelapp.util.Constans
import dagger.hilt.android.AndroidEntryPoint
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
    observers()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter = CharacterAdapter(CharacterAdapter.OnClickListener { dataCharacter ->
      val characterData = Character(
        id = dataCharacter.id,
        name = dataCharacter.name,
        image = dataCharacter.thumbnail.path + Constans.MARVELDIMENSIONLARGE + dataCharacter.thumbnail.extension,
        description = dataCharacter.description,
        numComics = dataCharacter.comics.available,
        numSeries = dataCharacter.series.available
      )
      val action = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(characterData)
      findNavController().navigate(action)
    })

  }

  private fun observers() {
    viewModel.characteresMarvel.observe(viewLifecycleOwner, {
      val data = it
      adapter.submitList(data)
      binding.recyclerViewCharacters.adapter = adapter
    })
  }
}
