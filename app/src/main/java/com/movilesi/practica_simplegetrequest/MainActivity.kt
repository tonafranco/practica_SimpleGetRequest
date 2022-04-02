package com.movilesi.practica_simplegetrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.movilesi.practica_simplegetrequest.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var queue: RequestQueue

    var numero: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        queue = Volley.newRequestQueue(this)
        val view = binding.root
        setContentView(view)

        binding.btnUpdatePokemon.setOnClickListener {
            numero = binding.etPokemonAmount.text.toString().toInt()
            getPokemonList(numero)
        }

    }
    

    fun getPokemonList(listAmount: Int){
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${listAmount}"

        val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject>{ response ->
            Log.i("JSONRESPONSE", response.getJSONArray("results").toString())
        },
        Response.ErrorListener { error ->
            Log.w("JSONRESPONSE", error.message as String)
        })

        queue.add(jsonRequest)
    }

    override fun onStop(){
        super.onStop()
        queue.cancelAll("Stopped")
    }

}