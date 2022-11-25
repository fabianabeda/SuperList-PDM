package com.example.superlist

import android.content.Intent
import com.example.superlist.Produto
import android.graphics.Color
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.widget.*
import java.util.*
import com.example.superlist.ProdutoDAO

class MainActivity : AppCompatActivity() {
    private lateinit var tv_total: TextView
    private lateinit var listView: ListView
    private lateinit var dao: ProdutoDAO
    private lateinit var bt_add: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.dao = ProdutoDAO(this)
        this.tv_total = findViewById(R.id.tv_total)
        //this.soma()
        this.bt_add = findViewById(R.id.bt_add)
        this.listView = findViewById(R.id.listview)
        this.bt_add.setOnClickListener(ADDOnClick())

        this.listView.setOnItemClickListener(ItemClickList())
        this.listView.setOnItemLongClickListener(OnItemLongClick())
    }

    private fun soma() {
        var total = 0.0F
        val Produto: MutableList<Produto>

        Produto = this.dao.read()
        for(produto  in Produto) {
            total += (produto.valor * produto.quantidade)
        }

        this.tv_total.setText(total.toString())
        }

    fun atualiza() {
        val layout = android.R.layout.simple_list_item_1
        this.listView.adapter = ArrayAdapter<Produto>(this, layout,this.dao.read())
        this.soma()
    }

    inner class ADDOnClick: View.OnClickListener {
        override fun onClick(p0: View?) {
            val intent = Intent(this@MainActivity, CadastroActivity::class.java)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }

        }

    }
    inner class ItemClickList : AdapterView.OnItemClickListener {
        override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            this@MainActivity.listView.setSelector(R.color.teal_200)
        }
    }


    inner class OnItemLongClick : AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(
            adapter: AdapterView<*>?,
            view: View?,
            index: Int,
            id: Long
        ): Boolean {
            val produto = adapter?.getItemAtPosition(index) as Produto
            this@MainActivity.dao.delete(produto)
            val msg = "${produto.nome} removido com sucesso!"
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            this@MainActivity.atualiza()
            return true
        }
    }
    override fun onResume() {
        super.onResume()
        this.atualiza()
    }
}
