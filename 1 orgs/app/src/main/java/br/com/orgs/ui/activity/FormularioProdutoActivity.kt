package br.com.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.R
import br.com.orgs.dao.ProdutoDAO
import br.com.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.orgs.models.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configurarBotaoSalvar()

        binding.activityFormularioProdutoImagem.setOnClickListener {
            AlertDialog.Builder(this)
                .setView(R.layout.formulario_imagem)
                .setPositiveButton("Confirmar") { _, _ ->

                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }

    private fun configurarBotaoSalvar() {
        //val salvar = findViewById<Button>(R.id.btSalvar)
        val salvar = binding.btSalvar
        val dao = ProdutoDAO()
        salvar.setOnClickListener {
            val produto = criarProduto()

            dao.adicionar(produto)
            finish()
        }
    }

    private fun criarProduto(): Produto {
        val campoNome = binding.etNome
        val campoDescricao = binding.etDescricao
        val campoValor = binding.etValor

        val nome = campoNome.text.toString()
        val descricao = campoDescricao.text.toString()
        val valorRecebido = campoValor.text.toString()

        Log.i(
            "Clique_btn_salvar",
            "nome: $nome \r\n descrição: $descricao \r\n valor: $valorRecebido"
        )

        val valor = if (valorRecebido.isBlank() || valorRecebido.isEmpty()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorRecebido)
        }

        return Produto(
            nome,
            descricao,
            valor
        )
    }
}