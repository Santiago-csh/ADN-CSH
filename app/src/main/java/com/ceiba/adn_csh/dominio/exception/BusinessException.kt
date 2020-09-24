package com.ceiba.adn_csh.dominio.exception

class BusinessException(val businessExceptionMessage: String): Exception(){
    override val message: String = businessExceptionMessage
}