package com.mobileapplications.gruppe_5_widmark_formel_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentResultBinding
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentStartBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        binding.helpButton.setOnClickListener{
                view: View -> view.findNavController().navigate(R.id.toHelpFragment)
        }

        return binding.root
    }
}