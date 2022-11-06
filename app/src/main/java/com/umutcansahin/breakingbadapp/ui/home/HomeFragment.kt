package com.umutcansahin.breakingbadapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.umutcansahin.breakingbadapp.base.BaseFragment
import com.umutcansahin.breakingbadapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(
    FragmentHomeBinding:: inflate
) {
    private val homeRecyclerViewAdapter = HomeRecyclerAdapter(arrayListOf())

    override val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeRecyclerView.adapter = homeRecyclerViewAdapter
        binding.homeRecyclerView.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)

        observeEvents()
    }

    private fun observeEvents() {
        with(viewModel) {
            breakingBadResponse.observe(viewLifecycleOwner, Observer { list->
                list?.let {
                    homeRecyclerViewAdapter.updateList(it)
                    binding.homeProgressBar.visibility = View.GONE
                }
            })
            isLoading.observe(viewLifecycleOwner, Observer { value ->
               value?.let {
                   if (it) {
                       binding.homeRecyclerView.visibility = View.GONE
                       binding.homeProgressBar.visibility = View.VISIBLE
                   }else {
                       binding.homeRecyclerView.visibility = View.VISIBLE
                       binding.homeProgressBar.visibility = View.GONE
                   }
               }
            })
            onError.observe(viewLifecycleOwner, Observer { value->
                value?.let {
                    binding.homeRecyclerView.visibility = View.GONE
                    binding.homeProgressBar.visibility = View.GONE
                }
            })
        }
    }
}