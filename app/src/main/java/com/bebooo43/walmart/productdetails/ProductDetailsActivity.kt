package com.bebooo43.walmart.productdetails

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import com.bebooo43.walmart.R
import com.bebooo43.walmart.databinding.ActivityProductDetailsBinding
import com.bebooo43.walmart.models.Product
import com.bebooo43.walmart.utils.getDrawableIdByName

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