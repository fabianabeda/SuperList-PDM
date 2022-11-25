package com.example.superlist

import java.io.Flushable
import java.io.Serializable

import java.util.*

class Produto : Serializable{
    var id: Int
    var nome: String
    var quantidade: Int
    var valor: Float

    // memória
    constructor(nome: String, quantidade: Int, valor: Float){
        this.id = -1
        this.nome = nome
        this.quantidade = quantidade
        this.valor = valor
    }

    // banco
    constructor(id: Int, nome: String, quantidade: Int, valor: Float){
        this.id = id
        this.nome = nome
        this.quantidade = quantidade
        this.valor = valor
    }



    override fun toString(): String {
        return "${this.nome} - ${this.quantidade} - R$ ${this.valor}"
    }
}