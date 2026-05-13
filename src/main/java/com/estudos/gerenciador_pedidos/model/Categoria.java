package com.estudos.gerenciador_pedidos.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    private String nome;

    public Categoria(){}
    public Categoria(String nome) {
        this.nome = nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "categoriaId : " + categoriaId +
                ", nome : " + nome;
    }

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos = new ArrayList<>();
}
