package br.com.orgs.database.dao

import androidx.room.*
import br.com.orgs.models.Produto

@Dao
interface ProdutoDao {
    @Query("SELECT * FROM produto")
    fun getAll(): List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(vararg produto: Produto)
    //    @Update
    //    fun altera(produto: Produto)

    @Delete
    fun remove(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaPorId(id: Long) : Produto?
}