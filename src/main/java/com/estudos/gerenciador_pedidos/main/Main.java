package com.estudos.gerenciador_pedidos.main;

import com.estudos.gerenciador_pedidos.model.Categoria;
import com.estudos.gerenciador_pedidos.model.Produto;
import com.estudos.gerenciador_pedidos.repository.CategoriaRepository;
import com.estudos.gerenciador_pedidos.repository.PedidoRepository;
import com.estudos.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Main {
    private Scanner sc =new Scanner(System.in);

    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private PedidoRepository pedidoRepository;

    public Main(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public void exibirMenu(){
        var opcao = -1;
        while (opcao !=4){
            String menu = """
                    1 - Adicionar produto
                    2 - Ver lista por categoria
                    3 - remover algum produto
                    4 - sair
                    """;
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    adicionaProduto();
                    break;
                case 2:
                    listarPorCategoria();
                    break;
                case 3:
                    removerProduto();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Selecione uma opção válida");
            }
        }
    }

    private void adicionaProduto(){
        System.out.println("Nome do produto: ");
        String nome = sc.nextLine().toUpperCase();
        System.out.println("Preço: (Ex: 19,22)");
        double preco = sc.nextDouble();
        sc.nextLine();
        System.out.println("Quantidade: ");
        int quantidade = sc.nextInt();
        sc.nextLine();
        System.out.println("Adicione a uma categoria usando o id: ");
        listaDeCategoria();
        Long categoriaId = sc.nextLong();
        sc.nextLine();
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);
        if(categoriaOptional.isPresent()){
            Categoria categoria = categoriaOptional.get();
            Produto produto = new Produto(nome, preco, quantidade);
            produto.setCategoria(categoria);
            produtoRepository.save(produto);
            System.out.println("Produto adicionado com sucesso");

        }

    }
    private void listarPorCategoria() {
//        List<Produto> produtoList = produtoRepository.findAll();
//        produtoList.stream()
//                .sorted(Comparator.comparing(Produto::getNome))
//                .forEach(System.out::println);
        categoriaRepository.findAll().forEach(System.out::println);
    }

    private void listaDeCategoria(){
        categoriaRepository.findAll().forEach(System.out::println);
    }
    private void removerProduto() {

    }


}
