package com.example.energywise

import android.annotation.SuppressLint
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
import com.example.energywise.activity.CriarConta
import com.example.energywise.activity.HomeActivity
import com.example.energywise.model.Usuario
import com.example.energywise.utils.Utils
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var db = Firebase.firestore
    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        db = FirebaseFirestore.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val campoEmail: EditText = findViewById(R.id.LOGIN_CampoEmailLogin_Editext)
        val campoSenha: EditText = findViewById(R.id.LOGIN_CampoSenhaLogin_Editext)
        val botaoEntrar: Button = findViewById(R.id.LOGIN_BotaoEntrar_Button)
        val textoCriarConta: TextView = findViewById(R.id.LOGIN_TextoCriarConta_TextView)


        botaoEntrar.setOnClickListener{
            val tempo: Long = 2000

            db.collection("usuarios")
                .whereEqualTo("email", campoEmail.text.toString())
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val senhaDigitada = campoSenha.text.toString()
                        val usuario = querySnapshot.documents[0].toObject<Usuario>()

                        if(senhaDigitada == usuario?.senha){
                            utils.criarAlerta(this, "Bem-Vindo", "Login feito com sucesso", tempo, R.drawable.check_circle)
                            val intent = Intent(this, HomeActivity::class.java)
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(tempo)
                                startActivity(intent)
                            }
                        } else {
                            utils.criarAlerta(this, "Falha", "Senha incorreta", tempo, R.drawable.warning_circle)
                        }


                    } else {
                        // O usuário não existe
                        utils.criarAlerta(this, "Falha", "Usuário não encontrado", tempo, R.drawable.warning_circle)
                    }
                }
                .addOnFailureListener { e ->
                    utils.criarAlerta(this, "Falha", "Erro ao recuperar usuário", tempo, R.drawable.warning_circle)
                }
        }

        textoCriarConta.setOnClickListener {
            val intent = Intent(this, CriarConta::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
    }

}