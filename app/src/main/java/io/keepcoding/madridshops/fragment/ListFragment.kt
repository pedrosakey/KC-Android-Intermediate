package io.keepcoding.madridshops.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

import io.keepcoding.madridshops.R
import io.keepcoding.madridshops.domain.model.Shop
import io.keepcoding.madridshops.domain.model.Shops

class ListFragment <T> : Fragment() {

    lateinit var list : Array<T>

    companion object {
       fun <T> newInstance() : ListFragment<T> {
           return ListFragment()
        }
    }

    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_list, container, false)
            var list = root.findViewById<ListView>(R.id.activity_main_list_fragment)
            val adapter = ArrayAdapter<T>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    this.list
            )
            list.adapter = adapter

        }

        return root
    }

    fun addList(list: Array<T>) {
        this.list = list
    }

}
