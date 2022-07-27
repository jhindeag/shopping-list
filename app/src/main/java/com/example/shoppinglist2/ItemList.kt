package com.example.shoppinglist2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ItemList : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<Adapter.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_list)
        supportActionBar!!.title = "Here is your shopping list"
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val button: FloatingActionButton = findViewById(R.id.addButton)
        layoutManager = LinearLayoutManager(this)
        val itemList = ArrayList<Array<String>>()
        val fos = openFileOutput("itemList.txt", MODE_APPEND)
        fos.close()
        val fis = openFileInput("itemList.txt")
        fis.bufferedReader().readLines().forEach {
            itemList.add(it.split("|").toTypedArray())
        }
        fis.close()
        adapter = Adapter(this, itemList)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        button.setOnClickListener {
            startActivity(Intent(this, AddItem::class.java))
        }
    }
}