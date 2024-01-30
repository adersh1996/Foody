package com.project.adersh.foody

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.project.adersh.retrofitapikotlindemo.api.ApiInterface
import com.project.adersh.retrofitapikotlindemo.api.ApiUtility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeActivity : AppCompatActivity() {

    lateinit var imageView: ShapeableImageView
    lateinit var foodName: TextView
    lateinit var foodDescription: TextView
    lateinit var animationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        imageView = findViewById(R.id.food_img)
        foodName = findViewById(R.id.food_name)
        foodDescription = findViewById(R.id.food_description)
        animationView = findViewById(R.id.animation_view)

        animationView.visibility = View.VISIBLE
        animationView.playAnimation()


        val apiCall = ApiUtility.getInstance().create(ApiInterface::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiCall.getFood()

            withContext(Dispatchers.Main) {

                val root = response.body()

                if (response.isSuccessful) {
                    if (root != null) {

                        animationView.visibility = View.GONE
                        animationView.pauseAnimation()

                        Glide.with(this@RecipeActivity)
                            .load(root.meals?.get(0)?.strMealThumb)
                            .into(imageView)
                        foodName.text = root?.meals?.get(0)?.strMeal.toString()
                        foodDescription.text = root?.meals?.get(0)?.strInstructions
                    } else {
                        Toast.makeText(this@RecipeActivity, "error", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@RecipeActivity, "error", Toast.LENGTH_SHORT).show()
                    animationView.visibility = View.GONE
                    animationView.pauseAnimation()
                }

            }
        }

    }
}