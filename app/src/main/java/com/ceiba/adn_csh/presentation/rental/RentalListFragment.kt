package com.ceiba.adn_csh.presentation.rental

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.RentalListFragmentBinding
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.infraestructure.view_model.RentalViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RentalListFragment : Fragment() {

    private lateinit var rentalListFragmentBinding: RentalListFragmentBinding

    @Inject
    lateinit var modelViewFactory: ViewModelProvider.Factory

    private val rentalViewModel by lazy {
        ViewModelProvider(this, modelViewFactory)[RentalViewModel::class.java]
    }

    private lateinit var rentalListAdapter: RentalListAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this@RentalListFragment)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rentalListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.rental_list_fragment, container, false)
        return rentalListFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rentalListAdapter = RentalListAdapter(object : RentalListAdapter.RentalDetailClickCallback {
            override fun onClick(rental: Rental) {
                val bundle = bundleOf("rentalId" to rental.id)
                findNavController().navigate(R.id.actionRentalDetailFragment, bundle)
            }
        })
        rentalListFragmentBinding.recyclerRentalList.adapter = rentalListAdapter
        callRentalList()
    }

    private fun callRentalList() {
        rentalViewModel.rentals.observe(viewLifecycleOwner, Observer { rentals ->
            rentalListAdapter.replace(rentals)
        })
    }
}