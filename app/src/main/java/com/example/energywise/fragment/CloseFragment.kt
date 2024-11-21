package com.example.energywise.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.energywise.R
import com.example.energywise.activity.AtualizarCondominio
import com.example.energywise.activity.DetalhesCondominio
import com.example.energywise.activity.HomeActivity
import com.example.energywise.model.Condominio

class CloseFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frament_close, container, false)

        val btnFecharPagina: Button = view.findViewById(R.id.Fragmento_FecharPagina_Button)

        btnFecharPagina.setOnClickListener {
            if(requireActivity() is AtualizarCondominio) {
                val condominio = arguments?.getSerializable("condominio") as? Condominio
                val intent = Intent(activity, DetalhesCondominio::class.java)
                intent.putExtra("condominio", condominio)
                startActivity(intent)
            } else {
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)
            }
        }
        return view
    }
}