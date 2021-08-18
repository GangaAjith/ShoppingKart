package com.gangaown.shoppingkart.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.gangaown.shoppingkart.data.db.ShoppingDatabase
import com.gangaown.shoppingkart.data.db.entities.ShoppingItem
import com.gangaown.shoppingkart.data.repositories.ShoppingRepository
import com.gangaown.shoppingkart.databinding.ActivityShoppingkartBinding
import com.gangaown.shoppingkart.other.ShoppingItemAdapter
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ShoppingKartActivity : AppCompatActivity(),KodeinAware {


    override val kodein by kodein()
        private val factory:ViewModelFactory by instance()

    private lateinit var binding:ActivityShoppingkartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityShoppingkartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel = ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(),viewModel)
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter
        viewModel.getAllShoppingItems().observe(this, Observer {

            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener{
            AddShoppingItemDialog (this, object:AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }


}