package com.example.restauapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restauapp.Models.Category
import com.example.restauapp.Models.CategoryList
import com.example.restauapp.Models.MealsByCategoryList
import com.example.restauapp.Models.MealsByCategory
import com.example.restauapp.Models.Meal
import com.example.restauapp.Models.MealList
import com.example.restauapp.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    private var  CategoriesLiveData = MutableLiveData<List<Category>>()
    fun getRandomMeal(){
        RetrofitInstance.mealApi.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() !=null){
                    val randomMeal: Meal = response.body()!!.meals[0]
//                    Log.d(  "TEST", "meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")
                    randomMealLiveData.value = randomMeal

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d( "HomeFragment",t.message.toString())
            }


        })
    }
    fun getPopularItems(category:String){
        RetrofitInstance.mealApi.getPopularItems(category).enqueue(object :Callback<MealsByCategoryList>{
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                if (response.body() != null){
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d( "HomeFragment",t.message.toString())
            }

        })
    }
    fun getCategories(){
        RetrofitInstance.mealApi.getCategories().enqueue(object :Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
//                if (response.body() != null){
//                    CategoriesLiveData.value = response.body()!!.categories
//                }
                response.body()?.let {categoryList ->
                    CategoriesLiveData.postValue(categoryList.categories)
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d( "HomeFragment",t.message.toString())
            }

        })
    }

    fun observeRandomMealLivedata():LiveData<Meal>{
        return randomMealLiveData
    }
    fun observerPopularItemsLiveData():LiveData<List<MealsByCategory>>{
        return popularItemsLiveData
    }
    fun observerCategoriesLiveData():LiveData<List<Category>>{
        return CategoriesLiveData
    }
}