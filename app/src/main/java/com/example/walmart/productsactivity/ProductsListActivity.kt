package com.example.walmart.productsactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart.R
import com.example.walmart.databinding.ActivityProductsListBinding
import com.example.walmart.models.Product

class ProductsListActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@ProductsListActivity)
            adapter = ProductRVAdapter(electronicsProducts)
        }
    }

}

val electronicsProducts = arrayListOf(
    Product(
        "Pixel 7 Pro",
        899.99,
        "Grey",
        "pixel7",
        "pixel",
        "Google Pixel 7 Pro - 128 GB - Obsidian - Unlocked"
    ),
    Product(
        "MacBook Pro 16 inch Laptop",
        2299.99,
        "Silver",
        "macbook",
        "macbook",
        "Apple M1 Pro chip - 16GB Memory - 1TB SSD (Latest Model 2021)"
    ),
    Product(
        "Pixel Watch",
        399.99,
        "Black",
        "watch",
        "watch",
        "Android Smartwatch with Fitbit Activity Tracking - Heart Rate Tracking Watch"
    ),
    Product(
        "Samsung Galaxy Z Fold 2 5G",
        1259.99,
        "Mystic Bronze",
        "samsung",
        "samsung",
        "Factory Unlocked Android Cell Phone | 256GB Storage"
    ),
    Product(
        "Apple iPhone 13 Pro",
        999.99,
        "Silver",
        "iphone",
        "iphone",
        "128GB, [Locked] + Carrier Subscription"
    )
)