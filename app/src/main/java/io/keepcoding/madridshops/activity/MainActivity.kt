package io.keepcoding.madridshops.activity

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.SupportMapFragment
import io.keepcoding.madridshops.R
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import io.keepcoding.madridshops.domain.model.Shop
import io.keepcoding.madridshops.domain.model.Shops
import io.keepcoding.madridshops.fragment.ListFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        initializeActivity()

    }

    private fun initializeActivity() {

        val getAllShopsInteractor: GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(object: SuccessCompletion<Shops>{
            override fun successCompletion(shops: Shops) {
                // Initilize Maps

                //Cargamos el framento
                //Comprobamos que no está cargado en la jerarquia
                if(supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) == null) {
                    val fragment  = ListFragment.newInstance<Shop>()
                    fragment.addList(shops.all().toTypedArray())
                    supportFragmentManager
                            .beginTransaction()
                            .add(R.id.activity_main_list_fragment, fragment)
                            .commit()
                }

                initializeMap()
            }

        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext,"Error,", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun initializeMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            Log.d("SUCCESS", "HABEMUS MAPA")
            //TODO : Centrar el mapa en madrid y pintar las tiendas
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
