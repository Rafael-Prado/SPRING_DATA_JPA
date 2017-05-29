package br.com.prado.codigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prado.codigo.entity.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{

}
