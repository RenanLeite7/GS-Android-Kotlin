package br.com.fiap.renanleite_rm96168.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.renanleite_rm96168.R
import br.com.fiap.renanleite_rm96168.model.EcoDicaModel

class EcoDicasAdapter(
    private val context: Context,
    private var ecoDicas: List<EcoDicaModel>, // Lista de dicas a ser exibida
    private val onDeleteClick: (EcoDicaModel) -> Unit
) : RecyclerView.Adapter<EcoDicasAdapter.EcoDicaViewHolder>() {

    fun updateDicas(newDicas: List<EcoDicaModel>) {
        ecoDicas = newDicas
        notifyDataSetChanged()
    }

    inner class EcoDicaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvDescricao: TextView = itemView.findViewById(R.id.tvDescricao)
        val deleteIcon: ImageView = itemView.findViewById(R.id.imageButton) // Ícone de exclusão

        init {
            // Exibe Toast
            itemView.setOnClickListener {
                val dica = ecoDicas[adapterPosition]
                Toast.makeText(context, "Mais sobre: ${dica.titulo}", Toast.LENGTH_SHORT).show()
            }

            // Clicar no ícone de lixeira, chama a função de exclusão
            deleteIcon.setOnClickListener {
                val dica = ecoDicas[adapterPosition]
                onDeleteClick(dica)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EcoDicaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.eco_dica_item, parent, false)
        return EcoDicaViewHolder(view)
    }

    override fun onBindViewHolder(holder: EcoDicaViewHolder, position: Int) {
        val dica = ecoDicas[position]
        holder.tvTitulo.text = dica.titulo
        holder.tvDescricao.text = dica.descricao
    }

    override fun getItemCount(): Int = ecoDicas.size
}
