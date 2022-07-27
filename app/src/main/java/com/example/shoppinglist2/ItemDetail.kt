package com.example.shoppinglist2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ItemDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail)
        val editButton = findViewById<Button>(R.id.editButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val editName = findViewById<EditText>(R.id.EditName)
        val editPrice = findViewById<EditText>(R.id.EditPrice)
        val editDate = findViewById<EditText>(R.id.EditDate)
        val currentName = findViewById<TextView>(R.id.CurrentName)
        val currentPrice = findViewById<TextView>(R.id.CurrentPrice)
        val currentDate = findViewById<TextView>(R.id.CurrentDate)

        val actionBar = supportActionBar
        actionBar!!.title = editName.text
        actionBar.setDisplayHomeAsUpEnabled(true)

        currentName.text = intent.getStringExtra("name")
        currentPrice.text = intent.getStringExtra("price")
        currentDate.text = intent.getStringExtra("date")

        editButton.setOnClickListener {
            var newString = ""
            val fis = openFileInput("itemList.txt")
            fis.bufferedReader().readLines().forEach {
                val currentLine = it.split("|").toTypedArray()
                if (currentLine[0] != currentName.text
                    && currentLine[1] != currentPrice.text
                    && currentLine[2] != currentDate.text
                ) {
                    newString += "$it\n"
                } else {
                    var newName = currentName.text
                    var newPrice = currentPrice.text
                    var newDate = currentDate.text
                    if (editName.text.isNotBlank()) {
                        newName = editName.text.trim()
                    }
                    if (editPrice.text.isNotBlank()) {
                        newPrice = editPrice.text.trim()
                    }
                    if (editDate.text.isNotBlank()) {
                        newDate = editDate.text.trim()
                    }
                    newString += "$newName|$newPrice|$newDate\n"
                }
            }
            fis.close()
            val fos = openFileOutput("itemList.txt", MODE_PRIVATE)
            val writer = fos.writer()
            writer.append(newString)
            writer.close()
            fos.close()
            startActivity(Intent(this, ItemList::class.java))
        }

        deleteButton.setOnClickListener {
            var newString = ""
            val fis = openFileInput("itemList.txt")
            fis.bufferedReader().readLines().forEach {
                val currentLine = it.split("|").toTypedArray()
                if (currentLine[0] != currentName.text
                    && currentLine[1] != currentPrice.text
                    && currentLine[2] != currentDate.text
                ) {
                    newString += "$it\n"
                }
            }
            fis.close()
            val fos = openFileOutput("itemList.txt", MODE_PRIVATE)
            val writer = fos.writer()
            writer.append(newString)
            writer.close()
            fos.close()
            startActivity(Intent(this, ItemList::class.java))
        }
    }
}