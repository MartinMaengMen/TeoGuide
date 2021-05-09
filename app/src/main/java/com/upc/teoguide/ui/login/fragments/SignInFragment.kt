package com.upc.teoguide.ui.login.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.upc.teoguide.data.entities.Usuario
import com.upc.teoguide.data.local.AppDatabase
import com.upc.teoguide.databinding.FragmentSigninBinding
import com.upc.teoguide.ui.login.viewmodels.SignInViewModel
import com.upc.teoguide.ui.login.viewmodels.SignUpViewModel
import com.upc.teoguide.ui.principal.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInFragment : Fragment() {
    private var _binding : FragmentSigninBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSigninBinding.inflate(inflater,container,false)
        val database = this.context?.let { AppDatabase.getInstance(it)}
        _binding = binding
        binding.loginButton.setOnClickListener {
            var email = binding?.userTextInput?.text.toString()
            var password = binding?.passwordTextInput?.text.toString()
            if(email!="" && password!="")
                CoroutineScope(Dispatchers.IO).launch {
                    var log = database?.usuarioDAO()?.findUser(email,password)
                    withContext(Dispatchers.Main){
                    if(log == true)
                    {

                        var i = Intent(activity,HomeActivity::class.java)
                        startActivity(i)
                        activity?.finish()

                    }
                    else
                        Toast.makeText(this@SignInFragment.context,"Usuario o contraseña incorrectas",Toast.LENGTH_LONG).show()
                    }
                }
            else
                Toast.makeText(this@SignInFragment.context,"Complete los campos",Toast.LENGTH_LONG).show()
        }
        binding.signupTextView.setOnClickListener {
            val action = SignInFragmentDirections.actionNavigationSigninToNavigationSignup()
            NavHostFragment.findNavController(this)
                .navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}