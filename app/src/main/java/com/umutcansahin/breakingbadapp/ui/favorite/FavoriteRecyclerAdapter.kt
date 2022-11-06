package com.umutcansahin.breakingbadapp.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.breakingbadapp.databinding.FavoriteRecyclerRowBinding
import com.umutcansahin.breakingbadapp.db.Favorite
import com.umutcansahin.breakingbadapp.ui.home.HomeFragmentDirections
import com.umutcansahin.breakingbadapp.util.loadImage

class FavoriteRecyclerAdapter(val list: ArrayList<Favorite>): RecyclerView.Adapter<FavoriteRecyclerAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(val binding: FavoriteRecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemBinding = FavoriteRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

            holder.binding.rowRecyclerViewImage.loadImage(list[position].img)

        holder.binding.rowRecyclerViewImage.setOnClickListener {

            val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(
                list[position].img,
                list[position].name,
                list[position].birthday,
                list[position].nickName,
                list[position].charId!!
            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<Favorite>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()

    }
}