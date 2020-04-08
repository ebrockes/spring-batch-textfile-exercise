package br.com.sample.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sample.model.Texto;

public interface TextoRepository extends JpaRepository<Texto,Integer>{

}
