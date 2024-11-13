package com.example.energywise.activity

import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.energywise.R

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val pesquisarComunidade: EditText = findViewById(R.id.HOME_CampoPesquisarComunidade_EditText)
        pesquisarComunidade.clearFocus()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}