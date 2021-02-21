package com.pranjul.findmycleaneruser.Activities.Navigation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pranjul.findmycleaner.Service.ApiService
import com.pranjul.findmycleaner.Service.ApiUtils
import com.pranjul.findmycleaneruser.Model.CategoryData
import com.pranjul.findmycleaneruser.Model.SubCategoryList
import com.pranjul.findmycleaneruser.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    //    lateinit var catData: ArrayList<CategoriesList>
    lateinit var catData: ArrayList<SubCategoryList>

    //    lateinit var catData2: ArrayList<CategoryData>
//    lateinit var catData2: ArrayList<CategoryData>
    lateinit var catData2: ArrayList<CategoryData>
    lateinit var mutableLiveData: MutableLiveData<SubCategoryList>

    /*listOf(

            )*/
    var apiService: ApiService? = null
    private val _categories = MutableLiveData<ArrayList<CategoryData>>().apply {
        loadCat()

    }

    private fun loadCat(): LiveData<SubCategoryList> {
        apiService = ApiUtils.apiService
        val url: String = "getSubCategories.php?req_all=y&category_name=Domestic&sub_category_name="
        apiService!!.getSubCategoriesList(url).enqueue(object : Callback<SubCategoryList> {
            override fun onResponse(
                    call: Call<SubCategoryList>,
                    response: Response<SubCategoryList>
            ) {

            }

            override fun onFailure(call: Call<SubCategoryList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        catData2 = ArrayList()
        catData2.add(CategoryData("Domestic", R.drawable.domestic))
        catData2.add(CategoryData("Commercial", R.drawable.commercial))
        catData2.add(CategoryData("Automobile", R.drawable.automobile))
        catData2.add(CategoryData("Laundry", R.drawable.laundry))
        catData2.add(CategoryData("Others", R.drawable.others))
        mutableLiveData = MutableLiveData<SubCategoryList>()

        return mutableLiveData
    }


    val categories: LiveData<ArrayList<CategoryData>> = _categories
}