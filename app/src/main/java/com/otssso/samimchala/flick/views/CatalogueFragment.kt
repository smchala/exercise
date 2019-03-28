package com.otssso.samimchala.flick.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.otssso.samimchala.flick.R
import com.otssso.samimchala.flick.databinding.CatalogueFragmentBinding
import com.otssso.samimchala.flick.viewModels.CatalogueViewModel
import com.otssso.samimchala.flick.viewModels.SharedViewModel
import com.otssso.samimchala.flick.viewModels.SharedViewModel.Companion.selectedMovie

class CatalogueFragment : Fragment() {

    private lateinit var catalogueViewModel: CatalogueViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catalogueViewModel = ViewModelProviders.of(this).get(CatalogueViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val catalogueFragmentBinding: CatalogueFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.catalogue_fragment, container, false)

        catalogueFragmentBinding.viewModel = catalogueViewModel
        catalogueFragmentBinding.setLifecycleOwner(this)
        navController = findNavController(this)

        SharedViewModel.ticketsLiveData.observe(this, Observer {
            catalogueFragmentBinding.root.findViewById<RecyclerView>(R.id.movies_recycler_view)
                .adapter?.notifyDataSetChanged()
        })

        catalogueViewModel.navigateToSaleCatalogueEvent.observe(this, Observer {
            //this is required for now, when we press back button in sale catalogue nav controller still holds action_CatalogueFragment_to_saleFragment action which makes it crash :(
            if (navController.currentDestination?.id == R.id.CatalogueFragment) {
//                var bundle = Bundle()
//                bundle.putInt("position", it)
                navController.navigate(R.id.action_CatalogueFragment_to_saleFragment)//, bundle)
                //or use the sharedViewModel
                selectedMovie.value = it
            }
        })
        return catalogueFragmentBinding.root
    }

    override fun onStop() {
        catalogueViewModel.cleanUpObservables()
        super.onStop()
    }
}