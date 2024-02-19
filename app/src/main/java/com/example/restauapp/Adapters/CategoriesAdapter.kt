package com.example.restauapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restauapp.Models.Category
import com.example.restauapp.Models.MealsByCategory
import com.example.restauapp.databinding.CategoryItemBinding

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var categoryList:List<Category> = ArrayList()
    lateinit var onItemClick: ((Category)-> Unit)
    private lateinit var onLongCategoryClick:OnLongCategoryClick

    fun setCategoryList(categoryList: List<Category>){
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    fun setOnLongCategoryClick(onLongCategoryClick:OnLongCategoryClick){
        this.onLongCategoryClick = onLongCategoryClick
    }



//    fun onItemClicked(onItemClick: OnItemCategoryClicked){
//        this.onItemClick = onItemClick
//    }

    class CategoryViewHolder(val binding:CategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            tvCategoryName.text = categoryList[position].strCategory

            Glide.with(holder.itemView)
                .load(categoryList[position].strCategoryThumb)
                .into(imgCategory)
        }

//        holder.itemView.setOnClickListener {
//            onItemClick.onClickListener(categoryList[position])
//        }
        holder.itemView.setOnClickListener {
            onItemClick.invoke(categoryList[position])
        }


    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    interface OnItemCategoryClicked{
        fun onClickListener(category:Category)
    }

    interface OnLongCategoryClick{
        fun onCategoryLongCLick(category:Category)
    }
}