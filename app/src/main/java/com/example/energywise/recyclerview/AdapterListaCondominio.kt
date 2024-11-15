package com.example.energywise.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.energywise.R
import com.example.energywise.model.Comunidade

class AdapterListaCondominio(
    private val context: Context,
    private val comunidades: List<Comunidade>
): RecyclerView.Adapter<AdapterListaCondominio.CondominioViewHolder>() {

    class CondominioViewHolder(itemCondominio: View): RecyclerView.ViewHolder(itemCondominio){
        fun vincula(comunidade: Comunidade) {
            val nomeComunidade = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextNomeCondominio_TextView)
            nomeComunidade.text = comunidade.nome
            val energiaEmEstoque = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextEnergiaEstoque_TextView)
            energiaEmEstoque.text = comunidade.energiaEmEstoque.toString().plus(" kW’s em estoque")
            val qtdHabitantes = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextQtdHabitantes_TextView)
            qtdHabitantes.text = comunidade.quantidadeHabitantes.toString().plus(" Habitantes")
            val energiaConsumida = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextEnergiaConsumida_TextView)
            energiaConsumida.text = comunidade.energiaConsumida.toString().plus(" kW’s consumido")
            val estoqueRecomendado = itemView.findViewById<TextView>(R.id.ItemListaCondominio_TextEstoqueRecomendado_TextView)
            estoqueRecomendado.text = comunidade.estoqueRecomendado.toString().plus(" kW’s recomendado")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CondominioViewHolder {
        val inflated = LayoutInflater.from(context)
        val view = inflated.inflate(R.layout.item_lista_comunidade, parent, false)
        return CondominioViewHolder(view)
    }

    override fun onBindViewHolder(holder: CondominioViewHolder, position: Int) {
        holder.vincula(comunidades[position])
    }

    override fun getItemCount(): Int {
        return comunidades.size
    }
}