package com.example.energywise.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        val btnExcluir: Button = findViewById(R.id.DetalhesComunidade_ButtonExcluir_Button)
        val btnAtualizar: Button = findViewById(R.id.DetalhesComunidade_ButtonAtualizar_Button)

        val nomeComunidade: TextView = findViewById(R.id.DetalhesComunidade_NomeComunidade_TextView)
        val descricaoComunidade: TextView = findViewById(R.id.DetalhesComunidade_DescricaoComunidade_TextView)
        val qtdHabitantes: TextView = findViewById(R.id.DetalhesComunidade_QtdHabitantes_TextView)
        val energiaConsumida: TextView = findViewById(R.id.DetalhesComunidade_EnergiaConsumida_TextView)
        val energiaEmEstoque: TextView = findViewById(R.id.DetalhesComunidade_EnergiaEstoque_TexView)
        val estoqueRecomendado: TextView = findViewById(R.id.DetalhesComunidade_EstoqueRecomendado_TexView)

        val condominio = intent.getSerializableExtra("condominio") as? Condominio
        nomeComunidade.setText(condominio?.nome)
        descricaoComunidade.setText(condominio?.observacao)
        qtdHabitantes.setText(condominio?.quantidadeHabitantes.toString().plus(" Habitantes"))
        energiaConsumida.setText(condominio?.energiaConsumida.toString().plus(" kW’s consumido diariamente"))
        energiaEmEstoque.setText(condominio?.energiaEmEstoque.toString().plus(" Kw's estocados atualmente"))
        estoqueRecomendado.setText(condominio?.estoqueRecomendado.toString().plus(" Kw's recomendado"))

        btnAtualizar.setOnClickListener{
            val intent = Intent(this, AtualizarCondominio::class.java)
            intent.putExtra("condominio", condominio)
            startActivity(intent)
        }
    }
}