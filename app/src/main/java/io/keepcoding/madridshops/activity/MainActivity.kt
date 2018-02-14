package io.keepcoding.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.SupportMapFragment
import io.keepcoding.madridshops.R
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import io.keepcoding.madridshops.domain.model.Shops
import io.keepcoding.madridshops.fragment.ListFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listFragment: ListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Log.d("App","onCreate MainActivity")
        setupMap()


       // var listFragment: MapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) as MapFragment
    }

    private fun setupMap() {

        val getAllShopsInteractor: GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(object: SuccessCompletion<Shops>{
            override fun successCompletion(shops: Shops) {
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
