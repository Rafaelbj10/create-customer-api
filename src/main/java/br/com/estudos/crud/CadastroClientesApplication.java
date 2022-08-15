package br.com.estudos.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.estudos.crud")
public class CadastroClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroClientesApplication.class, args);
	}

}
