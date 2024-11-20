package com.example.energywise.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.energywise.R
import com.example.energywise.fragment.CloseFragment
import com.example.energywise.fragment.InfoCondominio
import com.example.energywise.model.Condominio

class AtualizarCondominio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atualizar_comunidade)
        val condominio = intent.getSerializableExtra("condominio") as? Condominio
        val fragmentClose = CloseFragment()
        val bundleClose = Bundle()
        bundleClose.putSerializable("condominio", condominio)
        fragmentClose.arguments = bundleClose

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_close, fragmentClose)
            .commit()



        val fragmentInfoCondominio = InfoCondominio()
        val bundleInfoCondominio = Bundle()
        bundleInfoCondominio.putString("id", condominio?.id)
        fragmentInfoCondominio.arguments = bundleInfoCondominio

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_info_comunidade, fragmentInfoCondominio)
            .commit()

    }
}