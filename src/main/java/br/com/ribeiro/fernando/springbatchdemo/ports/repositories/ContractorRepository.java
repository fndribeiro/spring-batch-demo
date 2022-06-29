package br.com.ribeiro.fernando.springbatchdemo.ports.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long>{

}
