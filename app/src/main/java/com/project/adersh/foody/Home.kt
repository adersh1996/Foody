package com.project.adersh.foody

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.airbnb.lottie.LottieAnimationView

class Home : AppCompatActivity() {

    private lateinit var button: AppCompatButton
    private lateinit var anim: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        button = findViewById(R.id.btn)
        anim = findViewById(R.id.animation_view)

        button.setOnClickListener {
            anim.playAnimation()

        }
        anim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                // Animation started
            }

            override fun onAnimationEnd(animation: Animator) {
                // Animation ended

                val intent = Intent(this@Home, RecipeActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationCancel(animation: Animator) {
                // Animation cancelled
            }

            override fun onAnimationRepeat(animation: Animator) {
                // Animation repeated
            }
        })

    }
}