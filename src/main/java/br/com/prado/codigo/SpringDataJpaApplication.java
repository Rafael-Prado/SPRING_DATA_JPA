package br.com.prado.codigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import br.com.prado.codigo.repository.IPessoaRepository;

@SpringBootApplication
@ImportResource(value="spring-data-xml.xml")
public class SpringDataJpaApplication implements CommandLineRunner{
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		testconfiguration();
		
	}

	private void testconfiguration() {
		
		long total = pessoaRepository.count();
		
		System.out.println(total);
	}
}
