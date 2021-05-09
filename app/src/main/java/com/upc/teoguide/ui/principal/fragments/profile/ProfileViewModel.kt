package com.upc.teoguide.ui.principal.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.data.entities.Usuario
import com.upc.teoguide.data.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    /*
    private val _text = MutableLiveData<Usuario>().apply {
        value = Usuario()
    }
    val text: LiveData<Usuario> = _text
     */
    /*
    private val usuario: MutableLiveData<Usuario> by lazy {
        MutableLiveData<Usuario>()
    }
    fun getUsuario(database: AppDatabase, id : Int) : LiveData<Usuario>{
        viewModelScope.launch(Dispatchers.IO) {
            usuario.postValue(database.usuarioDAO().findById(id))
        }
        return usuario
    }
     */
}