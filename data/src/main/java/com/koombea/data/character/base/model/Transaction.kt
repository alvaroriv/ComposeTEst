package com.koombea.data.character.base.model

data class Transaction(
    var value: String = "",
    var category: String = "",
    var description: String = "",
    var wallet: String = "",
    var attachment: String = "",
    var reminder: String = "",
    var dateCreated: String = ""
)