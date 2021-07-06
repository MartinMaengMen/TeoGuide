package com.upc.teoguide.ui.principal.fragments.dashboard

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.textfield.TextInputEditText
import com.upc.teoguide.R
import com.upc.teoguide.data.base.Global
import com.upc.teoguide.data.remote.request.PlanReq
import com.upc.teoguide.databinding.FragmentCreatePlanBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CreatePlanFragment : Fragment() {

    private var _binding: FragmentCreatePlanBinding? = null
    private val binding get() = _binding!!
    private val model: CreatePlanViewModel by viewModels()
    private lateinit var view: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatePlanBinding.inflate(inflater, container, false)

        setUpListener()

        view = binding.etTituloPlan
        binding.clCreatePlan.setOnClickListener {
            hideSoftKeyboard()
        }

        return binding.root
    }

    private fun setUpListener() {
        binding.etTituloPlan.setOnClickListener {
            view = binding.etTituloPlan
        }

        binding.etDescripcionPlan.setOnClickListener {
            view = binding.etDescripcionPlan
        }

        val calendar = Calendar.getInstance()
        val cyear = calendar.get(Calendar.YEAR)
        val cmonth = calendar.get(Calendar.MONTH)
        val cday = calendar.get(Calendar.DAY_OF_MONTH)

        binding.etFechaPlan.setOnClickListener {
            view = binding.etFechaPlan
            hideSoftKeyboard()
            val dp = DatePickerDialog(view.context, {
                view, year, month, day ->
                    var m = (month + 1).toString()
                    var d = day.toString()
                    if( (month+1) < 10) {
                        m = "0"+m
                    }
                    if(day < 10) {
                        d = "0"+d
                    }
                    binding.etFechaPlan.setText("${year}-${m}-${d}")
            }, cyear, cmonth, cday)
            dp.show()
        }

        binding.btnCreatePlan.setOnClickListener {
            postPlan()
        }
    }

    private fun postPlan() {
        hideSoftKeyboard()

        val titulo = binding.etTituloPlan.text.toString()
        val descripcion = binding.etDescripcionPlan.text.toString()
        val fechaPlan = binding.etFechaPlan.text.toString()
        val plan = PlanReq(
            usuarioId = Global.usuarioId!!,
            titulo = titulo,
            descripcion = descripcion,
            fechaPlan = fechaPlan
        )

        Log.d("plan por postear:", plan.toString())

        model.postPlan(plan).observe(viewLifecycleOwner, { created ->
            if(created) {
                Toast.makeText(context,"Plan registrado correctamente!", Toast.LENGTH_LONG).show()
                NavHostFragment.findNavController(this).popBackStack()
            } else {
                //Toast.makeText(context,"Error al crear el plan!", Toast.LENGTH_LONG).show()
                binding.etTituloPlan.setText("")
                binding.etDescripcionPlan.setText("")
                binding.etFechaPlan.setText("")
            }
        })
    }

    private fun hideSoftKeyboard(){
        val inputManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }


}