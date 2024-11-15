package com.example.energywise.model

class Comunidade {
    val nome: String
    val quantidadeHabitantes: Int
    val energiaConsumida: Int
    val energiaEmEstoque: Int
    val estoqueRecomendado: Int
    var observacao: String? = null

    constructor(nome: String, quantidadeHabitantes: Int, energiaConsumida: Int, energiaEmEstoque: Int, observacao: String, estoqueRecomendado: Int) {
        this.nome = nome
        this.quantidadeHabitantes = quantidadeHabitantes
        this.energiaConsumida = energiaConsumida
        this.energiaEmEstoque = energiaEmEstoque
        this.estoqueRecomendado = estoqueRecomendado
        this.observacao = observacao
    }

    constructor(nome: String, quantidadeHabitantes: Int, energiaConsumida: Int, energiaEmEstoque: Int, estoqueRecomendado: Int) {
        this.nome = nome
        this.quantidadeHabitantes = quantidadeHabitantes
        this.energiaConsumida = energiaConsumida
        this.estoqueRecomendado = estoqueRecomendado
        this.energiaEmEstoque = energiaEmEstoque
    }

    override fun toString(): String {
        return "Comunidade(nome='$nome', quantidadeHabitantes=$quantidadeHabitantes, energiaConsumida=$energiaConsumida, energiaEmEstoque=$energiaEmEstoque, estoqueRecomendado=$estoqueRecomendado, observacao=$observacao)"
    }
}