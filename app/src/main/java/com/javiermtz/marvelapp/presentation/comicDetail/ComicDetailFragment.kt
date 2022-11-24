package com.javiermtz.marvelapp.presentation.comicDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.javiermtz.marvelapp.R
import com.javiermtz.marvelapp.databinding.FragmentComicDetailBinding
import com.javiermtz.marvelapp.presentation.MainActivity
import com.javiermtz.marvelapp.presentation.detailcharacter.CharacterDetailFragmentArgs
import com.javiermtz.marvelapp.presentation.toLoad

class ComicDetailFragment : Fragment() {

  private lateinit var binding: FragmentComicDetailBinding
  private val args: ComicDetailFragmentArgs by navArgs()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (requireActivity() as MainActivity).supportActionBar?.hide()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    // Inflate the layout for this fragment
    binding = FragmentComicDetailBinding.inflate(inflater, container, false)
    setLayout()
    return binding.root
  }

  private fun setLayout() {

    args.comicData.apply {
      binding.titleComic.text = title
      binding.imageCharacter.toLoad(image)
      binding.descriptionComic.text = description
      binding.pencilerComic.text = creator
      binding.writerComic.text = writer
      binding.priceComic.text = price.toString()
      binding.dateComic.text = datePublisher

    }
  }

}
