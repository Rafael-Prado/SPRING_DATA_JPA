package br.com.prado.codigo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas",
indexes = {@Index(columnList= "primeiro_nome, sobre_nome ", name= "IDX_PESSOA_NOME", unique= true)})
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="pessoa_id")
	private Long id;
	
	@Column(name="primeiro_nome", nullable= false, length=30)
	private String primeiroNome;
	
	@Column(name="sobre_nome", nullable= false, length= 60)
	private String sobreNome;
	
	@Column(name="idade", nullable=false)
	private Integer idade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "documento_Id")
	private Documento documento;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Telefone> telefones;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch =FetchType.EAGER)
	@JoinTable(
			name= "pessoa_endereco",
			joinColumns=@JoinColumn(name="pessoa_id"),
			inverseJoinColumns= @JoinColumn(name= "endereco_id")
	)
	private List<Endereco> enderecos;
	
	
	public void addTelefone(Telefone telefone){
		if(telefones == null){
			telefones = new ArrayList<Telefone>();
		}
		telefone.setPessoa(this);
		telefones.add(telefone);
		
	}
	
	public void delTelefone(Telefone telefone){
		if(telefones != null){
			telefones.remove(telefone);
		}
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}	

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}	
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", primeiroNome=" + primeiroNome + ", sobreNome=" + sobreNome + ", idade=" + idade
				+ ", documento=" + documento + ", telefones=" + telefones + "]";
	}		
	
}
