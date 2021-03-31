package com.ceiba.domain.exception

private const val RENTED_VEHICLE_MESSAGE = "El vehiculo ya se encuentra alquilado."

class RentedVehicleException: BusinessException(RENTED_VEHICLE_MESSAGE)