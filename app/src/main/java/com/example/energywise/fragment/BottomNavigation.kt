package com.example.energywise.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.energywise.R
import com.example.energywise.activity.HomeActivity
import com.example.energywise.activity.NovaComunidade
import com.example.energywise.activity.PerfilUsuario
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigation: Fragment() {
    private lateinit var bottomNavigation: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_navigation_bar, container, false)
        bottomNavigation = view.findViewById(R.id.bottomNavigationView)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.Menu_NovaComunidade -> {
                    if(activity !is NovaComunidade) {
                        NavigationStateManager.selectedItemId = R.id.Menu_NovaComunidade

                        val intent = Intent(activity, NovaComunidade::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        startActivity(intent)
                    }
                    true
                }

                R.id.Menu_Comunidades -> {
                    if(activity !is HomeActivity){
                        NavigationStateManager.selectedItemId = R.id.Menu_Comunidades

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
        var selectedItemId: Int = R.id.Menu_Comunidades
    }
}