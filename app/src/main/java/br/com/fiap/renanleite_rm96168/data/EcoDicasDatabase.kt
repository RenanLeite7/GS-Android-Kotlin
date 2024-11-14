package br.com.fiap.renanleite_rm96168.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.renanleite_rm96168.model.EcoDicaModel

@Database(entities = [EcoDicaModel::class], version = 1)
abstract class EcoDicasDatabase : RoomDatabase() {

    abstract fun ecoDicaDao(): EcoDicaDao

    companion object {
        @Volatile
        private var INSTANCE: EcoDicasDatabase? = null

        fun getDatabase(context: Context): EcoDicasDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EcoDicasDatabase::class.java,
                    "eco_dicas_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
