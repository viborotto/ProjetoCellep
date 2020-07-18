package br.com.vittoria.projetocellep

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recuperar o email da Intent
        val emailRecuperado = intent.getStringExtra("email")

        //Abrindo o Shared Preferences
        val minhaPreferencia = getSharedPreferences("cadastro-$emailRecuperado", Context.MODE_PRIVATE)

        //Recuperando os dados do Shared Preference e substituir no TextView correspondente
        val nome =  minhaPreferencia.getString("nome", "Chave não encontrada")
        val sobrenome = minhaPreferencia.getString("sobrenome", "Chave não encontrada")

        txvNome.text = "$nome $sobrenome"
        txvEmail.text = minhaPreferencia.getString("email", "E-mail não encontrado")
        txvGenero.text = minhaPreferencia.getString("genero", "Gênero não encontrado")

        //Botäo Sair da Main para a tela de Login
        btnSair.setOnClickListener {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Atenção")
                .setMessage(nome+", deseja mesmo sair?")
                .setPositiveButton("Sim"){_, _->
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("Não"){_,_->
                    //Lambda
                }
                .setCancelable(false)
                .create()
                .show()
        }

        //Botãi acessar Site Cellep, ou seja, abrir WebActivity
        btnSite.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebActivity::class.java))
        }
    }
}