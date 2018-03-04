package io.keepcoding.madridshops.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.keepcoding.madridshops.R
import io.keepcoding.madridshops.domain.model.Shop
import io.keepcoding.madridshops.fragment.GMapFragment

class DetailActivity : AppCompatActivity() {

    companion object {

        private val ARG_SHOP = "ARG_SHOP"

        fun intent (context: Context, shop: Shop) : Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ARG_SHOP, shop)
            return intent
        }
    }

    lateinit var shop : Shop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Initilize Maps
        shop = intent.getSerializableExtra(ARG_SHOP) as Shop

        if(supportFragmentManager.findFragmentById(R.id.activity_detail_map_fragment) == null) {
            val fragment  = GMapFragment.newInstance(shop)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.activity_detail_map_fragment, fragment)
                    .commit()

        }

        // Description are

        val name = findViewById<TextView>(R.id.activity_detail_name)
        val timeTable = findViewById<TextView>(R.id.activity_detail_timetable)
        val street = findViewById<TextView>(R.id.activity_detail_name_street)
        val description = findViewById<TextView>(R.id.activity_detail_description)

        name.text = shop.name
        timeTable.text = shop.timeTable
        street.text = shop.address
        description.text = shop.description
    }
}
