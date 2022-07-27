package com.example.shoppinglist2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class AddItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)
        val addButton = findViewById<Button>(R.id.addButton)
        val dateText = findViewById<TextView>(R.id.EditText)
        val productNameText = findViewById<TextView>(R.id.EditName)
        val priceText = findViewById<TextView>(R.id.EditPrice)

        val actionBar = supportActionBar
        actionBar!!.title = "Add a new item"
        actionBar.setDisplayHomeAsUpEnabled(true)

        addButton.setOnClickListener {
            if (dateText.text.isNotBlank() && productNameText.text.isNotBlank() && priceText.text.isNotBlank()) {
                val line =
                    "${productNameText.text.trim()}|${priceText.text.trim()}|${dateText.text.trim()}"
                val fos = openFileOutput("itemList.txt", MODE_APPEND)
                val writer = fos.writer()
                writer.append(line + "\n")
                writer.close()
                fos.close()
                startActivity(Intent(this, ItemList::class.java))
            } else {
                Snackbar.make(
                    findViewById(R.id.ConstraintLayout),
                    "Not all required text fields are filled with information!",
                    0
                ).show()
            }
        }
    }
}