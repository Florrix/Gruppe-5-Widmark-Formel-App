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
                    view?.findNavController()?.navigate(R.id.startToData)
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
        builder.setTitle("Widmarck-Formel")
        //Inhalt des Fensters
        builder.setMessage(
            "- Formel vom schwedische Chemiker Erik M. P. Widmark \n" +
                    "- dient zur Bestimmung der theoretischen maximalen Blutalkoholkonzentration" +
                    "\n" +
                    "\n"+
                    "w = A / (m * r) \n" +
                    "w = der Masseanteil des Alkohols im Körper in ‰ \n" +
                    "A = die aufgenommene Masse des Alkohols in Gramm (g) \n" +
                    "m = die Masse der Person in Kilogramm (kg) \n" +
                    "r = der Reduktions- oder Verteilungsfaktor im Körper: \n" +
                    "bei Männer liegt dieser Wert zwischen: 0,68–0,7 \n" +
                    "und bei Frauen: 0,55–0,60"
        )
        //Aktion wenn auf Okay geklickt wurde
        builder.setPositiveButton("Ok") { _, _ ->
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
        builder.setTitle("gültige Werte")
        //Inhalt des Fensters
        builder.setMessage(
                    "Gewicht: \n - Angabe des Körpergewichts in kg \n " +
                    "- Dabei gibst du die Zahl ein, die bei dir auf der Waage steht \n" +
                            "\n"+
                    "Geschlecht: \n - wir unterscheiden hier zwischen männlich und weiblich, da die Berechnung derzeit nur dafür ausgelegt ist\n" +
                            "\n"+
                    "Dauer: \n - die Angabe ist in Std., dass heißt wenn du 1 Stunde Alkohol getrunken hast, gibst du 1 h ein\n" +
                            "\n"+
                    "Menge: \n - du hast die Auswahl zwischen verschiedenen gängigen Alkoholtypen, die voreingestellten Vol. Werte orientieren sich an den typischen Werten.\n"
        )
        //Aktion wenn auf Okay geklickt wurde
        builder.setPositiveButton("Ok") { _, _ ->
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
        builder.setTitle("Promille-Wert")
        //Inhalt des Fensters
        builder.setMessage(
            "0,1‰ - 0,3‰: \n " +
                    "- man ist kontaktfreudiger\n" +
                    "- man macht erste Fehler" +
                    "\n" +
                    "\n" +
                    "0,3‰ - 0,5‰: \n" +
                    "- verlangsamte Reaktion und Aufmerksamkeit \n" +
                    "- Risikobereitschaft steigt \n" +
                    "- man kann weniger sehen und hören" +
                    "- man schätzt Geschwindigkeite falsch ein"+
                    "\n"+
                    "\n" +
                "0,5‰ - 0,8‰: \n" +
                "- fehlende Konzentrationsfähigkeiten \n" +
                    "- verkleindertes Sichtfeld \n" +
                    "- deutlich schlechtere Reaktionszeit \n" +
                    "- Gleichgewichtsstörrungen"+
                    "\n"+
                    "\n" +
                    "1,0‰ - 2,0‰: \n" +
                    "- schlechtere Sehfähigkeit\n" +
                    "- kein Räumliches Sehen mehr \n" +
                    "- Sprach- Reaktions- und Gleichgewichtsprobleme sind sehr stark ausgeprägt \n" +
                    "- Orientierungslosigkeit"+
                    "\n"+
                    "\n" +
                    "2,0‰ - 3,0‰: \n" +
                    "- unkontrollierte bewegungen \n" +
                    "- Erbrechen \n" +
                    "- Filmriss ist vorprogrammiert \n" +
                    "- LEBENSGEFAHR vorallen bei Jugendlichen"+
                    "\n"+
                    "\n" +
                    "ab 3,0‰: \n" +
                    "- LEBENSGEFAHR \n" +
                    "- Bewusstlosigkeit \n" +
                    "- Gefahren durch schwache Atmung"
        )
        //Aktion wenn auf Okay geklickt wurde
        builder.setPositiveButton("Ok") { _, _ ->
        }
        //Dialog erstellen
        val infoFormularDialog: AlertDialog = builder.create()
        infoFormularDialog.setCancelable(false)
        infoFormularDialog.show()
    }



}
