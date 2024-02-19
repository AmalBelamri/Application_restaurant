package com.example.restauapp.Activities;

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restauapp.Adapters.CategoryMealsAdapter
import com.example.restauapp.Fragment.HomeFragment

import com.example.restauapp.R
import com.example.restauapp.ViewModel.CategoryMealsViewModel
import com.example.restauapp.ViewModel.MealViewModel
import com.example.restauapp.databinding.ActivityCategoriesMealBinding

class CategoriesMealActivity:AppCompatActivity() {
    lateinit var binding : ActivityCategoriesMealBinding
    lateinit var categoryMVm:CategoryMealsViewModel
    lateinit var categoryMealsAdapter: CategoryMealsAdapter
   override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityCategoriesMealBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_categories_meal)
       prepareRecyclerViewAdapter()
       setContentView(binding.root)
       categoryMVm = ViewModelProviders.of(this)[CategoryMealsViewModel::class.java]
       categoryMVm.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
       categoryMVm.observerMealsLiveData().observe(this, Observer {categorymeal->
//           categorymeal.forEach{
//               Log.d("test",it.strMeal)
//           }
           categoryMealsAdapter.setCategoryList(categorymeal)
       })

    }

    private fun prepareRecyclerViewAdapter() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.mealRecyclerview.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealsAdapter
        }
    }
}