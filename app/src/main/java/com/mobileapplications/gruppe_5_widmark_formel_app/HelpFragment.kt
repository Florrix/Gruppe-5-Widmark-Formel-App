package com.mobileapplications.gruppe_5_widmark_formel_app

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mobileapplications.gruppe_5_widmark_formel_app.R
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentHelpBinding
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentStartBinding

class HelpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_help, container, false)

        binding.buttonInfoFormula.setOnClickListener{
            showInfo()
        }

        return binding.root
    }

    private fun showInfo(){
        val builder = AlertDialog.Builder(requireContext())
        //Titel des Dialogs
        builder.setTitle("Widmarck-Formel")
        //Inhalt des Fensters
        builder.setMessage(
            "Formel vom schwedische Chemiker Erik M. P. Widmark " +
                    "dient zur Bestimmung der theoretischen maximalen Blutalkoholkonzentration" +
                    "w = A / (m * r)" +
                    "w = der Masseanteil des Alkohols im Körper in ‰" +
                    "A = die aufgenommene Masse des Alkohols in Gramm (g)" +
                    "m = die Masse der Person in Kilogramm (kg)" +
                    "r = der Reduktions- oder Verteilungsfaktor im Körper:" +
                    "bei Männer liegt dieser Wert zwischen: 0,68–0,7" +
                    "und bei Frauen: 0,55–0,60"
        )
        //Aktion wenn auf Okay geklickt wurde
        builder.setPositiveButton("Ok") { _, _ ->
            Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT).show()
        }
        //Dialog erstellen
        val infoFormularDialog: AlertDialog = builder.create()
        infoFormularDialog.setCancelable(false)
        infoFormularDialog.show()
    }


}
