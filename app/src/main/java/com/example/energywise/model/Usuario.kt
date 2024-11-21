package com.example.energywise.model

import java.io.Serializable

data class Usuario(
    var nome: String = "",
    var email: String = "",
    var senha: String = ""
): Serializable{
    override fun toString(): String {
        return "Usuario(nome='$nome', email='$email', senha='$senha')"
    }
}