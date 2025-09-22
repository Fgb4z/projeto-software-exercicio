package br.edu.insper.pessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.edu.insper")
public class PessoaApplication {
	public static void main(String[] args) { SpringApplication.run(PessoaApplication.class, args); }
}
