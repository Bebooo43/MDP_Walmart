package com.example.walmart.productdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import com.example.walmart.R
import com.example.walmart.databinding.ActivityProductDetailsBinding
import com.example.walmart.models.Product
import com.example.walmart.utils.getDrawableIdByName

const val PRODUCT_EXTRA = "product_extra"

class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.getParcelable<Product>(PRODUCT_EXTRA)?.also {
            bindData(it)
        }
    }

    private fun bindData(product: Product) {
        binding.also {
            it.productIV.setImageResource(
                this.getDrawableIdByName(product.image)
            )

            it.titleTV.text = product.title

            it.colorTV.text = resources.getString(R.string.product_color_text, product.color)

            it.itemIdTV.text =
                SpannableStringBuilder()
                    .bold { append(resources.getString(R.string.product_id_text, product.itemId)) }

            it.itemDescTV.text =
                SpannableStringBuilder()
                    .append(resources.getString(R.string.product_details_desc))
                    .bold { append(product.desc) }

        }

    }

}