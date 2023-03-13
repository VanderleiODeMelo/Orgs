package com.alura.orgs.ui.activity.carregarImagem

import android.widget.ImageView
import coil.load
import com.alura.orgs.R


fun ImageView.tentaCarregarImagemView(
    url: String? = null,
    fallback: Int = R.drawable.imagem_padrao
) {
    load(url) {

        fallback(fallback)
        placeholder(R.drawable.placeholder_carregamento_dialog)
        error(R.drawable.erro)
    }
}
