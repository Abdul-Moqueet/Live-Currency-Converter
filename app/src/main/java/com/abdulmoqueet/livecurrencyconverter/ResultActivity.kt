package com.abdulmoqueet.livecurrencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import org.json.JSONException
import org.json.JSONObject
import androidx.recyclerview.widget.LinearLayoutManager

class ResultActivity : AppCompatActivity() {

    lateinit var recyclerList: RecyclerView
    lateinit var adapter:RecyclerView.Adapter<CustomAdapter.MyViewHolder>
    lateinit var mList: ArrayList<CurrencyData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        recyclerList = findViewById(R.id.recycler_list)

        val flag = arrayOf(
            R.drawable.united_states_of_america,
            R.drawable.united_kingdom,
            R.drawable.india,
            R.drawable.china,
            R.drawable.japan,
            R.drawable.australia,
            R.drawable.european_union,
            R.drawable.brazil,
            R.drawable.canada,
            R.drawable.indonesia,
            R.drawable.mexico,
            R.drawable.hong_kong
        )

        getCurrencyValues(flag)

        mList = ArrayList()

        adapter = CustomAdapter(this, mList)
        recyclerList.adapter = adapter
        recyclerList.layoutManager = LinearLayoutManager(this)

    }


    private fun getCurrencyValues(flag:Array<Int>) {

        val baseCurrency:String = intent.getStringExtra("Base")!!
        val input = intent.getDoubleExtra("input", 0.0)
        val index = intent.getIntExtra("index", 0)

        val url = "https://api.exchangeratesapi.io/latest?base=$baseCurrency"

        val queue = Volley.newRequestQueue(this)

        val getRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener<JSONObject> {
                    response ->
                try {

                    val jsonObject = response.getJSONObject("rates")

                    mList.add(CurrencyData(baseCurrency,input * jsonObject.getDouble(baseCurrency),flag[index]))

                    mList.add(CurrencyData("USD", input * jsonObject.getDouble("USD"),flag[0]))
                    mList.add(CurrencyData("GBP", input * jsonObject.getDouble("GBP"),flag[1]))
                    mList.add(CurrencyData("INR", input * jsonObject.getDouble("INR"),flag[2]))
                    mList.add(CurrencyData("CNY", input * jsonObject.getDouble("CNY"),flag[3]))
                    mList.add(CurrencyData("JPY", input * jsonObject.getDouble("JPY"),flag[4]))
                    mList.add(CurrencyData("AUD", input * jsonObject.getDouble("AUD"),flag[5]))
                    mList.add(CurrencyData("EUR", input * jsonObject.getDouble("EUR"),flag[6]))
                    mList.add(CurrencyData("BRL", input * jsonObject.getDouble("BRL"),flag[7]))
                    mList.add(CurrencyData("CAD", input * jsonObject.getDouble("CAD"),flag[8]))
                    mList.add(CurrencyData("IDR", input * jsonObject.getDouble("IDR"),flag[9]))
                    mList.add(CurrencyData("MXN", input * jsonObject.getDouble("MXN"),flag[10]))
                    mList.add(CurrencyData("HRK", input * jsonObject.getDouble("HRK"),flag[11]))


                    mList.removeAt(index + 1)

                    adapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error -> error.printStackTrace() }
        )

        queue.add(getRequest)

    }

}
