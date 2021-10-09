package com.example.covid19_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StateAdapter(val list: List<StatewiseItem>) : BaseAdapter() {
    lateinit var confirmedtv: TextView
    lateinit var activetv: TextView
    lateinit var recoveredtv: TextView
    lateinit var deceasedtv: TextView
    lateinit var stateTv: TextView
    override fun getCount() = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_list, parent, false)
        val item = list[position]
        confirmedtv = view.findViewById(R.id.confirmedTv)
        activetv = view.findViewById(R.id.activeTv)
        recoveredtv = view.findViewById(R.id.recoveredTv)
        deceasedtv = view.findViewById(R.id.deceasedTv)
        stateTv = view.findViewById(R.id.stateTv)

        confirmedtv.text = SpannableDelta(
            "${item.confirmed}\n↑${item.deltaconfirmed ?: "0"}",
            "#D32F2F",
            item.confirmed?.length ?: 0
        )
        recoveredtv.text = SpannableDelta(
            "${item.recovered}\n↑${item.deltarecovered ?: "0"}",
            "#388E3C",
            item.recovered?.length ?: 0
        )
        activetv.text = SpannableDelta(
            "${item.active}\n↑${item.deltaactive ?: "0"}",
            "#1976D2",
            item.confirmed?.length ?: 0
        )
        deceasedtv.text = SpannableDelta(
            "${item.deaths}\n↑${item.deltadeaths ?: "0"}",
            "#FBC02D",
            item.confirmed?.length ?: 0
        )
        stateTv.text = item.state

        return view
    }

}