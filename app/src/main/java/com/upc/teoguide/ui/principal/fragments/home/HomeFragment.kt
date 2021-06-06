package com.upc.teoguide.ui.principal.fragments.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
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
import kotlin.math.roundToInt

class HomeFragment : Fragment(), ListaCentrosHistoricosAdapter.CentrosHistoricosListener {
    private lateinit var adapter: ListaCentrosHistoricosAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val model: HomeViewModel by viewModels()

    private var locationManager: LocationManager? = null

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
        getLocation()
        //setUpNotifications()
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

    private fun setUpNotifications(title: String, description: String) {
        createNotificationChannel()
        createNotification(title, description)
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

    private fun createNotification(title: String, description: String) {
        var builder = NotificationCompat.Builder(binding.root.context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(description)
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

    private fun getLocation() {
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ContextCompat.checkSelfPermission(binding.root.context, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(binding.root.context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
        } else {
            val locCaral = Location(LocationManager.GPS_PROVIDER)
            var locationListener :LocationListener  = LocationListener { currentLocation ->
                Log.d("============================latitude", currentLocation.latitude.toString())
                Log.d("==========================longitud",currentLocation.longitude.toString())

                locCaral.latitude = -10.891901050774242
                locCaral.longitude = -77.52299354839664
                Log.d("=========loc1", locCaral.toString())

                val diff = currentLocation.distanceTo(locCaral)
                Log.d("======diff", diff.toString())
                if(diff < 10000) {
                    val title = "Sitio arqueológico de interés cercano"
                    val description = "Caral esta a ${(diff / 1000).roundToInt()}km. de ti"
                    setUpNotifications(title, description)
                    Log.d("===msg", description)
                }
            }
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                20L,
                100f,
                locationListener
            )
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                20L,
                100f,
                locationListener
            )
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