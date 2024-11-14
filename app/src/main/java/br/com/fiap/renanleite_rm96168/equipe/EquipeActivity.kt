package br.com.fiap.renanleite_rm96168.equipe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.renanleite_rm96168.R

class EquipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipe)

        supportActionBar?.title = "EQUIPE"

        val member1InfoTextView: TextView = findViewById(R.id.member1Info)
        val member2InfoTextView: TextView = findViewById(R.id.member2Info)

        val member1Info = "Nome: Arthur Miranda Santos\nRM: 93023"
        val member2Info = "Nome: Renan Bandeira Leite\nRM: 96168"

        member1InfoTextView.text = member1Info
        member2InfoTextView.text = member2Info

        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}
