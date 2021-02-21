package com.pranjul.findmycleaneruser.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pranjul.findmycleaner.Service.ApiService
import com.pranjul.findmycleaner.Service.ApiUtils
import com.pranjul.findmycleaneruser.Adapters.SubCategoriesAdapter
import com.pranjul.findmycleaneruser.Model.SubCategoryList
import com.pranjul.findmycleaneruser.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubCategoryActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var categoryName: String
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)
        supportActionBar!!.setTitle("Sub Categories")
        recyclerView = findViewById(R.id.recyclerView)
        categoryName = intent.getStringExtra("cat")!!
        apiService = ApiUtils.apiService
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        getSubcategories(cat = categoryName)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun getSubcategories(cat: String) {
        val url = "getSubCategories.php?req_all=n&category_name=$cat&sub_category_name="
        apiService.getSubCategoriesList(url).enqueue(object : Callback<SubCategoryList> {
            override fun onResponse(
                call: Call<SubCategoryList>,
                response: Response<SubCategoryList>
            ) {
                recyclerView.adapter =
                    SubCategoriesAdapter(this@SubCategoryActivity, response.body())
            }

            override fun onFailure(call: Call<SubCategoryList>, t: Throwable) {
                Log.e("TAG", "onFailure: " + t.message)
            }

        })
    }
}