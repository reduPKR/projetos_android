package br.com.orgs.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Produto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo val nome: String,
    @ColumnInfo val descricao: String,
    @ColumnInfo val valor: BigDecimal,
    @ColumnInfo val imagem: String? = null
) : Parcelable
