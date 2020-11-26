package com.udacity.shoestore.screens.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    private val _shoes =  MutableLiveData<MutableList<Shoe>>()
    val shoes : LiveData<MutableList<Shoe>>
        get() = _shoes

    init {
        _shoes.value = mutableListOf(
            Shoe("Flex", 44.5, "Nike", "A comfortable shoe..."),
            Shoe("Training", 42.0, "Nike", "A training shoe...")
        )
    }

    fun addNewShoe(s: Shoe) {
        _shoes.value?.add(s)
    }
}