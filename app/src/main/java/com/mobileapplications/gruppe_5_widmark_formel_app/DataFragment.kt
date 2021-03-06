package com.mobileapplications.gruppe_5_widmark_formel_app

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultDatabase
import com.mobileapplications.gruppe_5_widmark_formel_app.database.ResultRepository
import com.mobileapplications.gruppe_5_widmark_formel_app.databinding.FragmentDataBinding
import com.mobileapplications.gruppe_5_widmark_formel_app.model.MainActivityViewModel
import com.mobileapplications.gruppe_5_widmark_formel_app.model.MainActivityViewModelFactory


class DataFragment : Fragment() {
    private lateinit var binding: FragmentDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data, container, false)

        //Verbindung zum ViewModel aufbauen
        val database = ResultDatabase.getInstance(requireActivity())
        val resultRepository = ResultRepository(database.resultDao)
        val viewModelFactory = MainActivityViewModelFactory(resultRepository)
        val dataFragmentViewmodel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MainActivityViewModel::class.java)

        setHasOptionsMenu(true)

        // Erstellung einer RecyclerViews mit Hilfe eines Adapters
        binding.lifecycleOwner = this
        val adapter = ResultAdapter(listener = {
            dataFragmentViewmodel.deleteResult(it)
        })
        binding.resultList.layoutManager = LinearLayoutManager(requireActivity())
        binding.resultList.adapter = adapter
        dataFragmentViewmodel.results.observe(requireActivity(), Observer {
            adapter.submitList(it)
        })

        //Wenn der Button "alles L??schen" geklickt wird, sollen alle Ergebnisse gel??scht werden,
        //deswegen wird dann die Methode deletaAll() aufgerufen
        binding.deleteAllButton.setOnClickListener{
            dataFragmentViewmodel.deleteAll()
        }


        return binding.root
    }

    //Wenn man auf die Men??leiste klickt, sollte die zuvor in der options_menu.xml erstellte Liste angezeigt werden
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    //wenn auf ein bestimmtes Element geklickt werden, soll mit Hilfe des Navigationspfad auf die zugeh??rige Seite verwiesen werden
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //es wird mit Hilfe der Id geschaut welches Item ausgew??hlt wird
        return when(item.itemId){
            //wenn das Hile-item ausgew??hlt wird, wird versucht mit Hilfe des Navigationspfad toHelpFragment ausgef??hrt
            //wenn der das nicht klappt, gibt die Methode false zur??ck, damit die App nicht abst??rtzt
            R.id.menuHelp-> {
                try {
                    view?.findNavController()?.navigate(R.id.dataToHelp)
                    true
                } catch (ex:Exception) {
                    false
                }
            }

            //das gleiche gilt wenn das Item f??r alle ergebnisse geklickt wird ausgef??hrt
            R.id.menuStart-> {
                try {
                    view?.findNavController()?.navigate(R.id.dataToStart)
                    true
                } catch (ex:Exception) {
                    false
                }

            }
            else -> false
        }

    }
}