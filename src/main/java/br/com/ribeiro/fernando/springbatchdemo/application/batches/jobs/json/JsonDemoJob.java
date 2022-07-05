package br.com.ribeiro.fernando.springbatchdemo.application.batches.jobs.json;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;

public class JsonDemoJob {
	
	@StepScope
	@Bean
	public JsonItemReader<Contractor> jsonItemReader(@Value("#{jobParameters['file']}") FileSystemResource fileSystemResource) {
		
		JsonItemReader<Contractor> jsonItemReader = new JsonItemReader<Contractor>();
		jsonItemReader.setResource(fileSystemResource);
		jsonItemReader.setJsonObjectReader(new JacksonJsonObjectReader<>(Contractor.class));
		
		return jsonItemReader;
	}

}
