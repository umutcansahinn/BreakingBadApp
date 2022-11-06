package com.umutcansahin.breakingbadapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.umutcansahin.breakingbadapp.R
import com.umutcansahin.breakingbadapp.base.BaseFragment
import com.umutcansahin.breakingbadapp.databinding.FragmentDetailBinding
import com.umutcansahin.breakingbadapp.ui.home.HomeFragment
import com.umutcansahin.breakingbadapp.util.loadImage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding,DetailViewModel>(
    FragmentDetailBinding::inflate
) {

    override val viewModel by viewModels<DetailViewModel>()

    private val detailRecyclerAdapter = DetailRecyclerAdapter(arrayListOf())

    private var img: String? = null
    private var name: String? = null
    private var birthday: String? = null
    private var nickName: String? = null
    private var charId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            img = DetailFragmentArgs.fromBundle(it).img
            name = DetailFragmentArgs.fromBundle(it).name
            birthday = DetailFragmentArgs.fromBundle(it).birthday
            nickName = DetailFragmentArgs.fromBundle(it).nickName
            charId = DetailFragmentArgs.fromBundle(it).charId
        }

        binding.detailRecyclerView.adapter = detailRecyclerAdapter
        binding.detailRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getDetail(name!!)

        showToDetail()

        binding.addFavoriteButton.setOnClickListener {
            addFavoriteItem()
            Navigation.findNavController(it).popBackStack()
            Toast.makeText(requireContext(), "ADDED", Toast.LENGTH_LONG).show()
        }
        binding.deleteFavoriteButton.setOnClickListener {
            deleteFavoriteItem()
            Navigation.findNavController(it).popBackStack()
            Toast.makeText(requireContext(), "DELETED", Toast.LENGTH_LONG).show()
        }

        observeEvents()

    }

    private fun showToDetail() {
        binding.detailImageView.loadImage(img)
        binding.detailTextViewName.text = "Name: ${name}"
        binding.detailTextViewBirthday.text = "Birthday: ${birthday}"
        binding.detailTextViewNickName.text = "NickName: ${nickName}"
    }

    private fun addFavoriteItem() {
        viewModel.addRoomDatabase(birthday,img,name,nickName,charId)
    }

    private fun deleteFavoriteItem() {
        viewModel.deleteRoomDatabase(charId!!)
    }

    private fun observeEvents() {
        with(viewModel){
            detailResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    detailRecyclerAdapter.updateList(it)
                    binding.detailProgressBar.visibility = View.GONE
                    binding.detailErrorTextView.visibility = View.GONE
                }
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                it?.let {
                    if (it) {
                        binding.detailRecyclerView.visibility = View.GONE
                        binding.detailProgressBar.visibility = View.VISIBLE
                        binding.detailErrorTextView.visibility = View.GONE

                    }else {
                        binding.detailRecyclerView.visibility = View.VISIBLE
                        binding.detailProgressBar.visibility = View.GONE
                        binding.detailErrorTextView.visibility = View.GONE
                    }
                }
            })
            onError.observe(viewLifecycleOwner, Observer {
                binding.detailErrorTextView.visibility = View.VISIBLE
                binding.detailRecyclerView.visibility = View.GONE
                binding.detailProgressBar.visibility = View.GONE
            })
        }
    }

}