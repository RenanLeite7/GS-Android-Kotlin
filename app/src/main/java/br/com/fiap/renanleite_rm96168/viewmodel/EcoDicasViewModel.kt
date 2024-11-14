import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.fiap.renanleite_rm96168.data.EcoDicasDatabase
import br.com.fiap.renanleite_rm96168.model.EcoDicaModel
import androidx.lifecycle.viewModelScope
import android.app.Application
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers


class EcoDicasViewModel(application: Application) : AndroidViewModel(application) {
    private val ecoDicaDao = EcoDicasDatabase.getDatabase(application).ecoDicaDao()
    val allDicas: LiveData<List<EcoDicaModel>> = ecoDicaDao.getAll()


    fun addDica(dica: EcoDicaModel) {
        viewModelScope.launch(Dispatchers.IO) {

            ecoDicaDao.insert(dica)
        }
    }

    fun deleteDica(dica: EcoDicaModel) {
        viewModelScope.launch(Dispatchers.IO){
            ecoDicaDao.delete(dica)
        }
    }
}
