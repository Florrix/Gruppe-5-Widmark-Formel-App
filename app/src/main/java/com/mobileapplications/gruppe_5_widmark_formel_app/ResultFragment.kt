package com.mobileapplications.gruppe_5_widmark_formel_app

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentResultBinding

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
            view.findNavController().navigate(R.id.resultToStart)
        }
        //kommt noch weg wenn Menu funktioniert
        binding.helpButton.setOnClickListener{
                view: View -> view.findNavController().navigate(R.id.resultToHelp)
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
    //wenn auf ein bestimmtes Element geklickt werden, soll mit Hilfe des Navigationspfad auf die zugehörige Seite verwiesen werden
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //es wird mit Hilfe der Id geschaut welches Item ausgewählt wird
        return when(item.itemId){
            //wenn das Hile-item ausgewählt wird, wird versucht mit Hilfe des Navigationspfad toHelpFragment ausgeführt
            //wenn der das nicht klappt, gibt die Methode false zurück, damit die App nicht abstürtzt
            R.id.menuHelp-> {
                try {
                    view?.findNavController()?.navigate(R.id.resultToHelp)
                    true
                } catch (ex:Exception) {
                    false
                }

            }
            //das gleiche gilt wenn das Item für alle ergebnisse geklickt wird ausgeführt
            R.id.menuStart-> {
                try {
                    view?.findNavController()?.navigate(R.id.resultToStart)
                    true
                } catch (ex:Exception) {
                    false
                }

            }

            R.id.menuAll-> {
                try {
                    view?.findNavController()?.navigate(R.id.startToAll)
                    true
                } catch (ex:Exception) {
                    false
                }

            }
            else -> false
        }

    }
}