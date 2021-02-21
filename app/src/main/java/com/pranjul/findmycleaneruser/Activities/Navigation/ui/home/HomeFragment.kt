package com.pranjul.findmycleaneruser.Activities.Navigation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.pranjul.findmycleaner.Service.ApiService
import com.pranjul.findmycleaner.Service.ApiUtils
import com.pranjul.findmycleaner.Utils.CustomProgressBar
import com.pranjul.findmycleaneruser.Adapters.CategoriesAdapter
import com.pranjul.findmycleaneruser.Model.CategoriesList
import com.pranjul.findmycleaneruser.Model.CategoryData
import com.pranjul.findmycleaneruser.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var companyTxt: TextView
    private lateinit var serviceTxt: TextView
    private lateinit var companyLayout: LinearLayout
    private lateinit var serviceLayout: LinearLayout
    private lateinit var recyclerView: RecyclerView

    private var list: ArrayList<CategoryData> = ArrayList()
    private lateinit var adapter: CategoriesAdapter
    private lateinit var apiService: ApiService

    lateinit var catData: ArrayList<CategoryData>
    lateinit var customProgressBar: CustomProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initUI(root)

        customProgressBar = CustomProgressBar(this.activity)
        apiService = ApiUtils.apiService

//        load categories
        loadCat()


        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()


        adapter = CategoriesAdapter(requireActivity(), catData)
        recyclerView.adapter = adapter

        homeViewModel.categories.observe(viewLifecycleOwner, Observer {
            list = it
            adapter.notifyDataSetChanged();
        })

        clickEvents()

//        getCategories()

        return root
    }

    private fun getCategories() {
        customProgressBar.showProgress()
        apiService.run {
            getCategoriesList("y").enqueue(object : Callback<CategoriesList> {
                override fun onResponse(
                    call: Call<CategoriesList>,
                    response: Response<CategoriesList>
                ) {
//                    adapter = CategoriesAdapter(activity!!, response.body())
                    recyclerView.adapter = adapter

                    customProgressBar.hideProgress()

                }

                override fun onFailure(call: Call<CategoriesList>, t: Throwable) {
                    customProgressBar.hideProgress()
                }
            })
        }
    }

    private fun loadCat() {
        catData = ArrayList()
        catData.add(CategoryData("Domestic", R.drawable.domestic))
        catData.add(CategoryData("Commercial", R.drawable.commercial))
        catData.add(CategoryData("Automobile", R.drawable.automobile))
        catData.add(CategoryData("Laundry", R.drawable.laundry))
        catData.add(CategoryData("Others", R.drawable.others))
    }

    private fun clickEvents() {
        serviceTxt.setOnClickListener {
//            if (serviceLayout.visibility == View.VISIBLE) {

            serviceLayout.visibility = View.VISIBLE
            companyLayout.visibility = View.GONE
//            }
        }
        companyTxt.setOnClickListener {

            serviceLayout.visibility = View.GONE
            companyLayout.visibility = View.VISIBLE

        }

    }

    private fun initUI(root: View) {
        recyclerView = root.findViewById(R.id.recyclerView)
        companyTxt = root.findViewById(R.id.companyTxt)
        companyLayout = root.findViewById(R.id.showCompanyLayout)
        serviceLayout = root.findViewById(R.id.showCategoryLayout)
        serviceTxt = root.findViewById(R.id.serviceTxt)
    }
}