package com.dicoding.azerif

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCharacter: RecyclerView
    private val list = ArrayList<HonkaiCharacter>()
    private var lastClickTime: Long = 0
    private val clickDelay = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCharacter = findViewById(R.id.rv_character)
        rvCharacter.setHasFixedSize(true)

        list.addAll(getListCharacter())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > clickDelay) {
            lastClickTime = currentTime
            when (item.itemId) {
                R.id.action_about -> {
                    val goAbout = Intent(this@MainActivity, About::class.java)
                    startActivity(goAbout)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListCharacter(): ArrayList<HonkaiCharacter> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataPath = resources.getStringArray(R.array.data_path)
        val dataType = resources.getStringArray(R.array.datatype_name)
        val dataDesc = resources.getStringArray(R.array.data_description)

        val typedArrays = listOf(
            resources.obtainTypedArray(R.array.character_img),
            resources.obtainTypedArray(R.array.character_bg),
            resources.obtainTypedArray(R.array.rarity_icon),
            resources.obtainTypedArray(R.array.path_icon),
            resources.obtainTypedArray(R.array.type_icon)
        )
        val listCharacter = ArrayList<HonkaiCharacter>()

        for (i in dataName.indices) {
            val character = HonkaiCharacter(
                dataName[i],
                dataPath[i],
                dataType[i],
                dataDesc[i],
                typedArrays[0].getResourceId(i, -1), // character_img
                typedArrays[1].getResourceId(i, -1), // character_bg
                typedArrays[2].getResourceId(i, -1), // rarity_icon
                typedArrays[3].getResourceId(i, -1), // path_icon
                typedArrays[4].getResourceId(i, -1)  // type_icon
            )
            listCharacter.add(character)

        }

        typedArrays.forEach { it.recycle() }
        return listCharacter
    }

    private fun showRecyclerList() {
        rvCharacter.layoutManager = LinearLayoutManager(this)
        val listCharacterAdapter = ListCharacterAdapter(list)
        rvCharacter.adapter = listCharacterAdapter

    }

}