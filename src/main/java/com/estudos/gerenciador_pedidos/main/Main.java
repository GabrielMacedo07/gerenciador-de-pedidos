package com.estudos.gerenciador_pedidos.main;

import com.estudos.gerenciador_pedidos.model.Categoria;
import com.estudos.gerenciador_pedidos.model.Produto;
import com.estudos.gerenciador_pedidos.repository.CategoriaRepository;
import com.estudos.gerenciador_pedidos.repository.PedidoRepository;
import com.estudos.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

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
        while (opcao !=5){
            String menu = """
                    1 - Adicionar produto
                    2 - Adicionar categoria
                    3 - Ver lista por categoria
                    4 - Remover algum produto
                    5 - Sair
                    """;
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    adicionaProduto();
                    break;
                case 2:
                    adicionaCategoria();
                    break;
                case 3:
                    listarCategoriaComProdutos();
                    break;
                case 4:
                    removerProduto();
                    break;
                case 5:
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
        System.out.println("Você precisa adicionar o produto em uma categoria usando o id: ");
        verCategoria();
        adicionaProdutoEmCategoria(nome, preco, quantidade);

    }
    private void adicionaCategoria(){
        System.out.println("Nomeie a categoria nova: ");
        String nome = sc.nextLine().toUpperCase();
        Categoria categoria = new Categoria(nome);
        categoriaRepository.save(categoria);
        System.out.println("Adicionada com Sucesso");
    }

    private void listarCategoriaComProdutos() {
        List<Categoria> categorias = categoriaRepository.buscarCategoriasComProdutos();

        for (Categoria categoria: categorias){
            System.out.println(categoria.getNome() + " \n");
            for(Produto produto: categoria.getProdutos()){
                System.out.println(
                        produto.getProdutoId() + " "+
                                produto.getNome() + " - Quantidade: " +
                                produto.getQuantidade() + " - Preço: " +
                                produto.getPreco()
                );
            }
            System.out.println();
        }
    }

    private void verCategoria(){
        categoriaRepository.findAll().forEach(System.out::println);
    }

    private void adicionaProdutoEmCategoria(String nome, double preco, int quantidade){
        System.out.println("Adicione: ");
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
    private void removerProduto() {
        System.out.println("Qual produto você quer remover?");
        listarCategoriaComProdutos();
        System.out.println("Remova usando o id: ");
        Long id = sc.nextLong();
        sc.nextLine();
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
        System.out.println("Tem certeza? (S/N)");
        char resposta = sc.next().charAt(0);
        if(resposta == 'S' || resposta == 's'){
            Optional<Produto> produto = produtoRepository.findById(id);
            produto.ifPresent(value -> System.out.println("Removendo " + value.getNome()+"..."));
            produtoRepository.deleteById(id);
            System.out.println("Produto removido com sucesso!");
        }else {
            System.out.println("Produto não foi removido!");
        }

    }
}
