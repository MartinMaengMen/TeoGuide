package com.upc.teoguide.ui.principal.fragments.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import com.upc.teoguide.R
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.data.entities.Global
import com.upc.teoguide.databinding.FragmentHomeBinding
import com.upc.teoguide.ui.principal.fragments.home.adapters.ListaCentrosHistoricosAdapter
import retrofit2.http.Url
import java.lang.Exception
import java.net.URI
import java.net.URL
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class HomeFragment : Fragment(), ListaCentrosHistoricosAdapter.CentrosHistoricosListener {
    private lateinit var adapter: ListaCentrosHistoricosAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val model: HomeViewModel by viewModels()

    private lateinit var focusedLocation : FusedLocationProviderClient

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
        focusedLocation = LocationServices.getFusedLocationProviderClient(binding.root.context)
        setUpRecyclerView()
        setUpObservers()
        if(!Global.primerInicio){
            getLocation()
            Global.primerInicio = true
        }
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

    private fun setUpNotifications(title: String, description: String, image : String) {
        createNotificationChannel()
        createNotification(title, description, image)
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

    private fun createNotification(title: String, description: String, imageNotification: String) {
        val largeIcon : Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_teoguide_logo_foreground)
        lateinit var image : Bitmap

        Picasso.get().load(imageNotification).into(object: com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                if (bitmap != null) {
                    image = bitmap
                }
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                image = BitmapFactory.decodeResource(resources, R.mipmap.ic_teoguide_logo_foreground)
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                image = BitmapFactory.decodeResource(resources, R.mipmap.ic_teoguide_logo_foreground)
            }
        })

        var builder = NotificationCompat.Builder(binding.root.context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(description)
            .setLargeIcon(largeIcon)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(image).bigLargeIcon(null))
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
        if (ContextCompat.checkSelfPermission(binding.root.context, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(binding.root.context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
        } else {
            focusedLocation.lastLocation.addOnSuccessListener { currentlocation: Location? ->
                Log.d("===latitude", currentlocation?.latitude.toString())
                Log.d("======longitud",currentlocation?.longitude.toString())

                if (currentlocation != null) {
                    searchNearbyCentros(currentlocation)
                }
            }
        }
    }

    private fun searchNearbyCentros(currentPosition:Location) {
        val nearbyCentros : MutableList<CentroHistorico> = ArrayList()
        val nearbyDistances : ArrayList<Double> = ArrayList()
        model.getCentrosHistoricos().observe(viewLifecycleOwner, { centroHistoricos ->

            if(centroHistoricos != null){
                for (centro in centroHistoricos) {
                    val locationPlace = Location(LocationManager.GPS_PROVIDER)
                    locationPlace.latitude = centro.latitud
                    locationPlace.longitude = centro.longitud

                    if (currentPosition.distanceTo(locationPlace) < 10000) {
                        nearbyCentros.add(centro)
                        nearbyDistances.add(currentPosition.distanceTo(locationPlace).toDouble())
                    }
                }
                Log.d("===nearbyCentros", nearbyCentros.toString())
                Log.d("===nearbyDistances", nearbyDistances.toString())
                if (nearbyCentros.size != 0) {
                    val title = "Sitio arqueológico cercano que puede interesarte"
                    val description = "${nearbyCentros[0].nombre} esta a ${(nearbyDistances[0] / 1000).roundToInt()}km. de ti"
                    setUpNotifications(title, description, nearbyCentros[0].imgUrl)

                    Log.d("===msg", description)
                }
            }
        })

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