package br.com.prado.codigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prado.codigo.model.Pessoa;

public interface IPessoaRepository extends JpaRepository<Pessoa, Long>{

}
