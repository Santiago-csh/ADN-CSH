package com.ceiba.adn_csh.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ceiba.adn_csh.R
import com.squareup.picasso.Picasso
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*

private const val CAR = "AUTOMOVIL"
private const val MOTORCYCLE = "MOTOCICLETA"
private const val CAR_IMAGE = R.drawable.ic_baseline_directions_car_black_24
private const val MOTORCYCLE_IMAGE = R.drawable.ic_baseline_motorcycle_black_24
private val VEHICLE_IMAGE_TYPE = mapOf(CAR to CAR_IMAGE, MOTORCYCLE to MOTORCYCLE_IMAGE)

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImageVehicle(imageView: ImageView, vehicleType: String?) {
        for ((key, value) in VEHICLE_IMAGE_TYPE) {
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
        val formatoImporte = NumberFormat.getCurrencyInstance(Locale("es","CO"))
        textView.setText(StringBuilder().append("Precio: ").append(formatoImporte.format(valuePrice)))
    }

    @JvmStatic
    @BindingAdapter("valueCylinder")
    fun bindCylinderVehicle(textView: TextView, valueCylinder: Int) {
        textView.setText(StringBuilder().append("Cilindraje: ").append(valueCylinder))
    }
}