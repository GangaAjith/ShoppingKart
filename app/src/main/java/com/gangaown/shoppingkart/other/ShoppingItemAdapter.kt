package com.gangaown.shoppingkart.other

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.gangaown.shoppingkart.R
import com.gangaown.shoppingkart.data.db.entities.ShoppingItem
import com.gangaown.shoppingkart.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(var items:List<ShoppingItem>,
                          private val viewModel:ShoppingViewModel
                      ): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    inner class ShoppingViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvName: TextView
        val tvAmount:TextView
        val ivDelete:ImageView
        val ivPlus:ImageView
        val ivMinus:ImageView

        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvAmount = itemView.findViewById(R.id.tvAmount)
            ivDelete = itemView.findViewById(R.id.ivDelete)
            ivPlus = itemView.findViewById(R.id.ivPlus)
            ivMinus = itemView.findViewById(R.id.ivMinus)
        }
        fun setData(curShoppingItem:ShoppingItem?,position: Int){
            tvName.text  = curShoppingItem!!.name
            tvAmount.text = curShoppingItem.amount.toString()
            ivDelete.setOnClickListener{
                viewModel.delete(curShoppingItem)
            }
            ivPlus.setOnClickListener{
                curShoppingItem.amount++
                viewModel.upsert(curShoppingItem)
            }

            ivMinus.setOnClickListener{
                if(curShoppingItem.amount>0)
                    curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        holder.setData(curShoppingItem,position)

    }

    override fun getItemCount(): Int {
        return items.size
    }
}