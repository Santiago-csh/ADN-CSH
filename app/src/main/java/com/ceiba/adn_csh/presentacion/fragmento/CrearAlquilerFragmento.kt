package com.ceiba.adn_csh.presentacion.fragmento

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
import com.ceiba.adn_csh.App
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.FragmentCrearAlquilerBinding
import com.ceiba.adn_csh.dominio.excepciones.ExcepcionNegocio
import com.ceiba.adn_csh.dominio.modelo.Alquiler
import com.ceiba.adn_csh.dominio.modelo.Vehiculo
import com.ceiba.adn_csh.infraestructura.view_model.AlquilerViewModel
import com.ceiba.adn_csh.utilidades.Constantes
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CrearAlquilerFragmento : Fragment() {

    private lateinit var fragmentCrearAlquilerBinding: FragmentCrearAlquilerBinding

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private val alquilerViewModel by lazy {
        ViewModelProvider(this, modelFactory)[AlquilerViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this@CrearAlquilerFragmento)
        super.onAttach(context)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentCrearAlquilerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_crear_alquiler, container, false)
        return fragmentCrearAlquilerBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tiposVehiculos = arrayOf(Constantes.AUTOMOVIL, Constantes.MOTOCICLETA)
        val arrayAdapterSpinnerVehiculo: ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, tiposVehiculos)
        fragmentCrearAlquilerBinding.spinnerTipoVehiculo.adapter = arrayAdapterSpinnerVehiculo

        fragmentCrearAlquilerBinding.btnAgregarAlquiler.setOnClickListener {
            validarInformacionCompletaAlquiler()
        }
    }

    private fun validarInformacionCompletaAlquiler() {
        val placa = fragmentCrearAlquilerBinding.etVehiculoPlaca.text.toString()
        val cilindraje = fragmentCrearAlquilerBinding.etVehiculoCilindraje.text.toString()
        val tipoVehiculo = fragmentCrearAlquilerBinding.spinnerTipoVehiculo.selectedItem.toString()
        if(placa.isEmpty()){
            Toast.makeText(requireActivity(), "Debes ingresar la placa.", Toast.LENGTH_SHORT).show()
        }
        if(placa.isNotEmpty() && cilindraje.isEmpty()){
            Toast.makeText(requireActivity(), "Debes ingresar el cilindraje.", Toast.LENGTH_SHORT).show()
        }
        if(placa.isNotEmpty() && cilindraje.isNotEmpty()){
            crearAlquiler(placa, cilindraje.toInt(), tipoVehiculo)
        }
    }

    private fun crearAlquiler(placa: String, cilindraje: Int, tipoVehiculo: String) {
        val vehiculo = Vehiculo(placa, cilindraje, tipoVehiculo)
        val alquiler = Alquiler(vehiculo = vehiculo)
        lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO){ alquilerViewModel.crearAlquiler(alquiler) }
                Toast.makeText(requireActivity(), "Registro exitoso.", Toast.LENGTH_SHORT).show()
            }catch (e: ExcepcionNegocio){
                Toast.makeText(requireActivity(), e.message, Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(requireActivity(), "El alquiler no se pudo agregar, intentalo mas tarde.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}