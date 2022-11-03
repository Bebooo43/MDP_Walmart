package com.example.walmart.productsactivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.R
import com.example.walmart.databinding.ListItemBinding
import com.example.walmart.models.Product
import com.example.walmart.productdetails.PRODUCT_EXTRA
import com.example.walmart.productdetails.ProductDetailsActivity
import com.example.walmart.utils.getDrawableIdByName
import kotlinx.android.synthetic.main.list_item.view.*

class ProductRVAdapter(private val list: ArrayList<Product>): RecyclerView.Adapter<ProductRVAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       val binding =  ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProductViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product){
            itemView.also {
                it.itemIV.setImageResource(
                    it.context.getDrawableIdByName(product.image)
                )
                it.titleTV.text = product.title
                it.priceTV.text = it.context.getString(R.string.product_price_text, product.price.toString())
                it.colorTV.text = it.context.getString(R.string.product_color_text, product.color)
                it.setOnClickListener { view: View ->
                    openProductDetailsScreen(view.context, product)
                }
            }
        }
    }

    private fun openProductDetailsScreen(context: Context, product: Product) {
        context.startActivity(
            Intent(context, ProductDetailsActivity::class.java).apply {
                putExtra(PRODUCT_EXTRA, product)
            }
        )
    }
}