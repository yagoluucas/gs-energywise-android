package com.example.energywise.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.energywise.R
import com.example.energywise.model.Condominio

class DetalhesComunidade : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_comunidade)

//        val btnExcluir: Button = findViewById(R.id.DetalhesComunidade_BotaoExcluir_Button)
//        val btnAtualizar: Button = findViewById(R.id.DetalhesComunidade_BotaoAtualizar_Button)

        val condominio = intent.getSerializableExtra("condominio") as? Condominio

        Log.v("condominio", condominio.toString())
    }
}