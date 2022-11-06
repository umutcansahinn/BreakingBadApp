package com.umutcansahin.breakingbadapp.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.umutcansahin.breakingbadapp.util.loadImage

class CoinBinding {

    companion object {

        fun loadImage(imageView: ImageView,coinImage: String){
            imageView.loadImage(coinImage)
        }
    }
}