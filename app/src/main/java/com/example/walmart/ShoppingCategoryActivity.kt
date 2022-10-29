package com.example.walmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shopping_category.*

class ShoppingCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)

        val user = intent.extras?.getParcelable(USER_EXTRA) as? User
        welcomeTV.text = getString(R.string.welcome_message, user?.userName)

        container1.setOnClickListener {
            showToast(electronicsTV.text.toString())
        }

        container2.setOnClickListener {
            showToast(clothingTV.text.toString())
        }

        container3.setOnClickListener {
            showToast(BeautyTV.text.toString())
        }

        container4.setOnClickListener {
            showToast(foodTV.text.toString())
        }
    }

    private fun showToast(category: String) {
        Toast.makeText(this, "You have chosen the $category category of shopping", Toast.LENGTH_SHORT).show()
    }
}