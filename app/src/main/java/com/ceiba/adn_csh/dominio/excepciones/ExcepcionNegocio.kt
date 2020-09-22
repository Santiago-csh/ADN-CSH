package com.ceiba.adn_csh.dominio.excepciones

class ExcepcionNegocio(val mensaje: String): Exception(){
    override val message: String = mensaje
}