package com.upc.teoguide.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.upc.teoguide.databinding.ActivityLoginBinding
import com.upc.teoguide.ui.login.fragments.SignInFragment
import com.upc.teoguide.ui.principal.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*supportFragmentManager
            .beginTransaction()
            .replace(binding.loginContent.id, SignInFragment())
            .commit()*/
        /*
        binding.loginButton.setOnClickListener {
            //val action = LoginFragmentDirections.actionLoginFragmentToProductGridFragment(user)
            /*NavHostFragment.findNavController(this)
                    .navigate(action)*/
            var i = Intent(this,HomeActivity::class.java)
            startActivity(i)
            finish()
        }
         */
    }
}