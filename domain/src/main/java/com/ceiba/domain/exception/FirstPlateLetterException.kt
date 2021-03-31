package com.ceiba.domain.exception

private const val UNAUTHORIZED_ENTRY_MESSAGE = "No esta autorizado tu ingreso."

class FirstPlateLetterException: BusinessException(UNAUTHORIZED_ENTRY_MESSAGE)