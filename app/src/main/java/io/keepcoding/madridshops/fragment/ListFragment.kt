package io.keepcoding.madridshops.fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

import io.keepcoding.madridshops.R
import io.keepcoding.madridshops.adapter.CellRecyclerViewAdapter
import io.keepcoding.madridshops.domain.model.Shop
import io.keepcoding.madridshops.domain.model.Shops
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment  : Fragment() {

    lateinit var list : Array<Shop>
    private var onItemSelectedListener: OnItemSelectedListener? = null


    companion object {
       fun  newInstance() : ListFragment {
           return ListFragment()
        }
    }

    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_list, container, false)

            val recyclerView = root.findViewById<RecyclerView>(R.id.fragment_recycler_view)

            // Layout manager
            recyclerView.layoutManager = LinearLayoutManager(root.context)

            // Animation
            recyclerView.itemAnimator = DefaultItemAnimator()

            // Create Adapter
            val adapter = CellRecyclerViewAdapter(list.toList())

            // Bind Adapter
            recyclerView.adapter = adapter

            // Click en un item

            adapter.onClickListener = View.OnClickListener { view ->
                val position = recyclerView.getChildAdapterPosition(view)
                // Aviso al listener
                onItemSelectedListener?.onItemSelected(position)
            }





        }

        return root
    }

    fun addList(list: Array<Shop>) {
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
