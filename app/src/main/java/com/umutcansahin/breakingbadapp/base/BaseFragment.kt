package com.umutcansahin.breakingbadapp.base

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment(){

    private var _binding: VB? = null
    protected val binding: VB get() = _binding as VB

    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null) {
            throw IllegalArgumentException("Binding null")
        }
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}