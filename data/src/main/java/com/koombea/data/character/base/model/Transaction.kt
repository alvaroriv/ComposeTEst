package com.koombea.data.character.base.model

data class Transaction(
    var category: String = "",
    var description: String = "",
    var wallet: String = "",
    var attachment: String = "",
    var reminder: String = ""
)