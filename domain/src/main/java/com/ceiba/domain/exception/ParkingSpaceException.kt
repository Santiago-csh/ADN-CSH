package com.ceiba.domain.exception

import java.lang.StringBuilder

private const val PARKING_SPACE_MESSAGE = "Lo sentimos, el parqueadero no tiene espacio para "

class ParkingSpaceException(vehicleType: String): BusinessException(StringBuilder().append(PARKING_SPACE_MESSAGE).append("${vehicleType}.").toString())