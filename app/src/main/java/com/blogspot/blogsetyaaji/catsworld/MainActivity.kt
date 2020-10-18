package com.blogspot.blogsetyaaji.catsworld

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var rvCat: RecyclerView
    private lateinit var progressLayout: RelativeLayout
    private lateinit var arrayCats: ArrayList<HashMap<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCat = findViewById(R.id.rv_cat)
        progressLayout = findViewById(R.id.progress_layout)
        rvCat.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
        arrayCats = ArrayList()
        cats
    }

    private val cats: Unit
        get() {
            progressLayout.visibility = View.VISIBLE
            val url = "https://api.thecatapi.com/v1/breeds"
            val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null, { response ->
                progressLayout.visibility = View.GONE
                if (response != null) {
                    Log.d("hasil", "onResponse: $response")
                    for (a in 0 until response.length()) {
                        try {
                            val jsonObject = response.getJSONObject(a)
                            val map = HashMap<String, String>()
                            map["nama"] = jsonObject.getString("name")
                            map["asal"] = jsonObject.getString("origin")
                            arrayCats.add(map)
                            val catAdapter = CatAdapter(arrayCats)
                            rvCat.adapter = catAdapter
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
            }) { error ->
                progressLayout.visibility = View.GONE
                Log.e("hasil", "onErrorResponse: " + error.message)
                Toast.makeText(this@MainActivity, "Server error, try again later", Toast.LENGTH_SHORT).show()
            }
            val requestQueue = Volley.newRequestQueue(this@MainActivity)
            requestQueue.add(jsonArrayRequest)
        }
}
