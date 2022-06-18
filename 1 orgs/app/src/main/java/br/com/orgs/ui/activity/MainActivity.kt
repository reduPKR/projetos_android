package br.com.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.orgs.R
import br.com.orgs.dao.ProdutoDAO
import br.com.orgs.models.Produto
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

//Olhar padrao do formulario, ele é o modelo mais correto
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val dao = ProdutoDAO()
        val recycle = findViewById<RecyclerView>(R.id.rvLista)
        recycle.adapter = ListaProdutosAdapter(
            context = this,
            produtos = dao.buscarTodos()
        )
        recycle.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fabAdicionar)
        fab.setOnClickListener{
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}