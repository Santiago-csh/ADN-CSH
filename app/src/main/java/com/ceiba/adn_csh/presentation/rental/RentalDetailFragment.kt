package com.ceiba.adn_csh.presentation.rental

import android.content.Context
import android.os.Bundle
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
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.infraestructure.view_model.RentalViewModel
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
        val rentalId = requireArguments().getLong("rentalId")
        rentalViewModel.getActiveRentalById(rentalId)

        rentalDetailFragmentBinding.btnMakePayment.setOnClickListener {
            lifecycleScope.launch {
                try {
                    withContext(Dispatchers.IO){ rentalViewModel.updateRentalByPayment() }
                    Toast.makeText(requireActivity(), "Pago exitoso.", Toast.LENGTH_SHORT).show()
                }catch (e: Exception){
                    Toast.makeText(requireActivity(), "No se pudo realizar el pago, intentalo mas tarde.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return rentalDetailFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callRental()
    }

    private fun callRental() {
        rentalViewModel.rental.observe(viewLifecycleOwner, Observer { rental ->
            rentalDetailFragmentBinding.rental = rental
        })
    }
}