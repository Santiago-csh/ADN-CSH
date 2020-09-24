package com.ceiba.adn_csh.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.utilidades.Constantes
import com.squareup.picasso.Picasso

object BindingAdapters {

    private var vehicleTypeImage: Map<String, Int>

    init {
        vehicleTypeImage = mapOf(
            Constantes.CAR to R.drawable.ic_baseline_directions_car_black_24,
            Constantes.MOTORCYCLE to R.drawable.ic_baseline_motorcycle_black_24)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, vehicleType: String?) {
        for ((key, value) in vehicleTypeImage) {
            if (key == vehicleType) {
                Picasso.get().load(value).error(value).into(imageView)
            }
        }
    }
}