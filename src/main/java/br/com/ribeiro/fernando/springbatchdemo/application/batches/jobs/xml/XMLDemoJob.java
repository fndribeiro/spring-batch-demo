package br.com.ribeiro.fernando.springbatchdemo.application.batches.jobs.xml;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;

public class XMLDemoJob {
	
	@StepScope
	@Bean
	public StaxEventItemReader<Contractor> staxEventItemReader(@Value("#{jobParameters['file']}") FileSystemResource fileSystemResource) {
		
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setClassesToBeBound(Contractor.class);
		
		StaxEventItemReader<Contractor> staxEventItemReader = new StaxEventItemReader<Contractor>();
		staxEventItemReader.setResource(fileSystemResource);
		staxEventItemReader.setFragmentRootElementName("contractor");
		staxEventItemReader.setUnmarshaller(jaxb2Marshaller);
		
		return staxEventItemReader;
	}

}
