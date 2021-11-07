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

        val database = ResultDatabase.getInstance(requireContext())
        val resultRepository = ResultRepository(database.resultDao)
        val viewModelFactory = MainActivityViewModelFactory(resultRepository)
        val dataFragmentViewmodel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MainActivityViewModel::class.java)

        setHasOptionsMenu(true)

        binding.lifecycleOwner = this

        val adapter = ResultAdapter()
        binding.resultList.layoutManager = LinearLayoutManager(requireContext())
        binding.resultList.adapter = adapter

        dataFragmentViewmodel.results.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

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
                    view?.findNavController()?.navigate(R.id.dataToHelp)
                    true
                } catch (ex:Exception) {
                    false
                }

            }
            //das gleiche gilt wenn das Item für alle ergebnisse geklickt wird ausgeführt
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