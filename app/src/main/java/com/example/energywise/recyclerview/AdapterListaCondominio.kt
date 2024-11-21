package com.example.energywise.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.energywise.R
import com.example.energywise.activity.DetalhesCondominio
import com.example.energywise.model.Condominio

class AdapterListaCondominio(
    private val context: Context,
    private val condominios: List<Condominio>
): RecyclerView.Adapter<AdapterListaCondominio.CondominioViewHolder>() {

    class CondominioViewHolder(itemCondominio: View, val context: Context): RecyclerView.ViewHolder(itemCondominio){

        fun vincula(condominio: Condominio) {
            val nomeComunidade = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextNomeCondominio_TextView)
            nomeComunidade.text = condominio.nome
            val energiaEmEstoque = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextEnergiaEstoque_TextView)
            energiaEmEstoque.text = condominio.energiaEmEstoque.toString().plus(" kW’s em estoque")
            val qtdHabitantes = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextQtdHabitantes_TextView)
            qtdHabitantes.text = condominio.quantidadeHabitantes.toString().plus(" Habitantes")
            val energiaConsumida = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextEnergiaConsumida_TextView)
            energiaConsumida.text = condominio.energiaConsumida.toString().plus(" kW’s consumido")
            val estoqueRecomendado = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextEstoqueRecomendado_TextView)
            estoqueRecomendado.text = condominio.estoqueRecomendado.toString().plus(" kW’s recomendado")

            itemView.setOnClickListener{
                val intent = Intent(context, DetalhesCondominio::class.java)
                intent.putExtra("condominio", condominio)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CondominioViewHolder {
        val inflated = LayoutInflater.from(context)
        val view = inflated.inflate(R.layout.item_lista_comunidade, parent, false)
        return CondominioViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: CondominioViewHolder, position: Int) {
        holder.vincula(condominios[position])
    }

    override fun getItemCount(): Int {
        return condominios.size
    }
}