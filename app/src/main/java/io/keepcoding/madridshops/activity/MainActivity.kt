package io.keepcoding.madridshops.activity

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import io.keepcoding.madridshops.R
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import io.keepcoding.madridshops.domain.model.Shop
import io.keepcoding.madridshops.domain.model.Shops
import io.keepcoding.madridshops.fragment.ListFragment

import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.SupportMapFragment
import io.keepcoding.madridshops.domain.interactor.deleteallshops.DeleteAllShopsImpl
import io.keepcoding.madridshops.fragment.GMapFragment


class MainActivity : AppCompatActivity(), ListFragment.OnItemSelectedListener {

companion object {

    val ARG_ELEMENTS = "ARG_ELEMENTS "

    fun intent(context: Context,elements: Shops) : Intent{
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(ARG_ELEMENTS, elements)
        return intent
    }

}

    lateinit var elements: Shops


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

            initializeActivity()

    }

    private fun initializeActivity() {
        Log.d("getshops", "APP - Inicializamos... MainActivity")

                elements = intent.getSerializableExtra(ARG_ELEMENTS) as Shops
                if(supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) == null) {
                    val fragment  = GMapFragment.newInstance(elements)
                    supportFragmentManager
                            .beginTransaction()
                            .add(R.id.activity_main_map_fragment, fragment)
                            .commit()

                }

               //Cargamos el framento
                //Comprobamos que no está cargado en la jerarquia
                if(supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) == null) {
                    val fragment  = ListFragment.newInstance()
                    fragment.addList(elements.all().toTypedArray())
                    supportFragmentManager
                            .beginTransaction()
                            .add(R.id.activity_main_list_fragment, fragment)
                            .commit()
                }


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

    override fun onItemSelected(position: Int) {
        startActivity(DetailActivity.intent(this, elements.get(position)))
    }
}
