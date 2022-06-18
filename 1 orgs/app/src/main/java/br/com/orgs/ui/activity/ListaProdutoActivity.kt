package br.com.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.orgs.R
import br.com.orgs.dao.ProdutoDAO
import br.com.orgs.databinding.ActivityListaProdutoBinding
import br.com.orgs.ui.recyclerview.adapter.ListaProdutosAdapter



class ListaProdutoActivity : AppCompatActivity(R.layout.activity_lista_produto) {
    private val dao = ProdutoDAO()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscarTodos())
    private val binding by lazy {
        ActivityListaProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_lista_produto)
        setContentView(binding.root)
        configurarRecycleView()
        configurarFAB()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualizar(dao.buscarTodos())
    }

    private fun configurarFAB() {
       // val fab = findViewById<FloatingActionButton>(R.id.fabAdicionar)
        val fab = binding.fabAdicionar
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
        //val recycler = findViewById<RecyclerView>(R.id.rvLista)
        val recycler = binding.rvLista
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
    }
}