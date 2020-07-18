package br.com.vittoria.projetocellep

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //Criando Spinner
        //Etapa 1: Criar uma lista do spn
        val listaSpn = arrayListOf("Selecione o sexo", "Feminino", "Masculino", "Outros")
        //Etapa 2: Criar um adapter do spn
        val spinnerAdapter = ArrayAdapter(
            this@CadastroActivity,
            android.R.layout.simple_spinner_dropdown_item,
            listaSpn
        )
        //Etapa 3: Linkar o adapter com o spinner do layout
        spn.adapter = spinnerAdapter

        //Clicar no botao cadastro, veriificacoes, gravar as informacoes em algum lugar sharedPreferences(utiliza uma pasta do celular, para guardar umas informacoes nao sensiveis, o ideal seria um DB)
        btnCadastrar.setOnClickListener {

            //Recuperar as informacoes digitadas pelo usuario
            val nome = edtNome.text.toString().trim()
            val sobrenome = edtSobrenome.text.toString().trim()
            val email = edtEmail.text.toString().trim().toLowerCase()
            val senha = edtSenhaCadastro.text.toString().trim()
            val genero = spn.selectedItem.toString()

            //Verificar se os campos estao vazios
            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || genero == "Selecione o sexo"){
//                edtNome.error = "Campo obrigatório"
//                edtSobrenome.error = "Campo obrigatório"
//                edtEmail.error = "Campo obrigatório"
//                edtSenhaCadastro.error = "Campo obrigatório"

                //Criando uma caixa de alerta
                AlertDialog.Builder(this@CadastroActivity)
                    .setTitle("Atenção")
                    .setMessage("Preencha todos os campos!")
                    .setPositiveButton("OK"){ _, _ ->
                        //Funcao Lambda
                    } //quando clicar fora nao vai sair, so no btn
                    .setCancelable(false)
                    .create()
                    .show()
            } else {
                //Gravar os dados do usuario no Shared Preferences
                getSharedPreferences("cadastro-$email",Context.MODE_PRIVATE).edit().apply{
                    putString("nome", nome)
                    putString("sobrenome", sobrenome)
                    putString("email", email)
                    putString("senha", senha)
                    putString("genero", genero)
                }.apply() //guardando todas as infos

                Toast.makeText(this@CadastroActivity, "Usuário logado com sucesso", Toast.LENGTH_LONG)
                    .show()

                //Limpando todos os campos
                edtNome.setText("")
                edtSobrenome.setText("")
                edtEmail.text.clear()
                edtSenhaCadastro.text.clear()
                spn.setSelection(0)
            }
        }
    }
}