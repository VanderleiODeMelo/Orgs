package com.alura.orgs.ui.activity.carregarImagem

import android.widget.ImageView
import coil.load
import com.alura.orgs.R


fun ImageView.tentaCarregarImagemView(url: String?) {
    load(url) {
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder_carregamento_dialog)
    }
}
