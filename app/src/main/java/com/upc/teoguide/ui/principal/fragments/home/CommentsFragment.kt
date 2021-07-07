package com.upc.teoguide.ui.principal.fragments.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.teoguide.data.base.Global
import com.upc.teoguide.data.entities.Comentario
import com.upc.teoguide.data.remote.request.ComentarioReq
import com.upc.teoguide.databinding.FragmentCommentsBinding
import com.upc.teoguide.ui.principal.fragments.home.adapters.ListaComentariosAdapter
import kotlin.properties.Delegates


class CommentsFragment : Fragment() {
    private lateinit var adapter: ListaComentariosAdapter
    private var _binding: FragmentCommentsBinding?= null
    private val binding get() = _binding!!
    private val model: ComentarioViewModel by viewModels()
    private var centroId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        val editText = binding.etAddComment
        binding.clComments.setOnClickListener {
            hideSoftKeyboard(editText)
        }
        setUpRecyclerView()
        setUpListeners()
        setUpObservers()
        return binding.root
    }

    private fun hideSoftKeyboard(v: View){
        val inputManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(v.windowToken, 0)
        v.clearFocus()
    }

    private fun setUpRecyclerView() {
        adapter = ListaComentariosAdapter(binding.root.context)
        binding.rvComments.layoutManager = LinearLayoutManager(this.context)
        binding.rvComments.adapter = adapter
    }

    private fun setUpObservers() {
        arguments?.takeIf { it.containsKey("arg1") }?.apply {
            centroId = getInt("arg1").toInt()

        }

        model.getComentarios(centroId).observe(viewLifecycleOwner, { comentarios  ->
            if(comentarios != null) {
                adapter.setItems(comentarios as ArrayList<Comentario>)
            } else {
                adapter.setItems(ArrayList())
            }

        })
    }

    private fun setUpListeners() {
        binding.btnAddComment.setOnClickListener {
            postComment()
        }
    }

    private fun postComment() {
        val texto = binding.etAddComment.text.toString()
        val usuarioId = Global.usuarioId
        val centroHistoricoId = centroId
        val comentario = ComentarioReq(
            usuarioId = usuarioId!!,
            centroHistoricoId = centroHistoricoId,
            texto = texto)

        model.postComentario(comentario).observe(viewLifecycleOwner, { comentarios ->
            adapter.setItems(comentarios as ArrayList<Comentario>)
        })

        binding.etAddComment.setText("")
        hideSoftKeyboard(binding.etAddComment)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

