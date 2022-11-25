package com.example.superlist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.superlist.Produto
import com.example.superlist.ProdutoDAO


class CadastroActivity : AppCompatActivity() {
    private lateinit var btadicionar: Button
    private lateinit var btlista: Button
    private lateinit var etNome: EditText
    private lateinit var etQtd: EditText
    private lateinit var etValor: EditText
    private lateinit var dao: ProdutoDAO

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        this.btadicionar = findViewById(R.id.btadicionar)
        this.btlista = findViewById(R.id.btlista)
        this.etNome = findViewById(R.id.etNome)
        this.etQtd = findViewById(R.id.etQtd)
        this.etValor = findViewById(R.id.etValor)

        this.dao = ProdutoDAO(this)

        this.btadicionar.setOnClickListener { Adicionar() }
        this.btlista.setOnClickListener { cancelar() }
    }

    private fun Adicionar() {
        val nome = this.etNome.text.toString()
        val quantidade = this.etQtd.text.toString().toInt()
        val valor = this.etValor.text.toString().toFloat()
        val produto = Produto(nome, quantidade, valor)
        this.dao.insert(produto)
        val msg = "${produto.nome} cadastrado com sucesso!"
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

        val intent = Intent(this@CadastroActivity,Produto::class.java).apply {
            putExtra("PRODUTO", produto)

            if(intent.resolveActivity(packageManager) != null ){
                startActivity(intent)
                finish()

            }
        }


    }


    private fun cancelar() {
        finish()
    }

}