package com.example.energywise.model

import com.google.firebase.Timestamp

data class Condominio(
    var nome: String = "",
    var quantidadeHabitantes: Int = 0,
    var energiaConsumida: Double = 0.0,
    var energiaEmEstoque: Double = 0.0,
    var estoqueRecomendado: Double = 0.0,
    var dataMedicao: Any? = Timestamp.now(),
    var observacao: String? = "Sem Observacao"
) {
    constructor(nome: String, quantidadeHabitantes: Int, energiaConsumida: Double, energiaEmEstoque: Double, estoqueRecomendado: Double, observacao: String?) : this() {
        this.nome = nome
        this.quantidadeHabitantes = quantidadeHabitantes
        this.energiaConsumida = energiaConsumida
        this.estoqueRecomendado = estoqueRecomendado
        this.energiaEmEstoque = energiaEmEstoque
        this.observacao = observacao
    }

    override fun toString(): String {
        return "Condominio(nome='$nome', quantidadeHabitantes=$quantidadeHabitantes, energiaConsumida=$energiaConsumida, energiaEmEstoque=$energiaEmEstoque, estoqueRecomendado=$estoqueRecomendado, dataMedicao=$dataMedicao, observacao=$observacao)"
    }

}