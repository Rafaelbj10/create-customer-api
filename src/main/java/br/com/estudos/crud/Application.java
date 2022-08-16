package br.com.estudos.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CadastroClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroClientesApplication.class, args);
	}

}
