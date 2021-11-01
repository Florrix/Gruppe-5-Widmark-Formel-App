package com.mobileapplications.gruppe_5_widmark_formel_app

//import android.view.Menu
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultDatabase
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.ActivityMainBinding
import com.mobileapplications.gruppe_5_widmark_formel_app.model.ActivityMainViewModel
import com.mobileapplications.gruppe_5_widmark_formel_app.model.ActivityMainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        val database =
            ResultDatabase.getInstance(application)
        val noteRepository =
            ResultRepository(database.resultDao)
        val viewModelFactory =
            ActivityMainViewModelFactory(noteRepository)
        val mainActivityViewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ActivityMainViewModel::class.java)
        binding.viewModel = mainActivityViewModel
    }
}