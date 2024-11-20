package com.example.energywise.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.energywise.R
import com.example.energywise.activity.NovoCondominio
import com.example.energywise.model.Condominio
import com.example.energywise.utils.Utils
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class InfoCondominio : Fragment() {

    private var db = Firebase.firestore
    private var utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info_condominio, container, false)

        val btnEnviarInformacoes = view.findViewById<Button>(R.id.FragmentoInfoCondominio_ButnEnviarInformacoes_AppCompatButton)
        val nomeCondominio: EditText = view.findViewById(R.id.FragmentoInfoCondominio_CampoNomeComunidade_EditText)
        val qtdHabitantes: EditText = view.findViewById(R.id.FragmentoInfoCondominio_CampoQtdHabitantes_EditText)
        val energiaConsumida: EditText = view.findViewById(R.id.FragmentoInfoCondominio_CampoEnergiaConsumida_EditText)
        val energiaEmEstoque: EditText = view.findViewById(R.id.FragmentoInfoCondominio_CampoEnergiaEmEstoque_EditText)
        val observacao: EditText = view.findViewById(R.id.FragmentoInfoCondominio_CampoObservacao_EditText)



        FirebaseApp.initializeApp(view.context)
        db = FirebaseFirestore.getInstance()

        btnEnviarInformacoes.isEnabled = false

        // Adiciona um TextWatcher para validar os campos
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Valida os campos toda vez que algo é digitado
                btnEnviarInformacoes.isEnabled = camposValidos(nomeCondominio, qtdHabitantes, energiaConsumida, energiaEmEstoque)
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        // Adiciona o TextWatcher aos campos
        nomeCondominio.addTextChangedListener(textWatcher)
        qtdHabitantes.addTextChangedListener(textWatcher)
        energiaConsumida.addTextChangedListener(textWatcher)
        energiaEmEstoque.addTextChangedListener(textWatcher)

        btnEnviarInformacoes.setOnClickListener {

            val estoqueRecomendado = "%.2f".format((energiaConsumida.text.toString().toDouble() * 1.3) - energiaEmEstoque.text.toString().toDouble()).replace(",", ".").toDouble()

            // Envia informações apenas se os campos forem válidos
            val condominio = Condominio(
                nome = nomeCondominio.text.toString(),
                quantidadeHabitantes = qtdHabitantes.text.toString().toInt(),
                energiaConsumida = energiaConsumida.text.toString().toDouble(),
                energiaEmEstoque = energiaEmEstoque.text.toString().toDouble(),
                observacao =  if (observacao.text.toString() == "") "Sem observação" else observacao.text.toString(),
                estoqueRecomendado = if (estoqueRecomendado < 0) energiaEmEstoque.text.toString().toDouble() else estoqueRecomendado
            )

            "%.2f".format((energiaConsumida.text.toString().toDouble() * 1.3) - energiaEmEstoque.text.toString().toDouble())


            if(requireActivity() is NovoCondominio) {
                // Salvar no Firestore
                db.collection("condominios")
                    .add(condominio)
                    .addOnSuccessListener { documento ->
                        utils.criarAlerta(view.context, "Sucesso", "Sucesso ao cadastrar comunidade", 2000, R.drawable.check_circle)
                        removerTextoDosCampos(nomeCondominio, qtdHabitantes, energiaConsumida, energiaEmEstoque, observacao)

                    }
                    .addOnFailureListener { erro ->
                        utils.criarAlerta(view.context, "Erro", erro.message.toString(), 2000, R.drawable.warning_circle)
                    }
            } else {
                // atualiza os dados

                val condominioAtualizado = mapOf(
                    "nome" to condominio.nome,
                    "quantidadeHabitantes" to condominio.quantidadeHabitantes,
                    "energiaConsumida" to condominio.energiaConsumida,
                    "energiaEmEstoque" to condominio.energiaEmEstoque,
                    "observacao" to condominio.observacao,
                    "estoqueRecomendado" to condominio.estoqueRecomendado
                )

                val idCondominioAntigo: String = if (arguments?.getString("id")?.isNotEmpty() == true) arguments?.getString("id")!! else ""
                Log.v("id", idCondominioAntigo)
                db.collection("condominios")
                    .document(idCondominioAntigo).update(condominioAtualizado)
                    .addOnSuccessListener {
                        utils.criarAlerta(view.context, "Sucesso", "Condomínio atualizado com sucesso", 2000, R.drawable.check_circle)
                        removerTextoDosCampos(nomeCondominio, qtdHabitantes, energiaConsumida, energiaEmEstoque, observacao)
                    }.addOnFailureListener { erro ->
                        utils.criarAlerta(view.context, "Erro", erro.message.toString(), 2000, R.drawable.warning_circle)
                    }
            }
        }

        return view
    }

    fun camposValidos(
        nomeCondominio: EditText,
        qtdHabitantes: EditText,
        energiaConsumida: EditText,
        energiaEmEstoque: EditText
    ): Boolean {
        return nomeCondominio.text.length > 1 &&
                qtdHabitantes.text.toString().toIntOrNull() != null &&
                energiaConsumida.text.toString().toDoubleOrNull() != null &&
                energiaEmEstoque.text.toString().toDoubleOrNull() != null
    }

    fun removerTextoDosCampos(
        nomeCondominio: EditText,
        qtdHabitantes: EditText,
        energiaConsumida: EditText,
        energiaEmEstoque: EditText,
        observacao: EditText
    ){
        nomeCondominio.setText("")
        qtdHabitantes.setText("")
        energiaConsumida.setText("")
        energiaEmEstoque.setText("")
        observacao.setText("")
    }
}