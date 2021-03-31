package com.ceiba.domain.exception

private const val INVALID_PLATE_MESSAGE = "La placa ingresada no es valida."

class PlateFormatException: BusinessException(INVALID_PLATE_MESSAGE)