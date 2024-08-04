package com.example.cripto

import DataViewModel
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private val viewModel: DataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("HELP")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val spinner: Spinner = findViewById(R.id.spinner)


        viewModel.data.observe(this, Observer { data ->
            println("Data name: ${data.itemsList.size}")
            val names=data.itemsList.map { it.name }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,   names   )

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Apply the adapter to the spinner
            spinner.adapter = adapter


        })

        viewModel.fetchData()

        val textViewDetails: TextView = findViewById(R.id.details)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = viewModel.data.value?.itemsList?.get(position)
                selectedItem?.let {
                    val details = """
                        Detalhes:
                        Nome: ${it.name}
                        Valor em Dólares: ${it.priceUsd}
                        Saiba mais em: ${it.link}
                    """.trimIndent()
                    textViewDetails.text = details
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                textViewDetails.text = "Select an item to see details"
            }
        }




        val yourName:EditText=findViewById(R.id.yourName)
        val currency:EditText=findViewById(R.id.currencyName)

        val submit=findViewById<Button>(R.id.save)

        val listView:ListView = findViewById(R.id.listview_dessert)
        viewModel.voteList.observe(this, Observer { votes ->
            val adapter = VoteAdapter(this, votes)
            listView.adapter = adapter
        })

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = viewModel.voteList.value?.get(position)
            selectedItem?.let {
                viewModel.deleteVote(selectedItem.name)
            }
            viewModel.fetchVotes()
        }
        submit.setOnClickListener{
            val name = yourName.text.toString()
            val currencyVal = currency.text.toString()
            val newVote = VoteItem(name, currencyVal)
            viewModel.postVote(newVote)
            viewModel.fetchVotes()
        }

        viewModel.fetchVotes()



    }
}
