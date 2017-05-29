package br.com.prado.codigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prado.codigo.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
