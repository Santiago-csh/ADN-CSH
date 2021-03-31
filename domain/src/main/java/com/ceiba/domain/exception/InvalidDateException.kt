package com.ceiba.domain.exception

private const val THE_DEPARTURE_DATE_IS_GREATER_THAN_THE_ARRIVAL_DATE_MESSAGE = "La fecha de salida no puede ser menso a la fecha de llegada."

class InvalidDateException: BusinessException(THE_DEPARTURE_DATE_IS_GREATER_THAN_THE_ARRIVAL_DATE_MESSAGE)