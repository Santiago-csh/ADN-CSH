package com.ceiba.adn_csh.infraestructure.db

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(dateLong: Long) = Date(dateLong)

    @TypeConverter
    fun fromDate(date: Date) = date.time

}