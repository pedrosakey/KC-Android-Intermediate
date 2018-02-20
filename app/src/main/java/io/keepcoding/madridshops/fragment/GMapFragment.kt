package io.keepcoding.madridshops.fragment


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




class GMapFragment : Fragment(){

    companion object {
        private val ARG_SHOPS = "ARG_SHOPS"

        fun newInstance(shops: Shops) : GMapFragment{
            val fragment  = GMapFragment ()
            val arguments = Bundle()
            arguments.putSerializable(ARG_SHOPS, shops)
            fragment.arguments = arguments
            return fragment

        }
    }

    lateinit var mapView: MapView
    lateinit var map: GoogleMap
    lateinit var root: View
    lateinit var shops: Shops

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         shops = arguments.getSerializable(ARG_SHOPS) as Shops
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(inflater != null) {
            root = inflater.inflate(R.layout.fragment_gmap, container, false)

            mapView = root.findViewById<MapView>(R.id.mapview)
            mapView.onCreate(savedInstanceState)

            initializeMap()

        }
        return root
    }

     fun initializeMap() {
         mapView.getMapAsync({
            val gmap = it
            // Centrar mapa
            centerMapInPosition(it, 40.4146500, -3.7004000
            )

            // Pintar direccion

            shops.all().forEach {
                if (it.latitude != "" && it.longitude != ""){
                    gmap.addMarker(MarkerOptions()
                            .position(LatLng(it.latitude.toDouble(), it.longitude.toDouble()))
                            .title("Hello world"))
                }

            }
        })
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
