package br.com.fiap.renanleite_rm96168.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.renanleite_rm96168.model.EcoDicaModel

@Dao
interface EcoDicaDao {

    @Query("SELECT * FROM EcoDicas")
    fun getAll(): LiveData<List<EcoDicaModel>>

    @Insert
    fun insert(ecoDica: EcoDicaModel)

    @Delete
    fun delete(ecoDica: EcoDicaModel)
}
