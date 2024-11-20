package com.example.energywise.model

import com.google.firebase.Timestamp
import java.io.Serializable

data class Condominio(
    var id: String = "",
    var nome: String = "",
    var quantidadeHabitantes: Int = 0,
    var energiaConsumida: Double = 0.0,
    var energiaEmEstoque: Double = 0.0,
    var estoqueRecomendado: Double = 0.0,
    @Transient var dataMedicao: Any? = Timestamp.now(),
    var observacao: String? = ""
): Serializable {
    constructor(nome: String, quantidadeHabitantes: Int, energiaConsumida: Double, energiaEmEstoque: Double, estoqueRecomendado: Double, observacao: String?) : this() {
        this.nome = nome
        this.quantidadeHabitantes = quantidadeHabitantes
        this.energiaConsumida = energiaConsumida
        this.estoqueRecomendado = estoqueRecomendado
        this.energiaEmEstoque = energiaEmEstoque
        this.observacao = observacao
    }

    constructor(id: String, nome: String, quantidadeHabitantes: Int, energiaConsumida: Double, energiaEmEstoque: Double, estoqueRecomendado: Double, observacao: String?): this(){
        this.id = id
        this.nome = nome
        this.quantidadeHabitantes = quantidadeHabitantes
        this.energiaConsumida = energiaConsumida
        this.estoqueRecomendado = estoqueRecomendado
        this.energiaEmEstoque = energiaEmEstoque
        this.observacao = observacao
    }

    constructor(nome: String, quantidadeHabitantes: Int, energiaConsumida: Double, energiaEmEstoque: Double, observacao: String?): this(){
        this.nome = nome
        this.quantidadeHabitantes = quantidadeHabitantes
        this.energiaConsumida = energiaConsumida
        this.energiaEmEstoque = energiaEmEstoque
        this.observacao = observacao
    }

    override fun toString(): String {
        return "Condominio(nome='$nome', quantidadeHabitantes=$quantidadeHabitantes, energiaConsumida=$energiaConsumida, energiaEmEstoque=$energiaEmEstoque, estoqueRecomendado=$estoqueRecomendado, dataMedicao=$dataMedicao, observacao=$observacao)"
    }

}