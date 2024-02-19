package com.example.restauapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restauapp.Models.Meal
import com.example.restauapp.Models.MealList
import com.example.restauapp.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel():ViewModel() {
    private  var MealDeatailsLiveData = MutableLiveData<Meal>()
    fun getMealDeatails(id:String){
        RetrofitInstance.mealApi.getMealById(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if(response.body() !=null){
                    val randomMeal: Meal = response.body()!!.meals[0]
//                    Log.d(  "TEST", "meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}")
                    MealDeatailsLiveData.value = randomMeal

                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d( "HomeFragment",t.message.toString())
            }


        })
    }
    fun observeMealDeatailsLivedata(): LiveData<Meal> {
        return MealDeatailsLiveData
    }
}