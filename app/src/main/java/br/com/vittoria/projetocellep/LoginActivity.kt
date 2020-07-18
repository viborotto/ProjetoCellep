package br.com.vittoria.projetocellep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //TODO recuperar email e senha para que consiga logar e va para a MainActivity

        //Clique do botao entrar
        btnEntrar.setOnClickListener {
            //Capturar o texto que o usuario digitou nos EditText
            //val imutavel
            val usuario = edtUsuario.text.toString().trim();
            val senha = edtSenhaLogin.text.toString().trim();

            //verificar se exite algum texto digitado
            if (usuario.isEmpty()){
                edtUsuario.error = "Campo obrigatório"
                edtUsuario.requestFocus()
            } else if (senha.isEmpty()){
                edtSenhaLogin.error = "Campo obrigatório"
                edtSenhaLogin.requestFocus()
            } else if (usuario == "viborotto" && senha == "12345678"){
                //Apresentando uma mensagem ao usuario
                Toast.makeText(this@LoginActivity, "Usuário logado com sucesso", Toast.LENGTH_LONG)
                    .show()
                //Abrir a tela main
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                //tirar a tela do empilhamento
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "Usuário ou senha incorretos", Toast.LENGTH_LONG)
                    .show()
            }
        }

        //Abrir tela de cadastro
        btnCadastrarLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity, CadastroActivity::class.java))
        }
    }
}