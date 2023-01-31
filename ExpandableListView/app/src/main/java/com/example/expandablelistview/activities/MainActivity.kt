package com.example.expandablelistview.activities

import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.expandablelistview.adapters.MyExpandableAdapter
import com.example.expandablelistview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var map: HashMap<String, ArrayList<String>>
    private lateinit var titleList: ArrayList<String>
    private lateinit var myExpandableAdapter: MyExpandableAdapter
    private var lastPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

        myExpandableAdapter = MyExpandableAdapter(map, titleList)
        binding.listView.setAdapter(myExpandableAdapter)

        binding.listView.setOnGroupClickListener { parent, v, groupPosition, id ->
            Toast.makeText(this@MainActivity, titleList[groupPosition], Toast.LENGTH_SHORT)
                .show()
            false
        }

        binding.listView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(
                this@MainActivity,
                map[titleList[groupPosition]]!![childPosition],
                Toast.LENGTH_SHORT
            ).show()
            false
        }

        binding.listView.setOnGroupCollapseListener {
            Toast.makeText(
                this@MainActivity,
                "Collapse",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.listView.setOnGroupExpandListener { groupPosition ->
            if (lastPosition != -1) {
                binding.listView.collapseGroup(lastPosition)
            }
            lastPosition = groupPosition
            Toast.makeText(this@MainActivity, "Expand", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadData() {
        map = HashMap()

        val list1 = ArrayList<String>()
        list1.add("MYU")
        list1.add("Chelsea")
        list1.add("Arsenal")
        list1.add("Mcity")
        map.put("Premier Liga", list1)

        val list2 = ArrayList<String>()
        list2.add("Real")
        list2.add("Barsa")
        list2.add("Atletico")
        list2.add("Sevilla")
        map.put("LaLiga", list2)

        val list3 = ArrayList<String>()
        list3.add("Paxtakor")
        list3.add("Bunyodkor")
        list3.add("Qizilqum")
        list3.add("Lokomotiv")
        map.put("Coca cola", list3)

        titleList = ArrayList(map.keys)
    }
}