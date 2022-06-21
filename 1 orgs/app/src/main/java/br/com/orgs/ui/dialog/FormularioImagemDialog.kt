package br.com.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.orgs.databinding.FormularioImagemBinding
import br.com.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {
    fun exibir(
        urlPadrao: String? = null,
                quandoImagemCarragada: (imagem: String) -> Unit) {

        val layoutInflater = LayoutInflater.from(context)
        val binding = FormularioImagemBinding.inflate(layoutInflater).apply {
            //Com aply posso remover os biding

            formularioImagemBotaoCarregar.setOnClickListener {
                val url = formularioImagemUrl.text.toString()

                formularioImagemImageview.tentaCarregarImagem(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formularioImagemUrl.text.toString()
                    // binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
                    quandoImagemCarragada(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }

}
