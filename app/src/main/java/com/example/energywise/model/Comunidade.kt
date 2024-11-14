package com.example.energywise.model

class Comunidade {
    private val nome: String
    private val quantidadeHabitantes: Int
    private val energiaConsumida: Int
    private val energiaEmEstoque: Int
    private var observacao: String? = null

    constructor(nome: String, quantidadeHabitantes: Int, energiaConsumida: Int, energiaEmEstoque: Int, observacao: String) {
        this.nome = nome
        this.quantidadeHabitantes = quantidadeHabitantes
        this.energiaConsumida = energiaConsumida
        this.energiaEmEstoque = energiaEmEstoque
        this.observacao = observacao
    }

    constructor(nome: String, quantidadeHabitantes: Int, energiaConsumida: Int, energiaEmEstoque: Int) {
        this.nome = nome
        this.quantidadeHabitantes = quantidadeHabitantes
        this.energiaConsumida = energiaConsumida
        this.energiaEmEstoque = energiaEmEstoque
    }

    override fun toString(): String {
        return "Comunidade(nome='$nome', quantidadeHabitantes=$quantidadeHabitantes, energiaConsumida=$energiaConsumida, energiaEmEstoque=$energiaEmEstoque, observacao=$observacao)"
    }
}