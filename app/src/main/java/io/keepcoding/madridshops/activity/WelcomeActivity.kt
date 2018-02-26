package io.keepcoding.madridshops.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import io.keepcoding.madridshops.R
import io.keepcoding.madridshops.domain.interactor.ErrorCompletion
import io.keepcoding.madridshops.domain.interactor.SuccessCompletion
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllActivitiesInteractorImpl
import io.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import io.keepcoding.madridshops.domain.model.Shops

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val shops = findViewById<FrameLayout>(R.id.welcome_activity_shops)
        val activities = findViewById<FrameLayout>(R.id.welcome_activity_activities)
        val context: Context = this

        shops.setOnClickListener {

            GetAllShopsInteractorImpl(this).execute(

                    success = object : SuccessCompletion<Shops> {
                        override fun successCompletion(shops: Shops) {
                           // Intent con shops
                            startActivity(MainActivity.intent(context,shops))
                        }

                    },
                    error = object : ErrorCompletion {
                        override fun errorCompletion(errorMessage: String) {

                        }
                    })



        }

        activities.setOnClickListener {

            GetAllActivitiesInteractorImpl(this).execute(

                    success = object : SuccessCompletion<Shops> {
                        override fun successCompletion(shops: Shops) {
                            // Intent con actividades
                            startActivity(MainActivity.intent(context,shops))


                        }
                    },
                    error = object : ErrorCompletion {
                        override fun errorCompletion(errorMessage: String) {
                            Log.d("error", errorMessage)
                        }
                    })


        }
    }
}
