package com.example.energywise.activity

import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.energywise.R
import com.example.energywise.model.Comunidade
import com.example.energywise.recyclerview.AdapterListaCondominio

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val pesquisarComunidade: EditText = findViewById(R.id.HOME_CampoPesquisarComunidade_EditText)
        val recyclerViewEstoqueCritico: RecyclerView = findViewById(R.id.HOME_RecyclerViewEstoqueCritico_RecyclerView)
        recyclerViewEstoqueCritico.adapter = AdapterListaCondominio(this, listOf(
            Comunidade(
                nome = "Vila Verde",
                quantidadeHabitantes = 150,
                energiaConsumida = 1200,
                energiaEmEstoque = 500,
                observacao = "Requer expansão de geração solar.",
                estoqueRecomendado = 700
            ),
            Comunidade(
                nome = "Praia do Sol",
                quantidadeHabitantes = 300,
                energiaConsumida = 2500,
                energiaEmEstoque = 1800,
                estoqueRecomendado = 2200
            ),
            Comunidade(
                nome = "Ponte Alta",
                quantidadeHabitantes = 80,
                energiaConsumida = 600,
                energiaEmEstoque = 200,
                observacao = "Demanda baixa, geração suficiente.",
                estoqueRecomendado = 300
            )
        ))
        recyclerViewEstoqueCritico.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        pesquisarComunidade.clearFocus()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}