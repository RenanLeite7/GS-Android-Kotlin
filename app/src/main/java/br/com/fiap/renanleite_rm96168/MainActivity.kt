package br.com.fiap.renanleite_rm96168

import EcoDicasViewModel
import EcoDicasViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.renanleite_rm96168.viewmodel.EcoDicasAdapter
import br.com.fiap.renanleite_rm96168.equipe.EquipeActivity
import br.com.fiap.renanleite_rm96168.model.EcoDicaModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EcoDicasViewModel
    private lateinit var ecoDicasAdapter: EcoDicasAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuração da Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        ecoDicasAdapter = EcoDicasAdapter(this, listOf()) { dica ->
            viewModel.deleteDica(dica)
        }
        recyclerView.adapter = ecoDicasAdapter

        val addButton = findViewById<Button>(R.id.addButton)
        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val descEditText = findViewById<EditText>(R.id.descEditText)

        // Botão para adicionar dicas
        addButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val description = descEditText.text.toString().trim()

            if (title.isEmpty()) {
                titleEditText.error = "Preencha o título"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                descEditText.error = "Preencha a descrição"
                return@setOnClickListener
            }

            val ecoDica = EcoDicaModel(id = 0, titulo = title, descricao = description)

            viewModel.addDica(ecoDica)

            titleEditText.text.clear()
            descEditText.text.clear()
        }

        val viewModelFactory = EcoDicasViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EcoDicasViewModel::class.java)

        viewModel.allDicas.observe(this) { dicas ->
            ecoDicasAdapter.updateDicas(dicas)
        }

        // Configuração do SearchView para filtrar as dicas
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filtra as dicas conforme o texto digitado
                filterDicas(newText)
                return true
            }
        })

        // Botão para a EquipeActivity
        val btnEquipe: Button = findViewById(R.id.btnEquipe)
        btnEquipe.setOnClickListener {
            val intent = Intent(this, EquipeActivity::class.java)
            startActivity(intent)
        }
    }

    // Função para filtrar as dicas com base no título
    private fun filterDicas(query: String?) {
        val filteredList = viewModel.allDicas.value?.filter {
            it.titulo.contains(query ?: "", ignoreCase = true)
        } ?: listOf()
        ecoDicasAdapter.updateDicas(filteredList)
    }


}
