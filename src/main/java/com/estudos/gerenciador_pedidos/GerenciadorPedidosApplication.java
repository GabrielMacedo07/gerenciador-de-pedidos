package com.estudos.gerenciador_pedidos;

import com.estudos.gerenciador_pedidos.main.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GerenciadorPedidosApplication {

	public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(GerenciadorPedidosApplication.class, args);

        Main menu = context.getBean(Main.class);

        menu.exibirMenu();

	}

}
