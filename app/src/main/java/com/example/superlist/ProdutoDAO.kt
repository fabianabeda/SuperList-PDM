package com.example.superlist

import android.content.ContentValues
import android.content.Context


class ProdutoDAO {
    val banco: BancoHelper

    constructor(context: Context) {
        this.banco = BancoHelper(context)
    }

    fun insert(produto: Produto) {
        val cv = ContentValues()
        cv.put("nome", produto.nome)
        cv.put("quantidade", produto.quantidade)
        cv.put("valor", produto.valor)
        this.banco.writableDatabase.insert("produtos", null, cv)
    }

    fun read(): ArrayList<Produto> {
        val lista = arrayListOf<Produto>()
        val colunas = arrayOf("id", "nome", "quantidade", "valor")
        val c =
            this.banco.readableDatabase.query("produtos", colunas, null, null, null, null, "nome")
        c.moveToFirst()
        for (i in 1..c.count) {
            val id = c.getInt(0)
            val nome = c.getString(1)
            val quantidade = c.getInt(2)
            val valor = c.getFloat(3)
            val produto = Produto(id, nome, quantidade, valor)
            lista.add(produto)
            c.moveToNext()
        }
        return lista
    }

    fun find(id: Int): Produto? {
        return null
    }

    fun delete(id: Int) {
        val where = arrayOf(id.toString())
        this.banco.writableDatabase.delete("produtos", "id = ?", where)
    }

    fun delete(produto: Produto) {
        this.delete(produto.id)
    }

    fun update(produto: Produto){

    }
}
