package br.com.prado.codigo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

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
@ImportResource(value = "spring-data.xml")
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
		//testDelete();
		//TestSeivePessoas();
		//testeDeletePessoas();
		
		//testFindAndSort();
		//testFindByIds();
		//testExists();
		testPagination();
		
		
		
	}

	private void testPagination() {
		Page<Pessoa>pages = pessoaRepository.findAll(new PageRequest(0, 2));
		pages.getContent().forEach(System.out:: println);
		
	    pages = pessoaRepository.findAll(new PageRequest(1, 2));
		pages.getContent().forEach(System.out:: println);
		
	}

	private void testExists() {
		boolean pessoa = pessoaRepository.exists(2L);
		System.out.println("Pessoa is " +  pessoa);
		
		boolean pessoa1 = pessoaRepository.exists(50L);
		System.out.println("Pessoa is " +  pessoa1);
		
	}

	private void testFindByIds() {
		List<Pessoa> pessoas = pessoaRepository.findAll(Arrays.asList(1L, 3L, 2L));
		
		pessoas.forEach(System.out:: println);
		
	}

	private void testFindAndSort() {
		
		Order orderAsc =  new Order(Direction.ASC, "sobreNome");
		
		Order orderDesc = new Order(Direction.ASC, "primeiroNome");
		
		Sort sort =  new Sort(orderDesc ,orderAsc);
		
		List<Pessoa> pessoas  = pessoaRepository.findAll(sort);
		
		pessoas.forEach(System.out:: println);
		
	}

	private void testeDeletePessoas() {
		//Pessoa p1 = pessoaRepository.findOne(11L);
		//Pessoa p2 = pessoaRepository.findOne(12L);
		//Pessoa p3 = pessoaRepository.findOne(13L);
		
		//pessoaRepository.delete(Arrays.asList(p1, p2, p3));
		
		System.out.println("###################################");
		
		Pessoa p4 = pessoaRepository.findOne(6L);
		Pessoa p5 = pessoaRepository.findOne(10L);
		Pessoa p6 = pessoaRepository.findOne(14L);
		
		pessoaRepository.deleteInBatch(Arrays.asList(p4, p5, p6));
		
	}

	private void TestSeivePessoas() {
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setPrimeiroNome("João ");
		pessoa1.setSobreNome("Rios");
		pessoa1.setIdade(35);
		pessoa1.setDocumento(new Documento("695.852.963-74", 1236544485));
		
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setPrimeiroNome("Luiz");
		pessoa2.setSobreNome("Pereira");
		pessoa2.setIdade(35);
		pessoa2.setDocumento(new Documento("841.852.153-74", 1236548445));
		
		Pessoa pessoa3 = new Pessoa();
		pessoa3.setPrimeiroNome("Manuel");
		pessoa3.setSobreNome("JJ");
		pessoa3.setIdade(35);
		pessoa3.setDocumento(new Documento("841.852.695-74", 1235485));
		
		Pessoa pessoa4 = new Pessoa();
		pessoa4.setPrimeiroNome("Teresa");
		pessoa4.setSobreNome("criatna");
		pessoa4.setIdade(35);
		pessoa4.setDocumento(new Documento("841.695.963-74", 1236577485));
		
		Pessoa pessoa5 = new Pessoa();
		pessoa5.setPrimeiroNome("Jleuleu");
		pessoa5.setSobreNome("paranda");
		pessoa5.setIdade(35);
		pessoa5.setDocumento(new Documento("841.236.963-74", 1236547785));
		
		List<Pessoa> pessoas = 
		pessoaRepository.save(Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4, pessoa5));
		
		pessoas.forEach(System.out:: println);
		
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
