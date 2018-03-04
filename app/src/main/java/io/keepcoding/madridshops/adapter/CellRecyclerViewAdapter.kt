package io.keepcoding.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.keepcoding.madridshops.R
import io.keepcoding.madridshops.domain.model.Shop

class CellRecyclerViewAdapter(val  elements : List<Shop>): RecyclerView.Adapter<CellRecyclerViewAdapter.CellViewHolder>() {

    var onClickListener: View.OnClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CellViewHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_cell, parent, false)

        view.setOnClickListener(onClickListener)

        return CellViewHolder(view)

    }

    override fun getItemCount(): Int = elements.size

    override fun onBindViewHolder(holder: CellViewHolder?, position: Int) {
        holder?.bindShop(elements[position])
    }


    inner class CellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.cell_image)
        val text = itemView.findViewById<TextView>(R.id.cell_text)

        fun bindShop( shop: Shop) {
            val context = itemView.context

            Picasso.with(context).load(shop.logo).into(image);
            text.text = shop.name

        }

    }





}
