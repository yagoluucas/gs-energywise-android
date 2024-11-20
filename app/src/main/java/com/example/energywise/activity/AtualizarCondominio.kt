package com.example.energywise.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.energywise.R
import com.example.energywise.fragment.BottomNavigation
import com.example.energywise.fragment.CloseFragment
import com.example.energywise.model.Condominio

class AtualizarCondominio : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val condominio = intent.getSerializableExtra("condominio") as? Condominio
        val fragment = CloseFragment()
        val bundle = Bundle()
        bundle.putSerializable("condominio", condominio)
        fragment.arguments = bundle

        // Registra o fragmento no FragmentManager para inflar no XML
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_close, fragment)
            .commit()
        setContentView(R.layout.activity_atualizar_comunidade)

    }
}