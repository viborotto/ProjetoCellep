package br.com.vittoria.projetocellep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /*
        {} -> bloco ou escopo de programacao
        [] -> Lista e Coleçōes
        () -> Condição, passagem de parâmetros ou Método construtor
         */

        //Pausa a execução
        Handler().postDelayed({
            //chave dentro da funcao, funcao lambda
            //inicia uma outra tela: login
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }, 5000)
    }
}