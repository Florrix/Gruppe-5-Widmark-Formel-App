package com.mobileapplications.gruppe_5_widmark_formel_app

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_help, container, false)
        //wenn der Button gedrückt wurde, wird folgende Methode geöffnet
        binding.buttonInfoFormula.setOnClickListener{
            showInfoFormula()
        }
        //wenn der Button gedrückt wurde, wird folgende Methode geöffnet
        binding.buttonInfoValue.setOnClickListener{
            showInfoValue()
        }
        //wenn der Button gedrückt wurde, wird folgende Methode geöffnet
        binding.buttonInfoResult.setOnClickListener{
            showInfoResult()
        }
        //Anzeigen der Menu-leiste
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
            //wenn das Start-item ausgewählt wird, wird versucht mit Hilfe des Navigationspfad helpToStart ausgeführt
            //wenn der das nicht klappt, gibt die Methode false zurück, damit die App nicht abstürtzt
            R.id.menuStart-> {
                try {
                    view?.findNavController()?.navigate(R.id.helpToStart)
                    true
                } catch (ex:Exception) {
                    false
                }

            }
            //das gleiche gilt für das alle Ergebnisse Item
            R.id.menuData-> {
                try {
                    view?.findNavController()?.navigate(R.id.helpToData)
                    true
                } catch (ex:Exception) {
                    false
                }

            }
            else -> false
        }

    }

    // die Methode erstellt ein Dialog fenster, welches dann geöffnet wird, wenn der zuvorherige Button geklickt wird
    private fun showInfoFormula(){
        val builder = AlertDialog.Builder(requireContext())
        //Titel des Dialogs
        builder.setTitle(getString(R.string.InfoTitleWidmarckFormel))
        //Inhalt des Fensters
        builder.setMessage(R.string.InfoWidmarckText)
        //Aktion wenn auf Okay geklickt wurde
        builder.setPositiveButton(R.string.ok) { _, _ ->
        }
        //Dialog erstellen
        val infoFormularDialog: AlertDialog = builder.create()
        infoFormularDialog.setCancelable(false)
        infoFormularDialog.show()
    }
    // die Methode erstellt ein Dialog fenster, welches dann geöffnet wird, wenn der zuvorherige Button geklickt wird
    private fun showInfoValue(){
        val builder = AlertDialog.Builder(requireContext())
        //Titel des Dialogs
        builder.setTitle(R.string.InfoTitleAcceptedInputs)
        //Inhalt des Fensters
        builder.setMessage(R.string.InfoInputsText)
        //Aktion wenn auf Okay geklickt wurde
        builder.setPositiveButton(R.string.ok) { _, _ ->
        }
        //Dialog erstellen
        val infoFormularDialog: AlertDialog = builder.create()
        infoFormularDialog.setCancelable(false)
        infoFormularDialog.show()
    }
    // die Methode erstellt ein Dialog fenster, welches dann geöffnet wird, wenn der zuvorherige Button geklickt wird
    private fun showInfoResult(){
        val builder = AlertDialog.Builder(requireContext())
        //Titel des Dialogs
        builder.setTitle(R.string.InfoTitlePromilleSkala)
        //Inhalt des Fensters
        builder.setMessage(R.string.InfoPromilleText)
        //Aktion wenn auf Okay geklickt wurde
        builder.setPositiveButton(R.string.ok) { _, _ ->
        }
        //Dialog erstellen
        val infoFormularDialog: AlertDialog = builder.create()
        infoFormularDialog.setCancelable(false)
        infoFormularDialog.show()
    }



}
