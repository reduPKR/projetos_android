package br.com.orgs.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.orgs.R
import br.com.orgs.dao.ProdutoDAO
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

//Olhar padrao do formulario, ele é o modelo mais correto
class ListaProdutoActivity : Activity() {
    private val dao = ProdutoDAO()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscarTodos())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_produto)

        configurarRecycleView()
        configurarFAB()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualizar(dao.buscarTodos())
    }

    private fun configurarFAB() {
        val fab = findViewById<FloatingActionButton>(R.id.fabAdicionar)
        fab.setOnClickListener {
            abrirFormularioProduto()
        }
    }

    private fun abrirFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configurarRecycleView() {
        //No fim do id bom é activity_lista_nomeID
        val recycle = findViewById<RecyclerView>(R.id.rvLista)
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(this)
    }
}