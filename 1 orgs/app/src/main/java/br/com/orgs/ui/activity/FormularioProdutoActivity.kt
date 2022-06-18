package br.com.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.orgs.R
import br.com.orgs.dao.ProdutoDAO
import br.com.orgs.models.Produto
import java.math.BigDecimal

class FormularioProdutoActivity :
    AppCompatActivity(R.layout.activity_formulario_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configurarBotaoSalvar()
    }

    private fun configurarBotaoSalvar() {
        val salvar = findViewById<Button>(R.id.btSalvar)
        val dao = ProdutoDAO()
        salvar.setOnClickListener {
            val produto = criarProduto()

            dao.adicionar(produto)
            finish()
        }
    }

    private fun criarProduto(): Produto {
        val campoNome = findViewById<TextView>(R.id.etNome)
        val campoDescricao = findViewById<TextView>(R.id.etDescricao)
        val campoValor = findViewById<TextView>(R.id.etValor)

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