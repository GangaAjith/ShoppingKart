package com.gangaown.shoppingkart.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gangaown.shoppingkart.data.repositories.ShoppingRepository

class ViewModelFactory(private val repository: ShoppingRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T: ViewModel?> create (modelclass:Class<T>):T{
        return ShoppingViewModel(repository) as T
    }
}