package com.example.energywise.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.energywise.R
import com.example.energywise.model.Condominio
import com.example.energywise.recyclerview.AdapterListaCondominio
import com.google.firebase.FirebaseApp
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore


class HomeActivity: AppCompatActivity() {

    private var db = Firebase.firestore

    private val listaComunidade: MutableList<Condominio> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val pesquisarComunidade: EditText = findViewById(R.id.HOME_CampoPesquisarComunidade_EditText)
        val recyclerViewEstoqueCritico: RecyclerView = findViewById(R.id.HOME_RecyclerViewEstoqueCritico_RecyclerView)
        val recyclerViewMaioresCondominios: RecyclerView = findViewById(R.id.HOME_RecyclerViewMaioresCondominios_RecyclerView)

        recyclerViewMaioresCondominios.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewEstoqueCritico.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        FirebaseApp.initializeApp(this)
        db = FirebaseFirestore.getInstance()

        db.collection("condominios").
                get()
            .addOnSuccessListener { resultado ->
                for (condominio in resultado) {
                    listaComunidade.add(
                        Condominio(
                            condominio.get("nome").toString(),
                            condominio.get("quantidadeHabitantes").toString().toInt(),
                            condominio.get("energiaConsumida").toString().toDouble(),
                            condominio.get("energiaEmEstoque").toString().toDouble(),
                            condominio.get("estoqueRecomendado").toString().toDouble(),
                            condominio.get("dataMedicao"),
                            condominio.get("observacao").toString(),
                        )
                    )

                    recyclerViewMaioresCondominios.adapter = AdapterListaCondominio(this, listaComunidade)
                    recyclerViewEstoqueCritico.adapter = AdapterListaCondominio(this, listaComunidade)
                }
            }
            .addOnFailureListener {e ->
                Log.v("Erro", e.toString())
            }

        pesquisarComunidade.clearFocus()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}