package com.example.firebasenotepad.view.gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.firebasenotepad.R
import com.example.firebasenotepad.databinding.GalleryFragmentBinding
import com.example.firebasenotepad.view.gallery.tabs.*
import com.example.firebasenotepad.viewmodel.gallery.*

class GalleryFragment:Fragment() {
    private val gameController=GameGalleryController()
    private val bookController=BookGalleryController()
    private val movieController=MovieGalleryController()
    private val songController=SongGalleryController()
    private val tabs=listOf(
        GamesTab(),
        BooksTab(),
        MoviesTab(),
        SongsTab()
    )
    private val tabNames=listOf(
        R.string.tab_games,
        R.string.tab_books,
        R.string.tab_movies,
        R.string.tab_songs
    )
    private val controllers=listOf(
        gameController,
        bookController,
        movieController,
        songController
    )
    private val colors = listOf(
        R.color.gamecolor,
        R.color.bookcolor,
        R.color.moviecolor,
        R.color.songcolor
    )
    private lateinit var tabsAdapter:OldTabsAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("dbg","onCreate(GalleryFragment)")
        val binding = GalleryFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        gameController.apply {
            navigateToEdit.observe(viewLifecycleOwner){
                if(it!=-1){
                    Log.d("dbg","navigating to game edit(${it})")
                    findNavController().navigate(GalleryFragmentDirections.actionGalleryToEditGame(it))
                    resetNavigateToEdit()
                }
            }
        }
        bookController.apply {
            navigateToEdit.observe(viewLifecycleOwner){
                if(it!=-1){
                    Log.d("dbg","navigating to book edit(${it})")
                    findNavController().navigate(GalleryFragmentDirections.actionGalleryToEditBook(it))
                    resetNavigateToEdit()
                }
            }
        }
        movieController.apply {
            navigateToEdit.observe(viewLifecycleOwner){
                if(it!=-1){
                    Log.d("dbg","navigating to movie edit(${it})")
                    findNavController().navigate(GalleryFragmentDirections.actionGalleryToEditMovie(it))
                    resetNavigateToEdit()
                }
            }
        }
        songController.apply {
            navigateToEdit.observe(viewLifecycleOwner){
                if(it!=-1){
                    Log.d("dbg","navigating to song edit(${it})")
                    findNavController().navigate(GalleryFragmentDirections.actionGalleryToEditSong(it))
                    resetNavigateToEdit()
                }
            }
        }


        val factory = GalleryViewModel.Factory(controllers)
        val viewModel = ViewModelProvider(this,factory).get(GalleryViewModel::class.java)


        tabsAdapter = OldTabsAdapter(
            tabs,
            tabNames,
            controllers,
            requireContext(),
            requireActivity().supportFragmentManager
        )
        val viewTabs = binding.tlTabs
        viewPager = binding.vpGalleryPages

        //viewPager.addOnPageChangeListener(TabChangeListener({pageID->viewModel.onTabChanged(pageID)}))

        val fab_add = binding.fabAddEntry

        fun updateViewColor(colorID:Int){
            val newColor = requireContext().resources.getColor(colorID)
            viewTabs.setSelectedTabIndicatorColor(newColor)
            fab_add.setBackgroundColor(newColor)
        }

        viewModel.onTabChanged(0)
        viewPager.addOnPageChangeListener(
            object:ViewPager.OnPageChangeListener{
                override fun onPageSelected(position: Int) {
                    viewModel.onTabChanged(position)
                    updateViewColor(colors[position])
                }

                override fun onPageScrollStateChanged(state: Int) {
                }
                @SuppressLint("MissingSuperCall")
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {}

            }
        )

        viewPager.adapter = tabsAdapter
        viewTabs.setupWithViewPager(viewPager)


        binding.viewModel = viewModel


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewPager.invalidate()
    }
}