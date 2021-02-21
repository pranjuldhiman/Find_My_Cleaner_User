package com.pranjul.findmycleaneruser.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pranjul.findmycleaneruser.Activities.SubCategoryActivity
import com.pranjul.findmycleaneruser.Model.CategoriesList
import com.pranjul.findmycleaneruser.Model.CategoriesListItem
import com.pranjul.findmycleaneruser.Model.SubCategoryList
import com.pranjul.findmycleaneruser.Model.SubCategoryListItem
import com.pranjul.findmycleaneruser.R

class SubCategoriesAdapter(var mContext: Context, category: SubCategoryList?) :
    RecyclerView.Adapter<SubRecyclerHolder>() {

    var categoryList = category
    lateinit var layoutInflater: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubRecyclerHolder {
        layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_sub_category, parent, false)
        return SubRecyclerHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: SubRecyclerHolder, position: Int) {
        val categoryData: SubCategoryListItem = categoryList!!.get(position)
        holder.categoryName.text = categoryData.sub_category_title


//        holder.cardViewRoot.setOnClickListener {
//            mContext.startActivity(Intent(mContext, SubCategoryActivity::class.java).apply {
//                putExtra("cat", categoryData.category_name)
//            })

//        }
    }

    override fun getItemCount(): Int {
        return categoryList!!.size;
    }
}

class SubRecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var categoryName: TextView = itemView.findViewById(R.id.categoryTxt)
//    var cardViewRoot: CardView = itemView.findViewById(R.id.cardViewRoot)

}
