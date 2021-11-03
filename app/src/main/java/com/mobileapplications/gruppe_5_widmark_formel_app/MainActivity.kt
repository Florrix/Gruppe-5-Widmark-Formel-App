package com.mobileapplications.gruppe_5_widmark_formel_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Wenn die HäkchenButton bei den Eingabefeldern geklickt wird, soll jeweils diese Methode ausgeführt werden
        findViewById<Button>(R.id.buttonWeightDone).setOnClickListener {
            addWeightValue(it)
        }
        findViewById<Button>(R.id.buttonHeightDone).setOnClickListener {
            addHeightValue(it)
        }
        findViewById<Button>(R.id.buttonDurationDone).setOnClickListener {
            addDurationValue(it)
        }
        //Wenn ein eingegebner Wert geändert werden soll, dann klickt man auf das TextView und dann öffnet sich die Änderungsmethode
        findViewById<TextView>(R.id.WeightView).setOnClickListener {
            updateWeight(it)
        }
        findViewById<TextView>(R.id.HeightView).setOnClickListener {
            updateHeight(it)
        }
        findViewById<TextView>(R.id.DurationView).setOnClickListener {
            updateDuration(it)
        }
    }
    //Wenn auf den Häkchen Button bei der Gewichtseingabe geklickt wird, wird der einegegebne Wert in einem TextView angezeigt, und die tastatur verschiwndet
    fun addWeightValue(view: View) {
        //Um den Wert von dem Eingabefeld des Gewichts zu bekommen
        val editWeight= findViewById<EditText>(R.id.textWeightInput)
        //Und die textView defineiren, wo dann der Werte des Gewichts eingefügt werden soll
        val weightView = findViewById<TextView>(R.id.WeightView)
        //wenn das Eingabefeld von dem gewicht ausgewählt wurde, dann setze den eingegebenenText auf das Anzeige-textView
        weightView.text = editWeight.text
        //Button wieder anzeigen, daa er standardmäßig, wenn noch nix passiert ist, nicht zu sehen ist
        view.visibility= View.VISIBLE
        //Eingabefeld ausblenden
        editWeight.visibility= View.GONE
        //und button wieder ausblenden
        view.visibility= View.GONE

        //Text anzeigen
        weightView.visibility=View.VISIBLE
        // Danach Tastatur verschwinden lassen
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    //-----------Eingabevorgang für die Größe ---------------------------------------
    fun addHeightValue(view: View) {
        //Um den Wert von dem Eingabefeld der Größe zu bekommen
        val editHeight= findViewById<EditText>(R.id.textHeightInput)
        //Und die textViews, wo dann die Werte eingefügt werden sollen
        val heigtView = findViewById<TextView>(R.id.HeightView)
        //wenn das Eingabefeld von dem gewicht ausgewählt wurde, dann setze den eingegebenenText auf das Anzeige-textView
        heigtView.text = editHeight.text
        //Button wieder anzeigen, daa er standardmäßig, wenn noch nix passiert ist, nicht zu sehen ist
        view.visibility= View.VISIBLE
        //Eingabefeld ausblenden
        editHeight.visibility= View.GONE
        //und button wieder ausblenden
        view.visibility= View.GONE


        //Text anzeigen
        heigtView.visibility=View.VISIBLE
        // Danach Tastatur verschwinden lassen
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    //------------- Einagbevorgang für die Drinkdauer
    fun addDurationValue(view: View) {
        //Um den Wert von der Trinkdauer zu bekommen
        val editDuartion= findViewById<EditText>(R.id.textDurationInput)
        //Und die textView defineiren, wo dann der Werte der Größe eingefügt werden soll
        val durationView = findViewById<TextView>(R.id.DurationView)
        //wenn das Eingabefeld von der Größe ausgewählt wurde, dann setze den eingegebenenText auf das Anzeige-textView
        durationView.text = editDuartion.text
        //Button wieder anzeigen, daa er standardmäßig, wenn noch nix passiert ist, nicht zu sehen ist
        view.visibility= View.VISIBLE
        //Eingabefeld ausblenden
        editDuartion.visibility= View.GONE
        //und button wieder ausblenden
        view.visibility= View.GONE


        //Text anzeigen
        durationView.visibility=View.VISIBLE
        // Danach Tastatur verschwinden lassen
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
    //-------------Wert des Gewichtes setzen ------------------------
    private fun updateWeight (view:View) {
        val editWeight= findViewById<EditText>(R.id.textWeightInput)
        val weightView = findViewById<TextView>(R.id.WeightView)
        val done= findViewById<Button>(R.id.buttonWeightDone)

        // EditText und Button anzeigen, aber das TextView wo der Text drin steht muss wieder unsichtbar gemacht werden
        editWeight.visibility= View.VISIBLE
        weightView.visibility= View.GONE
        done.visibility=View.VISIBLE

        //Tastatur wieder anzeigen
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        imm.showSoftInput(editWeight, 0)
    }
    //-------------Wert des Größe setzen ------------------------
    private fun updateHeight (view:View) {
        val editHeight= findViewById<EditText>(R.id.textHeightInput)
        val heigtView = findViewById<TextView>(R.id.HeightView)
        val done= findViewById<Button>(R.id.buttonHeightDone)

        // EditText und Button anzeigen, aber das TextView wo der Text drin steht muss wieder unsichtbar gemacht werden
        editHeight.visibility= View.VISIBLE
        heigtView.visibility= View.GONE
        done.visibility=View.VISIBLE

        //Tastatur wieder anzeigen
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        imm.showSoftInput(editHeight, 0)
    }
    //-------------Wert der Drinkdauer setzen ------------------------
    private fun updateDuration (view:View) {
        val editDuartion= findViewById<EditText>(R.id.textDurationInput)
        val durationView = findViewById<TextView>(R.id.DurationView)
        val done= findViewById<Button>(R.id.buttonDurationDone)

        // EditText und Button anzeigen, aber das TextView wo der Text drin steht muss wieder unsichtbar gemacht werden
        editDuartion.visibility= View.VISIBLE
        durationView.visibility= View.GONE
        done.visibility=View.VISIBLE

        //Tastatur wieder anzeigen
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        imm.showSoftInput(editDuartion, 0)
    }

}