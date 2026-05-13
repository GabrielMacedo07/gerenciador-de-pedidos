package com.estudos.gerenciador_pedidos.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {
    //5 - Crie uma classe Pedido com os seguintes atributos: id (Long, chave primária) data (LocalDate) A classe deve ter um construtor e os getters. Transforme a classe em entidade.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pedidoId;
    private LocalDate data;

    public Pedido(Long pedidoId, LocalDate data) {
        this.pedidoId = pedidoId;
        this.data = data;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
