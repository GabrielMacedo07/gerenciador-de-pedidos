package com.estudos.gerenciador_pedidos.repository;

import com.estudos.gerenciador_pedidos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("""
        SELECT c
        FROM Categoria c
        LEFT JOIN FETCH c.produtos
    """)
    List<Categoria> buscarCategoriasComProdutos();
}
