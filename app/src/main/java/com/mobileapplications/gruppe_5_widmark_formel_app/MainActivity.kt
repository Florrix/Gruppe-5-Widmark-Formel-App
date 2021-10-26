package com.mobileapplications.gruppe_5_widmark_formel_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Dialogfesnter nach dem Öfnnen des Buttons zur Anzeige von den Hinetrgrundinfo´s zur Formel
        //dieses soll auf der Seite Hilfe ausgeführt werden
        val buttonInfoFormula = findViewById<Button>(R.id.buttonInfoFormula)
        buttonInfoFormula.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            //Titel des Dialogs
            builder.setTitle("Widmarck-Formel")
            //Inhalt des Fensters
            builder.setMessage("Formel vom schwedische Chemiker Erik M. P. Widmark " +
                    "dient zur Bestimmung der theoretischen maximalen Blutalkoholkonzentration" +
                    "w = A / (m * r)" +
                    "w = der Masseanteil des Alkohols im Körper in ‰" +
                    "A = die aufgenommene Masse des Alkohols in Gramm (g)" +
                    "m = die Masse der Person in Kilogramm (kg)" +
                    "r = der Reduktions- oder Verteilungsfaktor im Körper:" +
                    "bei Männer liegt dieser Wert zwischen: 0,68–0,7" +
                    "und bei Frauen: 0,55–0,60")
            //Aktion wenn auf Okay geklickt wurde
            builder.setPositiveButton("Ok") { _, _ ->
                Toast.makeText(this@MainActivity, "Ok", Toast.LENGTH_SHORT).show()
            }
            //Dialog erstellen
            val infoFormularDialog:AlertDialog = builder.create()
            infoFormularDialog.setCancelable(false)
            infoFormularDialog.show()
        }
    }
        //Anzeige für eine Menü-leiste am oberen rand aller Seiten, damit man immer die Hilfe und
        // "Datenbankseite" immer öffnen kann

        

}