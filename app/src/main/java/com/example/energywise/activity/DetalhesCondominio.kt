package com.example.energywise.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.energywise.R
import com.example.energywise.model.Condominio
import com.google.firebase.firestore.FirebaseFirestore
import android.app.AlertDialog
import com.example.energywise.utils.Utils
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetalhesCondominio : AppCompatActivity() {

    private var db = Firebase.firestore
    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {

        FirebaseApp.initializeApp(this)
        db = FirebaseFirestore.getInstance()

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

        // Recupera o objeto condominio da intent
        val condominio = intent.getSerializableExtra("condominio") as? Condominio
        nomeComunidade.setText(condominio?.nome)
        descricaoComunidade.setText(condominio?.observacao)
        qtdHabitantes.setText(condominio?.quantidadeHabitantes.toString().plus(" Habitantes"))
        energiaConsumida.setText(condominio?.energiaConsumida.toString().plus(" kW’s consumido diariamente"))
        energiaEmEstoque.setText(condominio?.energiaEmEstoque.toString().plus(" Kw's estocados atualmente"))
        estoqueRecomendado.setText(condominio?.estoqueRecomendado.toString().plus(" Kw's recomendado"))

        // Ação para o botão de atualizar
        btnAtualizar.setOnClickListener {
            val intent = Intent(this, AtualizarCondominio::class.java)
            intent.putExtra("condominio", condominio)
            startActivity(intent)
        }

        // Ação para o botão de excluir
        btnExcluir.setOnClickListener {
            val condominioId: String = condominio?.id ?: ""

            if (condominioId.isNotEmpty())
                mostrarDialogoConfirmacao(db, condominioId)
             else
                utils.criarAlerta(this, "Erro",
                    "Não é possível excluir pois o id está nulo",
                    3000,
                    R.drawable.warning_circle)
        }
    }


    fun mostrarDialogoConfirmacao(db: FirebaseFirestore, condominioId: String) {
        // Infla o layout personalizado
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_confirmacao, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false) // Impede o fechamento ao clicar fora
            .create()

        val btnConfirmar = dialogView.findViewById<Button>(R.id.DialogConfirmacao_BotaoConfirma_Button)
        val btnCancelar = dialogView.findViewById<Button>(R.id.DialogConfirmacao_BotaoCancelar_Button)

        btnConfirmar.setOnClickListener {
            // Ação de confirmação
            dialog.dismiss()
            excluirCondominio(db, condominioId)
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun excluirCondominio(db: FirebaseFirestore, condominioId: String) {
        val tempo: Long = 3000

        db.collection("condominios")
            .document(condominioId)
            .delete()
            .addOnSuccessListener {
                utils.criarAlerta(
                    this,
                    "Sucesso",
                    "Comunidade excluída com sucesso",
                    tempo,
                    R.drawable.check_circle
                )
                val intent = Intent(this, HomeActivity::class.java)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(tempo)
                    startActivity(intent)
                }
            }
            .addOnFailureListener {
                utils.criarAlerta(
                    this,
                    "Erro",
                    "Erro ao excluir comunidade",
                    tempo,
                    R.drawable.warning_circle
                )
            }
    }

}
