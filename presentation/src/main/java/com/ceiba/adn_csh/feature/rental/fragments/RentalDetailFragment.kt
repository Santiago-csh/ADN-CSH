package com.ceiba.adn_csh.feature.rental.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.FragmentRentalDetailBinding
import com.ceiba.adn_csh.feature.rental.viewmodel.RentalViewModel
import com.ceiba.domain.model.Rental
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class RentalDetailFragment : Fragment() {

    private lateinit var rentalDetailFragmentBinding: FragmentRentalDetailBinding

    @Inject
    lateinit var modelViewFactory: ViewModelProvider.Factory

    private val rentalViewModel by lazy {
        ViewModelProvider(this, modelViewFactory)[RentalViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this@RentalDetailFragment)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rentalDetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_rental_detail, container, false)
        val idRental = requireArguments().getLong(getString(R.string.rental))
        idRental.let {rentalViewModel.fetchRental(it) }

        rentalDetailFragmentBinding.btnMakePayment.setOnClickListener {
            try {
                rentalViewModel.updateRentalByPayment().observe(requireActivity(), Observer {
                    rentalDetailFragmentBinding.rental = it
                    Toast.makeText(requireActivity(), getString(R.string.successful_payment), Toast.LENGTH_SHORT).show()
                })
            }catch (e: Exception){
                Log.e("UpdateRental", e.message, e)
                Toast.makeText(requireActivity(), getString(R.string.rent_could_not_be_added_please_try_again_later), Toast.LENGTH_SHORT).show()
            }
        }

        return rentalDetailFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rentalViewModel.rentalDetail.observe(requireActivity(), Observer {
            rentalDetailFragmentBinding.rental = it
        })
    }
}