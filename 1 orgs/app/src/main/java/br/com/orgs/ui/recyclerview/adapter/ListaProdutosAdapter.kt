package br.com.orgs.ui.recyclerview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.orgs.R
import br.com.orgs.databinding.ProdutoItemBinding
import br.com.orgs.extensions.tentaCarregarImagem
import br.com.orgs.models.Produto
import coil.load
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

//    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        fun vincular(produto: Produto) {
//            val nome = itemView.findViewById<TextView>(R.id.tvNome)
//            nome.text = produto.nome
//
//            val descricao = itemView.findViewById<TextView>(R.id.tvDescricao)
//            descricao.text = produto.descricao
//
//            val valor = itemView.findViewById<TextView>(R.id.tvValor)
//            valor.text = produto.valor.toPlainString()
//        }
//    }

    class ViewHolder(private val binding: ProdutoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun vincular(produto: Produto) {
            //val nome = itemView.findViewById<TextView>(R.id.tvNome)
            val nome = binding.tvNome
            nome.text = produto.nome

            val descricao = binding.tvDescricao
            descricao.text = produto.descricao

            val valor = binding.tvValor
            val currencyInstance = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val moeda = currencyInstance.format(produto.valor)
            valor.text = moeda

            val visibilidade = if(produto.imagem != null){
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.formularioProdutoImageView.visibility = visibilidade

            //binding.formularioProdutoImageView.load(produto.imagem)
             
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(R.layout.produto_item, parent, false)
//        return ViewHolder(view)
        val bindind = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(bindind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincular(produto)
    }

    override fun getItemCount(): Int = produtos.size

    @SuppressLint("NotifyDataSetChanged")
    fun atualizar(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
