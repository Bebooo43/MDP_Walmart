package com.bebooo43.walmart

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bebooo43.walmart.models.User
import com.bebooo43.walmart.utils.isValidEmail
import kotlinx.android.synthetic.main.activity_main.*

const val USER_EXTRA = "user"

class MainActivity : AppCompatActivity() {

    private val usersList = arrayListOf(
        User("user1", "user1Last", "username1@gmail.com", "password1"),
        User("user2", "user2Last", "username2@gmail.com", "password2"),
        User("user3", "user3Last", "username3@gmail.com", "password3"),
        User("user4", "user4Last", "username4@gmail.com", "password4"),
        User("user", "last", "user@gmail.com", "pass")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInBtn.setOnClickListener {
            if (isInputsValid()) {
                val user = authenticate()
                if (user != null)
                    openShoppingCategory(user)
                else
                    showAuthError()
            }
        }

        passwordET.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    signInBtn.performClick()
                    true
                }
                else -> false
            }
        }

        signUpBtn.setOnClickListener {
            openRegisterActivity()
        }

        forgotPasswordBtn.setOnClickListener {
            emailET.text?.toString()?.also { email ->
                if(email.isValidEmail()) {
                    getRegisteredUser(email)?.also { user ->
                        sendEmail(
                            arrayListOf(email),
                            "Forgot Password",
                            "This is your password: ${user.password}"
                        )
                    } ?: run {
                        Toast.makeText(this, "Email is not registered!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Email is not valid!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                result.data?.getParcelableExtra<User>(USER_EXTRA)?.also { user ->
                    usersList.add(user)
                }
            }
        }
    }

    private fun openRegisterActivity() {
        startForResult.launch(Intent(this, RegisterActivity::class.java))
    }

    private fun openShoppingCategory(user: User) {
        startActivity(
            Intent(this, ShoppingCategoryActivity::class.java).apply {
                putExtra(USER_EXTRA, user)
            }
        )
    }

    private fun showAuthError() {
        Toast.makeText(this, "Email and Password does not match !", Toast.LENGTH_SHORT).show()
    }

    private fun authenticate(): User? =
        usersList.firstOrNull {
            it.userName.equals(emailET.text.toString(), true) && it.password == passwordET.text.toString()
        }

    private fun getRegisteredUser(email: String) =
        usersList.firstOrNull { it.userName.equals(email, true) }

    private fun isInputsValid() =
        when {
            emailET.text.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter your email!", Toast.LENGTH_SHORT).show()
                false
            }
            passwordET.text.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter your password!", Toast.LENGTH_SHORT).show()
                false
            }
            emailET.text.isNullOrEmpty() && passwordET.text.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter your email & password!", Toast.LENGTH_SHORT)
                    .show()
                false
            }
            else -> true
        }

    private fun sendEmail(addresses: List<String>, subject: String, body: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, addresses.toTypedArray())
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, "There are no email apps installed.", Toast.LENGTH_SHORT).show()
        }
    }
}