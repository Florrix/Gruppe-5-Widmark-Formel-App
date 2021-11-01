package com.mobileapplications.gruppe_5_widmark_formel_app

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
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
        //Wenn auf den Button berechnen geklickt wird, dann wird die Seite des Ergbenisses angezigt
        binding.buttonCalculate.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.startToResult)
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
        //Anzeigen der Menüleiste
        setHasOptionsMenu(true)
        return binding.root
    }

    //Wenn man auf die Menüleiste klickt, sollte die zuvor in der options_menu.xml erstellten Liste angezeigt werden
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    //wenn auf ein bestimmtes Element geklickt werden, soll mit Hilfe des Navigationspfad auf die zugehörige Seite verwiesen werden
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //es wird mit Hilfe der Id geschaut welches Item ausgewählt wird
        return when(item.itemId){
            //wenn das Hile-item ausgewählt wird, wird versucht mit Hilfe des Navigationspfad toHelpFragment ausgeführt
                //wenn der das nicht klappt, gibt die Methode false zurück, damit die App nicht abstürtzt
            R.id.menuHelp-> {
                try {
                    view?.findNavController()?.navigate(R.id.startToHelp)
                    true
                } catch (ex:Exception) {
                    false
                }

            }
            //das gleiche gilt wenn das Item Start geklickt wird und somit die passende Navigation ausgeführt wird
            R.id.menuStart -> {
                try {
                    view?.findNavController()?.navigate(R.id.startToResult)
                    true
                } catch (ex:Exception) {
                    false
                }

            }
            /*
            R.id.menuAll-> {
                try {
                    view?.findNavController()?.navigate(R.id.startToAll)
                    true
                } catch (ex:Exception) {
                    false
                }

            }*/
            else -> false
        }

    }

}
