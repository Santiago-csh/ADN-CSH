package com.ceiba.adn_csh.presentation.rental

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.RentalCreateFragmentBinding
import com.ceiba.adn_csh.dominio.exception.BusinessException
import com.ceiba.adn_csh.dominio.model.Rental
import com.ceiba.adn_csh.dominio.model.Vehicle
import com.ceiba.adn_csh.infraestructure.view_model.RentalViewModel
import com.ceiba.adn_csh.utilidades.Constantes
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class RentalCreateFragment : Fragment() {

    private lateinit var rentalCreateFragmentBinding: RentalCreateFragmentBinding

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private val rentalViewModel by lazy {
        ViewModelProvider(this, modelFactory)[RentalViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this@RentalCreateFragment)
        super.onAttach(context)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rentalCreateFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.rental_create_fragment, container, false)
        return rentalCreateFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val typeVehicles = arrayOf(Constantes.CAR, Constantes.MOTORCYCLE)
        val arrayAdapterSpinnerVehiculo: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, typeVehicles)
        rentalCreateFragmentBinding.spinnerVehicleType.adapter = arrayAdapterSpinnerVehiculo

        rentalCreateFragmentBinding.btnAddRental.setOnClickListener {
            validateFullRentalInformation()
        }
    }

    private fun validateFullRentalInformation() {
        val plate = rentalCreateFragmentBinding.etPlateVehicle.text.toString()
        val cylinderCapacity = rentalCreateFragmentBinding.etCylinderCapacityVehicle.text.toString()
        val typeVehicle = rentalCreateFragmentBinding.spinnerVehicleType.selectedItem.toString()
        if(plate.isEmpty()){
            Toast.makeText(requireActivity(), "Debes ingresar la placa.", Toast.LENGTH_SHORT).show()
        }
        if(plate.isNotEmpty() && cylinderCapacity.isEmpty()){
            Toast.makeText(requireActivity(), "Debes ingresar el cilindraje.", Toast.LENGTH_SHORT).show()
        }
        if(plate.isNotEmpty() && cylinderCapacity.isNotEmpty()){
            createRental(plate, cylinderCapacity.toInt(), typeVehicle)
        }
    }

    private fun createRental(plate: String, cylinderCapacity: Int, typeVehicle: String) {
        val vehicle = Vehicle(plate, cylinderCapacity, typeVehicle)
        val rental = Rental(vehicle = vehicle, arrivalDate = Date())
        lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO){ rentalViewModel.createRental(rental) }
                Toast.makeText(requireActivity(), "Registro exitoso.", Toast.LENGTH_SHORT).show()
                rentalCreateFragmentBinding.etPlateVehicle.setText("")
                rentalCreateFragmentBinding.etCylinderCapacityVehicle.setText("")
            }catch (e: BusinessException){
                Toast.makeText(requireActivity(), e.message, Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(requireActivity(), "El alquiler no se pudo agregar, intentalo mas tarde.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}