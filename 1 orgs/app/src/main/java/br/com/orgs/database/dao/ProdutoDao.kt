package br.com.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.orgs.models.Produto

@Dao
interface ProdutoDao {
    @Query("SELECT * FROM produto")
    fun getAll(): List<Produto>

    @Insert
    fun salvar(vararg produto: Produto)
}