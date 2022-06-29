package br.com.ribeiro.fernando.springbatchdemo.application.batches.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;
import br.com.ribeiro.fernando.springbatchdemo.ports.repositories.ContractorRepository;

@Component
public class ContractorItemWriter implements ItemWriter<Contractor> {
	
	private ContractorRepository repository;
	
    @Autowired
	public ContractorItemWriter(ContractorRepository repository) {
		this.repository = repository;
	}

	@Override
	public void write(List<? extends Contractor> items) throws Exception {
		
		System.out.println("Saving contractors...");
		
		items.forEach(item -> {
			repository.save(item);
		});
		
	}

}
