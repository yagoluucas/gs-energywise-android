package com.example.energywise.utils

import android.app.AlertDialog
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Utils {

    fun criarAlerta(context: Context, titulo: String, mensagem: String, tempoDialogo: Long, icone: Int){
        val alertDialog: AlertDialog = AlertDialog.Builder(context)
            .setTitle(titulo)
            .setMessage(mensagem)
            .setCancelable(false)
            .setIcon(icone)
            .show()

        // time para apagar o alert
        CoroutineScope(Dispatchers.Main).launch {
            delay(tempoDialogo)
            if(alertDialog.isShowing){
                alertDialog.dismiss()
            }
        }
    }
}