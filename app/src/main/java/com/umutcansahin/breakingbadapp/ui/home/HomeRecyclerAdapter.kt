package com.umutcansahin.breakingbadapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.breakingbadapp.databinding.HomeRecyclerRowBinding
import com.umutcansahin.breakingbadapp.model.character.NetworkResponseItem
import com.umutcansahin.breakingbadapp.util.loadImage

class HomeRecyclerAdapter(val list: ArrayList<NetworkResponseItem>): RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    class HomeViewHolder(val binding: HomeRecyclerRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemBinding = HomeRecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return HomeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.binding.rowRecyclerViewImage.loadImage(list[position].img)

        holder.binding.rowRecyclerViewImage.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                list[position].img,
                list[position].name,
                list[position].birthday,
                list[position].nickname,
                list[position].charId!!
            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<NetworkResponseItem>){
        list.addAll(newList)
        notifyDataSetChanged()
    }
}