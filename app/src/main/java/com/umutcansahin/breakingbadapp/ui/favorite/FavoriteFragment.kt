package com.umutcansahin.breakingbadapp.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.umutcansahin.breakingbadapp.base.BaseFragment
import com.umutcansahin.breakingbadapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding,FavoriteViewModel>(
    FragmentFavoriteBinding::inflate
) {
    override val viewModel by viewModels<FavoriteViewModel>()

    private val favoriteRecyclerViewAdapter = FavoriteRecyclerAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllDataFromRoom()

        binding.favoriteRecyclerView.adapter = favoriteRecyclerViewAdapter
        binding.favoriteRecyclerView.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)

        observeEvents()
    }

    private fun observeEvents(){

        viewModel.favoriteData.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNotEmpty()){
                    binding.favoriteRecyclerView.visibility = View.VISIBLE
                    favoriteRecyclerViewAdapter.updateList(it)
                    binding.favoriteEmptyTextView.visibility = View.GONE
                }else {
                    binding.favoriteEmptyTextView.visibility = View.VISIBLE
                    binding.favoriteRecyclerView.visibility = View.GONE
                }

            }
        })

    }

}