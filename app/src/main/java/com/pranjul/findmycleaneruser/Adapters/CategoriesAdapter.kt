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
import com.pranjul.findmycleaneruser.Model.CategoryData
import com.pranjul.findmycleaneruser.R

class CategoriesAdapter(var mContext: Context, category: ArrayList<CategoryData>?) :
    RecyclerView.Adapter<RecyclerHolder>() {

    var categoryList = category
    lateinit var layoutInflater: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_list, parent, false)
        return RecyclerHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val categoryData: CategoryData = categoryList!!.get(position)
        holder.categoryName.text = categoryData.categoryName

        Glide.with(layoutInflater.context)
            .load(/*categoryData.img_path +*/ categoryData.categoryImage)
            .into(holder.imageView)

        holder.cardViewRoot.setOnClickListener {
            mContext.startActivity(Intent(mContext, SubCategoryActivity::class.java).apply {
                putExtra("cat", categoryData.categoryName)
            })

        }
    }

    override fun getItemCount(): Int {
        return categoryList!!.size;
    }
}

class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var categoryName: TextView = itemView.findViewById(R.id.categoryTxt)
    var imageView: ImageView = itemView.findViewById(R.id.categoryImg)
    var cardViewRoot: CardView = itemView.findViewById(R.id.cardViewRoot)

}
