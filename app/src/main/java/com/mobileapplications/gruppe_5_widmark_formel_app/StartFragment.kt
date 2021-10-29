package com.mobileapplications.gruppe_5_widmark_formel_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        binding.buttonCalculate.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.toResultFragment)
        }
        //das String-array was in dem Spinner sich befindet, muss druch die folgende Methode ausgeführt und angezeigt werden
        //Dafür wird das String-aray in einen Arrayadpater gepackt, und wenn man nun den Spinner auswählt öffnet sich das Array und wird angezeigt
        val spinnerAtype: Spinner = binding.spinnerAlcoholQuantity
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerAlcoholQuantity,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //Adapter zum Spinner hinzufügen
            spinnerAtype.adapter = adapter
        }

        //das String-array was in dem Spinner sich befindet, muss druch die folgende Methode ausgeführt und angezeigt werden
        //Dafür wird das String-aray in einen Arrayadpater gepackt, und wenn man nun den Spinner auswählt öffnet sich das Array und wird angezeigt
        val spinnerAQuantity: Spinner = binding.spinnerAlcoholtype
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerAlcoholtype,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //Adapter zum Spinner hinzufügen
            spinnerAQuantity.adapter = adapter
        }

        return binding.root
    }
}