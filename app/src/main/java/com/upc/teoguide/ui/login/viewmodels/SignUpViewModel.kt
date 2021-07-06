package com.upc.teoguide.ui.login.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upc.teoguide.data.entities.Usuario
import com.upc.teoguide.data.local.AppDatabase
import com.upc.teoguide.data.remote.ApiClient
import com.upc.teoguide.data.remote.request.UsuarioReq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(database: AppDatabase) : ViewModel() {
    var _database : AppDatabase = database

    fun postUsuario(usuario : Usuario){
        var idNewUsuario = 0
        val newUsuario = UsuarioReq(
            nombres = usuario.name!!,
            apellidos = "SnAp",
            correo = usuario.email!!,
            contrasenya = usuario.password!!,
            imagenUrl = "url",
            tipo = "TURISTA_LOCAL")

        val postResponse = ApiClient.build()?.postUsuario(newUsuario)
        postResponse?.enqueue(object: Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                idNewUsuario = response.body()!!.toInt()

                viewModelScope.launch(Dispatchers.IO) {
                    usuario.id = idNewUsuario
                    _database.usuarioDAO().insert(usuario)
                }

            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
}