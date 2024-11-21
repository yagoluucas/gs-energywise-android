package com.example.energywise.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.energywise.MainActivity
import com.example.energywise.R
import com.example.energywise.model.Usuario
import com.example.energywise.utils.Utils
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CriarConta : AppCompatActivity() {
    private var db = Firebase.firestore
    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {

        FirebaseApp.initializeApp(this)
        db = FirebaseFirestore.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_conta)


        val btnCriarConta: Button = findViewById(R.id.CadastrarUsuario_BotaoCadastrarUsuario_Button)
        val campoNome: EditText = findViewById(R.id.CadastrarUsuario_CampoNome_EditText)
        val campoEmail: EditText = findViewById(R.id.CadastrarUsuario_CampoEmail_EditText)
        val campoSenha: EditText = findViewById(R.id.CadastrarUsuario_CampoSenha_EditText)

        btnCriarConta.setOnClickListener {
            val usuario = Usuario(
                campoNome.text.toString(),
                campoEmail.text.toString(),
                campoSenha.text.toString()
            )

            val tempo: Long = 2000
            db.collection("usuarios")
                .add(usuario).addOnSuccessListener { resultado ->

                   utils.criarAlerta(this, "Sucesso", "Você será direcionado para página de login em 2 segundos", tempo, R.drawable.check_circle)
                   val intent = Intent(this, MainActivity::class.java)

                    CoroutineScope(Dispatchers.Main).launch {
                        delay(tempo)
                        startActivity(intent)
                    }
                }.addOnFailureListener {erro ->
                    utils.criarAlerta(this, "Erro", "Erro ao cadastrar usuário", tempo, R.drawable.warning_circle)
                }
        }

    }
}