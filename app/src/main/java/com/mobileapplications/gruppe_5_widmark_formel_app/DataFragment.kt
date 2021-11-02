package com.mobileapplications.gruppe_5_widmark_formel_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultDatabase
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentDataBinding
import com.mobileapplications.gruppe_5_widmark_formel_app.model.DataFragmentViewModel
import com.mobileapplications.gruppe_5_widmark_formel_app.model.DataFragmentViewModelFactory

class DataFragment : AppCompatActivity() {
    private lateinit var binding: FragmentDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.fragment_data
        )
        val database = ResultDatabase.getInstance(application)
        val noteRepository = ResultRepository(database.resultDao)
        val viewModelFactory = DataFragmentViewModelFactory(noteRepository)
        val dataFragmentViewmodel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(DataFragmentViewModel::class.java)
        binding.viewModel = dataFragmentViewmodel

    }
}