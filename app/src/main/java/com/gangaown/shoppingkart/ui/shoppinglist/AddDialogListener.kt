package com.gangaown.shoppingkart.ui.shoppinglist

import com.gangaown.shoppingkart.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)

}