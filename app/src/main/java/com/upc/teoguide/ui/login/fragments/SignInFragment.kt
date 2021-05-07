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

class SignInFragment : Fragment() {
    private var _binding : FragmentSigninBinding? = null
    val database = this.context?.let { AppDatabase.getInstance(it)}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSigninBinding.inflate(inflater,container,false)
        _binding = binding
        binding.loginButton.setOnClickListener {
            var email = _binding?.userTextInput?.text.toString()
            var password = _binding?.passwordTextInput?.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    if(email!="" && password!=""){
                        var usuario = Usuario(
                            0,
                            _binding?.userTextInput?.text.toString(),
                            _binding?.passwordTextInput?.text.toString())
                    var log = database?.usuarioDAO()?.findUser(usuario.email!!,usuario.password!!)
                    if(log == true)
                    {
                        var i = Intent(activity,HomeActivity::class.java)
                        startActivity(i)
                        activity?.finish()
                    }
                    else
                        Log.e("1","Usuario o contraseña incorrectas")
                }
                else
                    Log.e("1","Complete los campos")
                }
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