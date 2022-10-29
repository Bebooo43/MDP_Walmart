package com.example.walmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.emailET
import kotlinx.android.synthetic.main.activity_register.passwordET

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerBtn.setOnClickListener {
            getUserFromInputIfValid()?.also{
                setResult(RESULT_OK,
                    Intent().apply {
                        putExtra(USER_EXTRA, it)
                    }
                )
                Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }

    private fun getUserFromInputIfValid(): User? {
        return when {
            firstNameET.text.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter your first name!", Toast.LENGTH_SHORT).show()
                null
            }
            lastNameET.text.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter your last name!", Toast.LENGTH_SHORT).show()
                null
            }
            !isValidEmail(emailET.text.toString())-> {
                Toast.makeText(this, "Please enter your valid email!", Toast.LENGTH_SHORT).show()
                null
            }
            passwordET.text.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter your password!", Toast.LENGTH_SHORT).show()
                null
            }
            else ->
                User(
                    firstNameET.text.toString(),
                    lastNameET.text.toString(),
                    emailET.text.toString(),
                    passwordET.text.toString()
                )
        }
    }

}
