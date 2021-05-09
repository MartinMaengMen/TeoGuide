package com.upc.teoguide.ui.principal.fragments.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.upc.teoguide.R
import com.upc.teoguide.data.base.Global
import com.upc.teoguide.data.local.AppDatabase
import com.upc.teoguide.databinding.FragmentProfileBinding
import com.upc.teoguide.ui.principal.fragments.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {

    //private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val model: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentProfileBinding.inflate(inflater, container, false)
        _binding = binding
        val database = this.context?.let { AppDatabase.getInstance(it)}
        CoroutineScope(Dispatchers.IO).launch {
            var usuario = database?.usuarioDAO()?.findById(Global.usuarioId!!)
            withContext(Dispatchers.Main){
                binding.correoDescriptionTextView.text = usuario?.email
                binding.usernameProfileTextView.text = usuario?.name
            }
        }
        return binding.root
    }
}