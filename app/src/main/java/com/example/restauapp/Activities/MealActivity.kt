package com.example.restauapp.Activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.restauapp.Fragment.HomeFragment
import com.example.restauapp.Models.Meal
import com.example.restauapp.R
import com.example.restauapp.ViewModel.HomeViewModel
import com.example.restauapp.ViewModel.MealViewModel
import com.example.restauapp.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var binding: ActivityMealBinding
    private lateinit var MealMvvm: MealViewModel
    private lateinit var YotubeLink:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_meal)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MealMvvm = ViewModelProviders.of(this)[MealViewModel::class.java]
        getMealInformationFormtheIntent()
        setInformationinViews()
        loadingCase()
        MealMvvm.getMealDeatails(mealId)
        observerMealDeatialsLiveData()
        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(YotubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDeatialsLiveData() {
        MealMvvm.observeMealDeatailsLivedata().observe(this, object : Observer<Meal> {
            override fun onChanged(value: Meal) {
                onResponceCase()
                val meal = value
                binding.tvCategoryInfo.text = meal.strCategory
                binding.tvAreaInfo.text = meal.strArea
                binding.tvInstructions.text = meal.strInstructions
                YotubeLink = meal.strYoutube
            }

        })
    }

    private fun setInformationinViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.teal_200))
//        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.teal_200))
    }

    private fun getMealInformationFormtheIntent() {
        val intent = intent
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!


    }
    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategoryInfo.visibility = View.INVISIBLE
        binding.tvAreaInfo.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }
    private fun onResponceCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnSave.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategoryInfo.visibility = View.VISIBLE
        binding.tvAreaInfo.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }
}