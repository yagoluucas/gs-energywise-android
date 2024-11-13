package com.example.energywise.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.energywise.R
import com.example.energywise.activity.HomeActivity
import com.example.energywise.activity.NovoCondominio
import com.example.energywise.activity.PerfilUsuario
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigation: Fragment() {
    private lateinit var bottomNavigation: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_navigation_bar, container, false)
        bottomNavigation = view.findViewById(R.id.bottomNavigationView)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.Menu_NovoCondominio -> {
                    if(activity !is NovoCondominio) {
                        NavigationStateManager.selectedItemId = R.id.Menu_NovoCondominio

                        val intent = Intent(activity, NovoCondominio::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        startActivity(intent)
                    }
                    true
                }

                R.id.Menu_Condominio -> {
                    if(activity !is HomeActivity){
                        NavigationStateManager.selectedItemId = R.id.Menu_Condominio

                        val intent = Intent(activity, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        startActivity(intent)
                    }
                    true
                }

                R.id.Menu_Perfil -> {
                    if(activity !is PerfilUsuario){
                        NavigationStateManager.selectedItemId = R.id.Menu_Perfil

                        val intent = Intent(activity, PerfilUsuario::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        startActivity(intent)
                    }
                    true
                }

                else -> false
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        // Atualiza o item selecionado sempre que o fragmento se tornar visível
        bottomNavigation.selectedItemId = NavigationStateManager.selectedItemId
    }

    object NavigationStateManager {
        var selectedItemId: Int = R.id.Menu_Condominio
    }
}