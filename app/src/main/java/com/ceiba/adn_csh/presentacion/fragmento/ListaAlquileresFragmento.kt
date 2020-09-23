package com.ceiba.adn_csh.presentacion.fragmento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.FragmentListaAlquileresBinding
import com.ceiba.adn_csh.infraestructura.view_model.AlquilerViewModel
import javax.inject.Inject

class ListaAlquileresFragmento : Fragment() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private val alquilerViewModel by lazy {
        ViewModelProvider(this, modelFactory)[AlquilerViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentListaAlquileresBinding: FragmentListaAlquileresBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lista_alquileres, container, false)
        return fragmentListaAlquileresBinding.root
    }
}