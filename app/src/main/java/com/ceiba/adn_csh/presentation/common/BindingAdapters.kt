package com.ceiba.adn_csh.presentation.common

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.utilidades.Constantes
import com.squareup.picasso.Picasso
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*

object BindingAdapters {

    private var vehicleTypeImage: Map<String, Int>

    init {
        vehicleTypeImage = mapOf(
            Constantes.CAR to R.drawable.ic_baseline_directions_car_black_24,
            Constantes.MOTORCYCLE to R.drawable.ic_baseline_motorcycle_black_24)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImageVehicle(imageView: ImageView, vehicleType: String?) {
        for ((key, value) in vehicleTypeImage) {
            if (key == vehicleType) {
                Picasso.get().load(value).error(value).into(imageView)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("visibility")
    fun visible(view: View, activeVehicle: Boolean) {
        view.visibility = if(!activeVehicle) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("valuePrice")
    fun bindPriceVehicle(textView: TextView, valuePrice: Double) {
        val formatoImporte = NumberFormat.getCurrencyInstance(Locale("es","CO"));
        textView.setText(StringBuilder().append("Precio: ").append(formatoImporte.format(valuePrice)))
    }
}