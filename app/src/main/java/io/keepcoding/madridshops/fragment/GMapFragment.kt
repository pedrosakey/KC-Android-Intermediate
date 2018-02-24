package io.keepcoding.madridshops.fragment


import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*


import io.keepcoding.madridshops.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions
import io.keepcoding.madridshops.domain.model.Shops
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_map.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.provider.Contacts
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import io.keepcoding.madridshops.domain.model.Shop
import java.net.URL
import java.util.*


class GMapFragment : Fragment(){

    companion object {
        private val ARG_SHOPS = "ARG_SHOPS"
        private val ARG_SHOP = "ARG_SHOP"

        fun newInstance(shops: Shops) : GMapFragment{
            val fragment  = GMapFragment ()
            val arguments = Bundle()
            arguments.putSerializable(ARG_SHOPS, shops)
            fragment.arguments = arguments
            return fragment

        }

        fun newInstance(shop: Shop) : GMapFragment {
            val fragment  = GMapFragment ()
            val arguments = Bundle()
            arguments.putSerializable(ARG_SHOP, shop)
            fragment.arguments = arguments
            return fragment

        }
    }

    lateinit var mapView: MapView
    lateinit var root: View
     var shops: Shops? = null
     var shop: Shop? = null
    val SOL = LatLng(40.4146500, -3.7004000)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         shops = arguments.getSerializable(ARG_SHOPS) as Shops?
         shop = arguments.getSerializable(ARG_SHOP) as Shop?

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(inflater != null) {
            root = inflater.inflate(R.layout.fragment_gmap, container, false)

            mapView = root.findViewById<MapView>(R.id.mapview)
            mapView.onCreate(savedInstanceState)

            if(shops != null) {
                setupMapMainActivity()
            }else if ( shop != null) {
                setupMapDetailActivity()
            }

        }
        return root
    }

     fun setupMapMainActivity() {
         mapView.getMapAsync({
            val gmap = it

             // Localizacion habilitada
             // TODO Solicitar permisos

             if (ContextCompat.checkSelfPermission(root.context, Manifest.permission.ACCESS_FINE_LOCATION)
                     == PackageManager.PERMISSION_GRANTED) {
                 gmap.setMyLocationEnabled(true);
             }   else {
                     Toast.makeText(root.context, "Active location app's services!", Toast.LENGTH_LONG).show();
                     if (ContextCompat.checkSelfPermission(root.context, Manifest.permission.ACCESS_FINE_LOCATION)
                             == PackageManager.PERMISSION_GRANTED) {
                         gmap.setMyLocationEnabled(true);
                     }

                 }
             // Centrar mapa
            centerMapInPosition(it, SOL.latitude, SOL.longitude)

           //Pintar tiendas
            shops?.all()?.forEach {
                if (it.latitude != "" && it.longitude != ""){
                    gmap.addMarker(MarkerOptions()
                            .position(LatLng(it.latitude.toDouble(), it.longitude.toDouble()))
                            .title(it.name))
                }

            }
        })


    }



    fun setupMapDetailActivity() {
        mapView.getMapAsync {
            // Centro el mapa en la localizacion
            //TODO Localizacion de la persona
            val gmap = it

            val origin = SOL

            val lat = shop?.latitude?.toDouble()
            val long = shop?.longitude?.toDouble()

            if ( lat != null && long != null) {
                var destination = LatLng(lat, long) // la de la tienda

                centerMapInPosition(gmap, origin.latitude, origin.longitude)
                // Donwload Json with multiple locations
                // TODO Ir al modulo repository a por ello
                var url = getUrl(origin, destination)

                // Draw
                val coordList = arrayListOf(LatLng(40.7, -74.0), LatLng(41.7, -76.0))
                // ... get a map.
                gmap.addPolyline(PolylineOptions()
                        .add(origin, destination)
                        .width(5f)
                        .color(Color.RED))
            }
        }
    }

    // Al repositorio
    fun getUrl(a: LatLng, b: LatLng) : String {
        val DIRECTION_API = "https://maps.googleapis.com/maps/api/directions/json?origin="
        val API_KEY = R.string.GOOGLE_MAPS_API_KEY
        return DIRECTION_API + a.latitude.toString() + ","+ a.longitude.toString() + "&destination=" +
                b.latitude.toString() + "," + b.longitude.toString() + "&key=" + API_KEY
    }

    fun centerMapInPosition(googleMap: GoogleMap, latitude: Double, longitude: Double) {
        val cameraPosition = CameraPosition.Builder().target(
                LatLng(latitude, longitude)).zoom(12f).build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }


    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}
