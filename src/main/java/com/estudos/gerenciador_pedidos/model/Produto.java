package com.estudos.gerenciador_pedidos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(name = "valor")
    private double preco;
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoId;

    public Produto(){}

    public Produto(String nome, double preco, int quantidade) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @ManyToOne
    private Categoria categoria;

    @Override
    public String toString() {
        return "nome : " + nome +
                ", preco : " + preco +
                ", quantidade : " + quantidade;
    }
}
