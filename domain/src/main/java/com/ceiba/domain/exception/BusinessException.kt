package com.ceiba.domain.exception

import java.lang.RuntimeException

open class BusinessException(businessExceptionMessage: String): RuntimeException(businessExceptionMessage)