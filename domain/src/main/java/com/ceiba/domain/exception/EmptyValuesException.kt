package com.ceiba.domain.exception

import com.ceiba.domain.exception.BusinessException

class EmptyValuesException(message: String): BusinessException(message)