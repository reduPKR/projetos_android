package br.com.orgs.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class Produto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo val nome: String,
    @ColumnInfo val descricao: String,
    @ColumnInfo val valor: BigDecimal,
    @ColumnInfo val imagem: String? = null
)
