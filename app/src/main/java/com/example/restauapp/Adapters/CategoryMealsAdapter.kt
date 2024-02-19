package com.example.restauapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restauapp.Models.Category
import com.example.restauapp.Models.MealsByCategory
import com.example.restauapp.Models.MealsByCategoryList
import com.example.restauapp.databinding.CategoryItemBinding
import com.example.restauapp.databinding.CategoryMealCardBinding
import kotlin.random.Random

class CategoryMealsAdapter: RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewHolder>() {
    private var categoryList:List<MealsByCategory> = ArrayList()
    lateinit var onItemClick: ((MealsByCategory)-> Unit)

    private lateinit var onLongCategoryClick:OnLongCategoryClick

    fun setCategoryList(categoryList: List<MealsByCategory>){
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    fun setOnLongCategoryClick(onLongCategoryClick:OnLongCategoryClick){
        this.onLongCategoryClick = onLongCategoryClick
    }



//    fun onItemClicked(onItemClick: OnItemCategoryClicked){
//        this.onItemClick = onItemClick
//    }

    class CategoryMealsViewHolder(val binding: CategoryMealCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewHolder {
        return CategoryMealsViewHolder(CategoryMealCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoryMealsViewHolder, position: Int) {
        holder.binding.apply {
            val str = categoryList[position].strMeal.replace(" ","\n")
            val random = Random.nextInt(10,30)
            tvMealName.text = categoryList[position].strMeal +"\n${random * 5} Dh"
            Glide.with(holder.itemView)
                .load(categoryList[position].strMealThumb)
                .into(imgMeal)
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
        fun onClickListener(category: Category)
    }

    interface OnLongCategoryClick{
        fun onCategoryLongCLick(category: Category)
    }
}