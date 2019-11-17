package com.abdulmoqueet.livecurrencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var spCurrency:Spinner
    lateinit var ivFlag:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spCurrency = findViewById(R.id.sp_currency)
        ivFlag = findViewById(R.id.iv_flag)

        val arrayAdapter = ArrayAdapter.createFromResource(this, R.array.currency, R.layout.custom_spinner_layout)
        spCurrency.adapter = arrayAdapter

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

        spCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                ivFlag.setImageResource(flag[position])
            }


        }

    }
}
