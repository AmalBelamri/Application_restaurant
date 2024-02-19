package com.example.restauapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restauapp.Models.MealsByCategory
import com.example.restauapp.databinding.PopularItemsBinding

class MostPopularAdapter(): RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
    private var mealslist = ArrayList<MealsByCategory>()
    lateinit var onItemClick: ((MealsByCategory)-> Unit)
    fun setMeals(mealsList: ArrayList<MealsByCategory>){
        this.mealslist = mealsList
        notifyDataSetChanged()
    }
    class PopularMealViewHolder( var binding: PopularItemsBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context), parent,  false))
    }

    override fun getItemCount(): Int {
        return mealslist.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealslist[position].strMealThumb)
            .into(holder.binding.imgPopularMeal)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealslist[position])
        }
    }
}