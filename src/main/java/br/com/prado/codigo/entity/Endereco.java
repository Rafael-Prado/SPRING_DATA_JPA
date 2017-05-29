package br.com.prado.codigo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum TipoEndereco {
		COMERCIAL, RESIDENCIAL
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name= "endereco_id")
	private Long id ;
	
	@Column(nullable= false)
	private String cidade;
	
	@Column(nullable= false)
	private String logradouro;
	
	@Enumerated(EnumType.STRING)
	@Column(name= "tipo_endereco", nullable= false)
	private TipoEndereco tipoEndereco;
	
	
	@ManyToMany(fetch =FetchType.EAGER)
	@JoinTable(
			name="pessoa_endereco",
			joinColumns =@JoinColumn(name="endereco_id"),
			inverseJoinColumns=@JoinColumn(name="pessoa_id")
			)
	private List<Pessoa> pessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public List<Pessoa> getPessoa() {
		return pessoa;
	}

	public void setPessoa(List<Pessoa> pessoa) {
		this.pessoa = pessoa;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cidade=" + cidade + ", logradouro=" + logradouro + ", tipoEndereco="
				+ tipoEndereco + ", pessoa=" + pessoa + "]";
	}

	
}
