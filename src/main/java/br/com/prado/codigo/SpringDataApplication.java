package br.com.prado.codigo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.domain.Sort;

import br.com.prado.codigo.entity.Endereco;
import br.com.prado.codigo.entity.Documento;
import br.com.prado.codigo.entity.Pessoa;
import br.com.prado.codigo.entity.Telefone;
import br.com.prado.codigo.entity.Endereco.TipoEndereco;
import br.com.prado.codigo.entity.Telefone.TipoTelefone;
import br.com.prado.codigo.repository.EnderecoRepository;
import br.com.prado.codigo.repository.DocumentoRepository;
import br.com.prado.codigo.repository.PessoaRepository;
import br.com.prado.codigo.repository.TelefoneRepository;

@SpringBootApplication
//@ImportResource(value = "spring-data.xml")
public class SpringDataApplication implements CommandLineRunner {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private DocumentoRepository documentoRepository;
	@Autowired
	private TelefoneRepository telefoneRepository; 
	
    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		//testConfiguration();
		//testSave();
		//testUpdate();
		testDelete();
		
	}

	private void testDelete() {
		
		pessoaRepository.delete(9L);
		
		Pessoa pessoa = pessoaRepository.findOne(8L);
		
		pessoaRepository.delete(pessoa);
		
		List<Pessoa> pessoas = pessoaRepository.findAll();
		pessoas.forEach(System.out::println);
		
	}

	private void testUpdate() {
		
		Pessoa pessoa = pessoaRepository.findOne(9L);
		
		System.out.println(pessoa.toString());
		
		pessoa.setPrimeiroNome("Aguiar");
		
		pessoaRepository.save(pessoa);
		
		Pessoa p2 =  pessoaRepository.findOne(15L);
		
		System.out.println(p2.toString());
		
	}

	private void testSave() {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setPrimeiroNome("João Luiz");
		pessoa.setSobreNome("Rios");
		pessoa.setIdade(35);
		pessoa.setDocumento(new Documento("841.852.963-74", 12365485));
		
		Endereco endereco = new Endereco();
		endereco.setCidade("Manaus");
		endereco.setLogradouro("Rua das Valquirias, 32");
		endereco.setTipoEndereco(TipoEndereco.RESIDENCIAL);
		
		pessoa.setEnderecos(Arrays.asList(endereco));
		pessoa.addTelefone(new Telefone(TipoTelefone.RESIDENCIAL, "32220303"));
		
		pessoaRepository.save(pessoa);
		
		Pessoa p2 = pessoaRepository.findOne(pessoa.getId());
		
		System.out.println(p2.toString());
		
	}

	private void testConfiguration() {
		
		long total = pessoaRepository.count();		
		System.out.println("Total of pessoa = " + total);
		
		List<Pessoa> pessoa = pessoaRepository.findAll();
		pessoa.forEach(System.out::println);
		
		long t2 = enderecoRepository.count();		
		System.out.println("Total of enderços = " + t2);
		
		long t3 = documentoRepository.count();		
		System.out.println("Total of documentos = " + t3);
		
		long t4 = telefoneRepository.count();		
		System.out.println("Total of phones = " + t4);
		
	}
}
