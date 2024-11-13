package com.example.energywise.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.energywise.R
import com.example.energywise.activity.NovoCondominio

class InfoCondominio: Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info_condominio, container, false)
        val btnEnviarInformacoes = view.findViewById<Button>(R.id.FragmentoInfoCondominio_ButnEnviarInformacoes_AppCompatButton)


        return view
    }
}