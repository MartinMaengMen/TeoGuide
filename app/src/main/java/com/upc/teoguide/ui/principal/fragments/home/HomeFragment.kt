package com.upc.teoguide.ui.principal.fragments.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.upc.teoguide.R
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.databinding.FragmentHomeBinding
import com.upc.teoguide.ui.principal.fragments.home.adapters.ListaCentrosHistoricosAdapter
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), ListaCentrosHistoricosAdapter.CentrosHistoricosListener {
    private lateinit var adapter: ListaCentrosHistoricosAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val model: HomeViewModel by viewModels()

    companion object {
        const val CHANNEL_ID = "com.upc.teoguide.CHANNEL"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding = binding
        setUpRecyclerView()
        setUpObservers()
        setUpNotifications()
        return binding.root

    }

    private fun setUpRecyclerView() {
        adapter = ListaCentrosHistoricosAdapter(binding.root.context, this)
        binding.centrosHistoricosRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.centrosHistoricosRecyclerView.adapter = adapter
    }

    private fun setUpObservers() {
        model.getCentrosHistoricos().observe(viewLifecycleOwner, { centroHistoricos ->
            if(centroHistoricos != null){
                adapter.setItems(centroHistoricos as ArrayList<CentroHistorico>)
            } else{
                adapter.setItems(ArrayList())
            }
        })
    }

    private fun setUpNotifications() {
        createNotificationChannel()
        createNotification()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onClickedCentroHistorico(centroHistorico: CentroHistorico, textView: TextView) {
        val action  = HomeFragmentDirections.actionNavigationHomeToDetalleCentroFragment2(centroHistorico)
        val extras = FragmentNavigatorExtras(
            textView to centroHistorico.id.toString()
        )
        NavHostFragment.findNavController(this).navigate(action, extras)
    }

    private fun createNotification() {
        var builder = NotificationCompat.Builder(binding.root.context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Titulo de notificacion")
            .setContentText("Cuerpo de notificacion")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val manager: NotificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1,builder.build())
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val nameChannel = "Canal de notificacion"
            val descriptionChannel = "Descripcion del canal"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, nameChannel, importance).apply {
                description = descriptionChannel
            }
            val manager: NotificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

        }
    }

    //SI SIRVE
    /*override fun onClickedCentro(centro: CentroHistorico, textView: TextView) {
        val action  = HomeFragmentDirections.actionHomeFragmentToDetalleCentroFragment(centro)
        val extras = FragmentNavigatorExtras(
            textView to centro.description
        )
        NavHostFragment.findNavController(this).navigate(action, extras)
    }*/

}