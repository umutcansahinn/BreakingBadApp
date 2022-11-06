package com.umutcansahin.breakingbadapp.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.breakingbadapp.databinding.DetailReyclerRowBinding
import com.umutcansahin.breakingbadapp.model.quote.NetworkResponseDetailItem

class DetailRecyclerAdapter(val list: ArrayList<NetworkResponseDetailItem>): RecyclerView.Adapter<DetailRecyclerAdapter.DetailViewHolder>() {

    class DetailViewHolder(val binding: DetailReyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val itemBinding = DetailReyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {

        holder.binding.detailRecyclerTextView.text = list[position].quote

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<NetworkResponseDetailItem>) {
        list.addAll(newList)
        notifyDataSetChanged()

    }
}