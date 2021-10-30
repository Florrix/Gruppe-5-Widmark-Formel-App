package com.mobileapplications.gruppe_5_widmark_formel_app

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
        //Wenn auf den Button berechnen geklickt wird, dann wird wieder die Seite mit den Eingabewerten angezeigt angezeigt
        binding.buttonNewCalculate.setOnClickListener {
                view: View ->
            view.findNavController().navigate(R.id.toStartFragment)
        }
        //kommt noch weg wenn Menu funktioniert
        binding.helpButton.setOnClickListener{
                view: View -> view.findNavController().navigate(R.id.toHelpFragment)
        }
        //Anzeigen der Menüleiste
        setHasOptionsMenu(true)
        return binding.root
    }
    //Wenn man auf die Menüleiste klickt, sollte die zuvor in der options_menu.xml erstellte Liste angezeigt werden
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    //wenn auf ein bestimmtes Element geklickt werden, soll mit Hilfe des Navigationspfad auf die zugehöroge Seite verwiesen werden
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
    }
}