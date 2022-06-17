package br.com.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.orgs.R
import br.com.orgs.models.Produto
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

//Olhar padrao do formulario, ele é o modelo mais correto
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        setContentView(R.layout.activity_main)

//        val titulo: TextView  = findViewById<TextView>(R.id.tvTitulo)
//        titulo.text = "Organics"

//        val tvNome= findViewById<TextView>(R.id.tvNome)
//        tvNome.text = "Salada de frutas"
//
//        val descricao = findViewById<TextView>(R.id.tvDescricao)
//        descricao.text = "Laranja, morango, maça"
//
//        val valor = findViewById<TextView>(R.id.tvValor)
//        valor.text = "22,99"

        val recycle = findViewById<RecyclerView>(R.id.rvLista)
        recycle.adapter = ListaProdutosAdapter(
            context = this,
            produtos = listOf(
                Produto("Salada", "Alface, tomate, cebola", BigDecimal("15.57")),
                Produto("Salada de frutas", "Morango, banana, laranja", BigDecimal("21.99")),
                Produto("Açai", "Açai, morango, banana, laranja", BigDecimal("25.99"))
            )
        )
        recycle.layoutManager = LinearLayoutManager(this)
    }
}