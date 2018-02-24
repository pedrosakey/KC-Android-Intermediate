package io.keepcoding.madridshops.fragment


import android.app.Activity
import android.content.Context
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
    private var onItemSelectedListener: OnItemSelectedListener? = null


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

            // Click en un item
            list.setOnItemClickListener { _, _, position, _ ->
                // Aviso al listener
                onItemSelectedListener?.onItemSelected(position)
            }

        }

        return root
    }

    fun addList(list: Array<T>) {
        this.list = list
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)
    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onItemSelectedListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnItemSelectedListener) {
            onItemSelectedListener = listener
        }
    }


    interface OnItemSelectedListener {
        fun onItemSelected(position: Int)
    }

}
