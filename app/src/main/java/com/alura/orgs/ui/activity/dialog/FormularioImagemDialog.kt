package com.alura.orgs.ui.activity.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.alura.orgs.databinding.FormularioDialogBinding
import com.alura.orgs.ui.activity.carregarImagem.tentaCarregarImagemView

class FormularioImagemDialog(private val context: Context) {

    fun mostra(urlPadrao: String? = null, quandoCarregadaImagem: (imagem: String) -> Unit) {

        FormularioDialogBinding.inflate(LayoutInflater.from(context))

            .apply {
                urlPadrao?.let {

                    formularioDialogImagemView.tentaCarregarImagemView(urlPadrao)
                    textInputEditTextCampoUrl.setText(urlPadrao)
                }

                formularioDialogBotaoCarregar.setOnClickListener(View.OnClickListener {
                    val url = textInputEditTextCampoUrl.text.toString()
                    formularioDialogImagemView.tentaCarregarImagemView(url)
                })
                AlertDialog.Builder(context)
                    .setView(root)
                    .setNegativeButton("Cancelar") { _, _ -> }
                    .setPositiveButton("Confirmar") { _, _ ->
                        val url = textInputEditTextCampoUrl.text.toString()
                        quandoCarregadaImagem(url)
                    }.show()
            }
    }
}