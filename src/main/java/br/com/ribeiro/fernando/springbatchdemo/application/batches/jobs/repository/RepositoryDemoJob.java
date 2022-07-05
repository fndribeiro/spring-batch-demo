package br.com.ribeiro.fernando.springbatchdemo.application.batches.jobs.repository;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;
import br.com.ribeiro.fernando.springbatchdemo.ports.repositories.ContractorRepository;

public class RepositoryDemoJob {
	
	@Autowired
	private ContractorRepository repository;

	public RepositoryItemReader<Contractor> repositoryItemReader() {
		
		RepositoryItemReader<Contractor> repositoryItemReader = new RepositoryItemReader<Contractor>();
		repositoryItemReader.setRepository(repository);
		repositoryItemReader.setMethodName("findAll");
		
		return repositoryItemReader;
	};
	
	
	
}
