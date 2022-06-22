package br.com.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.dao.ProdutoDAO
import br.com.orgs.database.AppDatabase
import br.com.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.orgs.extensions.tentaCarregarImagem
import br.com.orgs.models.Produto
import br.com.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto TOP"
        configurarBotaoSalvar()

        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .exibir(url) { imagem ->
                    url = imagem
                    binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
                }
        }
    }

    private fun configurarBotaoSalvar() {
        val salvar = binding.btSalvar

        val db = AppDatabase.instancia(this)
        val produtoDao = db.produtoDao()
        salvar.setOnClickListener {
            val produto = criarProduto()

            produtoDao.salvar(produto)
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
            0,
            nome,
            descricao,
            valor,
            url
        )
    }
}