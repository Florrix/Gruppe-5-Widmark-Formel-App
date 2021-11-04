package com.mobileapplications.gruppe_5_widmark_formel_app

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.text.isDigitsOnly
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultDatabase
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentStartBinding
import com.mobileapplications.gruppe_5_widmark_formel_app.model.DataFragmentViewModel
import com.mobileapplications.gruppe_5_widmark_formel_app.model.DataFragmentViewModelFactory
import java.math.BigDecimal
import java.math.RoundingMode

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)

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

        //Wenn auf den Button berechnen geklickt wird, dann wird die Seite des Ergbenisses angezeigt
        binding.buttonCalculate.setOnClickListener { view: View ->
            val genderStr =
                decideGender() //TODO "it" als Paramter. Hatte irgendwie nicht funktioniert
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
            val weight = binding.textWeightInput.text.toString()
            val height = binding.textHeightInput.text
            val duration = binding.textDurationInput.text
            val quantity = binding.spinnerAlcoholQuantity.selectedItem.toString()
            val pureAlcoholPerDrink =
                alcoholMapping.get(binding.spinnerAlcoholtype.selectedItem).toString()

            if (weight.isNullOrEmpty()
                || height.isNullOrEmpty()
                || duration.isNullOrEmpty()
                || quantity.isNullOrEmpty()
                || pureAlcoholPerDrink.isNullOrEmpty()
                || genderStr.equals("")
            ) {
                Toast.makeText(
                    activity,
                    "Please check your input again and only enter valid data!",
                    Toast.LENGTH_SHORT
                ).show();
            } else {
                val totalPureAlcohol = quantity.toDouble() + pureAlcoholPerDrink.toDouble()
                val promille =
                    BigDecimal(totalPureAlcohol / (genderFactor.toDouble() * weight.toDouble()))
                        .setScale(2, RoundingMode.HALF_EVEN)

                view.findNavController()
                    .navigate(
                        StartFragmentDirections.startToResult(
                            weight.toString(),
                            height.toString(),
                            genderStr,
                            duration.toString(),
                            promille.toString(),
                            totalPureAlcohol.toString()
                        )
                    )
            }
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
            //wenn das Hile-item ausgewählt wird, wird versucht mit Hilfe des Navigationspfad toHelpFragment ausgeführt
            //wenn der das nicht klappt, gibt die Methode false zurück, damit die App nicht abstürtzt
            R.id.menuHelp -> {
                try {
                    view?.findNavController()?.navigate(R.id.startToHelp)
                    true
                } catch (ex: Exception) {
                    false
                }

            }
            //das gleiche gilt wenn das Item Start geklickt wird und somit die passende Navigation ausgeführt wird
            /*R.id.menuStart -> {
                try {
                    view?.findNavController()?.navigate(R.id.startToResult)
                    true
                } catch (ex:Exception) {
                    false
                }

            }*/

            R.id.menuAll -> {
                try {
                    view?.findNavController()?.navigate(R.id.startToAll)
                    true
                } catch (ex: Exception) {
                    false
                }

            }

            else -> false
        }

    }


}
