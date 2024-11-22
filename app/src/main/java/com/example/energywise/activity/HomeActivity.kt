package com.example.energywise.activity

import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.energywise.R
import com.example.energywise.model.Condominio
import com.example.energywise.recyclerview.AdapterListaCondominio
import com.example.energywise.utils.Utils
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeActivity : AppCompatActivity() {

    private var db = Firebase.firestore
    private val listaCondominios: MutableList<Condominio> = mutableListOf()
    private val utils: Utils = Utils()

    private lateinit var recyclerViewEstoqueCritico: RecyclerView
    private lateinit var recyclerViewMaioresCondominios: RecyclerView
    private lateinit var recyclerViewMaioresConsumos: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val pesquisarComunidade: EditText = findViewById(R.id.HOME_CampoPesquisarComunidade_EditText)

        recyclerViewEstoqueCritico = findViewById(R.id.HOME_RecyclerViewEstoqueCritico_RecyclerView)
        recyclerViewMaioresCondominios = findViewById(R.id.HOME_RecyclerViewMaioresCondominios_RecyclerView)
        recyclerViewMaioresConsumos = findViewById(R.id.HOME_RecyclerViewMaioresConsumos_RecyclerView)

        recyclerViewMaioresCondominios.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewEstoqueCritico.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewMaioresConsumos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        FirebaseApp.initializeApp(this)
        db = FirebaseFirestore.getInstance()

        pesquisarComunidade.clearFocus()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun onResume() {
        super.onResume()
        carregarDados()
    }

    private fun carregarDados() {
        listaCondominios.clear() // Limpa a lista para evitar duplicação dos dados
        db.collection("condominios")
            .get()
            .addOnSuccessListener { resultado ->
                for (condominio in resultado) {
                    listaCondominios.add(
                        Condominio(
                            condominio.id,
                            condominio.get("nome").toString(),
                            condominio.get("quantidadeHabitantes").toString().toInt(),
                            condominio.get("energiaConsumida").toString().toDouble(),
                            condominio.get("energiaEmEstoque").toString().toDouble(),
                            condominio.get("estoqueRecomendado").toString().toDouble(),
                            condominio.get("dataMedicao"),
                            condominio.get("observacao").toString(),
                        )
                    )
                }

                recyclerViewMaioresCondominios.adapter = AdapterListaCondominio(this, geraListaMaioresCondominios(listaCondominios))
                recyclerViewEstoqueCritico.adapter = AdapterListaCondominio(this, geraListaCondominioComEstoqueCritico(listaCondominios))
                recyclerViewMaioresConsumos.adapter = AdapterListaCondominio(this, geraListaCondominioComMaioresConsumos(listaCondominios))
            }
            .addOnFailureListener { e ->
                utils.criarAlerta(this, "Erro", "Erro ao recuperar lista: $e", 3000, R.drawable.warning_circle)
            }
    }

    fun geraListaMaioresCondominios(listaCondominios: MutableList<Condominio>): MutableList<Condominio> {
        val condominiosOrdenados = listaCondominios.sortedByDescending { condominio: Condominio -> condominio.quantidadeHabitantes }.toMutableList()

        return if (condominiosOrdenados.size >= 5) condominiosOrdenados.subList(0, 5) else condominiosOrdenados
    }

    fun geraListaCondominioComEstoqueCritico(listaCondominios: MutableList<Condominio>): MutableList<Condominio> {
        val condominiosOrdenados: MutableList<Condominio> = mutableListOf()
        listaCondominios.forEach { condominio: Condominio ->
            if (condominio.energiaEmEstoque < (condominio.estoqueRecomendado * 0.3)) condominiosOrdenados.add(condominio)
        }
        return condominiosOrdenados
    }

    fun geraListaCondominioComMaioresConsumos(listaCondominios: MutableList<Condominio>): MutableList<Condominio> {
        val condominiosOrdenados = listaCondominios.sortedByDescending { condominio: Condominio -> condominio.energiaConsumida }.toMutableList()
        return if (condominiosOrdenados.size >= 5) condominiosOrdenados.subList(0, 5) else condominiosOrdenados
    }
}
