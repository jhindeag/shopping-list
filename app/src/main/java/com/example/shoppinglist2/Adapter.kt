package com.example.shoppinglist2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val itemListActivity: AppCompatActivity, itemList: ArrayList<Array<String>>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    private var nameList: ArrayList<String> = ArrayList()
    private var priceList: ArrayList<String> = ArrayList()
    private var dateList: ArrayList<String> = ArrayList()

    init {
        itemList.forEach {
            nameList.add(it[0])
            priceList.add(it[1])
            dateList.add(it[2])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var price: TextView
        var date: TextView

        init {
            name = itemView.findViewById(R.id.EditName)
            price = itemView.findViewById(R.id.EditPrice)
            date = itemView.findViewById(R.id.EditText)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = nameList[position]
        holder.price.text = priceList[position]
        holder.date.text = dateList[position]
        holder.itemView.setOnClickListener {
            val intent = Intent(itemListActivity, ItemDetail::class.java).apply {
                putExtra("name", nameList[position])
                putExtra("price", priceList[position])
                putExtra("date", dateList[position])
            }
            ContextCompat.startActivity(itemListActivity, intent, null)
        }
    }

    override fun getItemCount() = priceList.size
}