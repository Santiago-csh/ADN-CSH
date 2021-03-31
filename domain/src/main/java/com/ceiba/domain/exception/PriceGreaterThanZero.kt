package com.ceiba.domain.exception

private const val PRICE_GREATER_THAN_ZERO_MESSAGE = "El precio no puede ser menos a cero"

class PriceGreaterThanZero: BusinessException(PRICE_GREATER_THAN_ZERO_MESSAGE)