package com.mobileapplications.gruppe_5_widmark_formel_app

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultDatabase
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentStartBinding
import com.mobileapplications.gruppe_5_widmark_formel_app.model.MainActivityViewModel
import com.mobileapplications.gruppe_5_widmark_formel_app.model.MainActivityViewModelFactory
import java.math.BigDecimal
import java.math.RoundingMode

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
    // Erstellung einer Map mit den ganzen getränken die man Auswählen kann
        //jedem Getränk wird dann einem Wert zugewisesen
        //dieser Wert gibt an, wie viel ml reiner Alkohol in dem Getränk drin ist
        val alcoholMapping = mapOf(
            "Flasche Bier (0.33 l, 4,8 Vol.-%)" to 15.84,
            "Flasche Bier (0.5l, 4,8 Vol.-%)" to 24,
            "Glas Bier (0.2 l, 4,8 Vol.-%)" to 9.6,
            "Glas Bier (0.4l, 4,8 Vol.-%)" to 19.2,
            "Flasche Rotwein (0.75 l, 12.5 Vol.-%)" to 93.75,
            "Glas Rotweinein(0.2 l, 12.5 Vol.-%)" to 25,
            "Flasche Weißwein (0.75l, 11.5 Vol.-%)" to 86.25,
            "Glas Weißweinein(0.2 l, 11.5 Vol.-%)" to 23,
            "Flasche Sekt (0.75l, 12.5 Vol.-%)" to 93.75,
            "Glas Sekt(0.2 l, 12.5 Vol.-%)" to 25,
            "Cocktail (0.4l, 0.15 Vol.-%)" to 60,
            "Shot (4 cl, 0.4 Vol.-%)" to 16,
            "Shot (6 cl, 0.4 Vol.-%)" to 24,
        )

        //Erstellung des ViewModel
        val database = ResultDatabase.getInstance(requireActivity())
        val noteRepository = ResultRepository(database.resultDao)
        val viewModelFactory = MainActivityViewModelFactory(noteRepository)
        val mainActivityViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MainActivityViewModel::class.java)

        //Wenn auf den Button berechnen geklickt wird, dann wird die Seite des Ergbenisses angezeigt
        binding.buttonCalculate.setOnClickListener { view: View ->
            //die Variable ruft eine Methode auf, die dann auswählt welches Geschlecht geklickt wurde
            val genderStr =
                decideGender() //TODO "it" als Paramter. Hatte irgendwie nicht funktioniert
            //Angabe welcher Verteilungswert der Alkhol im körper hat
            // es wird dabei unterschieden, wenn man den radiobutton "weiblich" oder "männlcih" ausgewählt hat
            val genderFactor = when (genderStr) {
                "weiblich" -> {
                    "0.6"
                }
                "männlich" -> {
                    "0.7"
                }
                else -> {
                    "0"
                }
            }
            //setzten der Variablen, die in den Eingabefeldern nach Einagbe sind
            val weight = binding.textWeightInput.text.toString()
            val duration = binding.textDurationInput.text.toString()
            val quantity = binding.spinnerAlcoholQuantity.selectedItem.toString()
            //die pure Alkoholmenge von jedem getränk wird bestimmt
            //dabei wird geschaut,welches getränk aus der Map bzw. dem dazugehörigen Spinner gewählt wurde
            //der Wert zu dem dann gemappt wurde, wird dann noch von ml in g umgewandelt
            // (da Alkohol eine Dichte von  0,79 g/cm^3) wird dann noch *0,79 gerechnet
            val pureAlcoholPerDrink =
                alcoholMapping.get(binding.spinnerAlcoholtype.selectedItem).toString()
                    .toDouble() * 0.79
            //es soll geschaut werden, ob alle Inhalte eingegeben wurden
            if (weight.isNullOrEmpty()
                || duration.isNullOrEmpty()
                || quantity.isNullOrEmpty()
                || genderStr.equals("")
                || quantity.isNullOrEmpty()
                || pureAlcoholPerDrink == 0.0
            ) {
                //wenn noch nicht alles ausgefült ist, dann wird eine Meldung angezeigt
                Toast.makeText(
                    activity,
                    "Bitte überprüfe alle Eingaben",
                    Toast.LENGTH_SHORT
                ).show();
            } else {
                //wenn alles eingegegeben wurde, dann berehnet es den Promillegehelt
                    //Alkohol für die Anzahl der getrunkenene Getränke, Anzahl des Getränks * die pure Menge Alkohol pro Getränk
                var totalPureAlcohol = BigDecimal(quantity.toDouble() * pureAlcoholPerDrink).setScale(2, RoundingMode.HALF_EVEN)
                //genaue Berechnungd er promille
                //pureAlkoholmenge die man aufgenommen hat / (Verteilungsfaktor abhängig vom Geschlecht * Gewicht)
                // - Abbau des Körpers von 0,15 Promille pro Stunde
                var promille = BigDecimal(
                    (totalPureAlcohol.toDouble() / (genderFactor.toDouble() * weight.toDouble()) - 0.15 * duration.toDouble())
                )//dann auf 2 Stellen runden
                    .setScale(2, RoundingMode.HALF_EVEN)
                //wenn der Alkoholwert kleiner 0 ist, gibt es 0 aus, so kann kein negativer Promillewert angezeigt werden
                if(promille < BigDecimal(0)){
                    promille = BigDecimal(0)
                }

                //setzen der Werte in die ViewModel
                mainActivityViewModel.resultPromille = promille.toString()
                mainActivityViewModel.resultWeight = weight
                mainActivityViewModel.resultGender = genderStr
                mainActivityViewModel.resultDuration = duration
                mainActivityViewModel.resultQuantity = totalPureAlcohol.toString()

                //führt die Methode insert im ViewModel auf, diese fügt die Werte später in das Repository ein,
                // womit die Daten auch später in die DB gespeichert weden können
                mainActivityViewModel.insert()

                //dann navigiert es zum ResultFragment und gibt dort die Werte und den Promillewert aus
                view.findNavController()
                    .navigate(
                        StartFragmentDirections.startToResult(
                            weight.toString(),
                            genderStr,
                            duration.toString(),
                            promille.toString(),
                            totalPureAlcohol.toString()
                        )
                    )
            }
        }

        //das String-array was in dem Spinner sich befindet, muss durch die folgende Methode ausgeführt und angezeigt werden
        //Dafür wird das String-array in einen Arrayadpater gepackt, und wenn man nun den Spinner auswählt öffnet sich das Array und wird angezeigt
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

        //das String-array was in dem Spinner sich befindet, muss durch die folgende Methode ausgeführt und angezeigt werden
        //Dafür wird das String-array in einen Arrayadpater gepackt, und wenn man nun den Spinner auswählt öffnet sich das Array und wird angezeigt
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

    //Hier wird je nach Auswahl der Radiobuttons festgelegt ob "männlich" oder "weiblich" ausgewählt wurde
    private fun decideGender(): String {
        when (binding.genderRadioGroup.checkedRadioButtonId) {
            R.id.genderRadioW -> {
                return "weiblich"
            }
            R.id.genderRadioM -> {
                return "männlich"
            }
        }
        return ""
    }


    //Wenn man auf die Menüleiste klickt, sollte die zuvor in der options_menu.xml erstellten Liste angezeigt werden
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    //wenn auf ein bestimmtes Element geklickt werden, soll mit Hilfe des Navigationspfad auf die zugehörige Seite verwiesen werden
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //es wird mit Hilfe der Id geschaut welches Item ausgewählt wird
        return when (item.itemId) {
            //wenn das Hilfe-item ausgewählt wird, wird versucht mit Hilfe des Navigationspfad startToHelp ausgeführt
            //wenn der das nicht klappt, gibt die Methode false zurück, damit die App nicht abstürtzt
            R.id.menuHelp -> {
                try {
                    view?.findNavController()?.navigate(R.id.startToHelp)
                    true
                } catch (ex: Exception) {
                    false
                }

            }
            //wenn "alle Ergebnisse" in dem Menu geklickt wird, soll das DataFragment geöffnet werden
            R.id.menuData -> {
                try {
                    view?.findNavController()?.navigate(R.id.startToData)
                    true
                } catch (ex: Exception) {
                    false
                }

            }

            else -> false
        }

    }


}
