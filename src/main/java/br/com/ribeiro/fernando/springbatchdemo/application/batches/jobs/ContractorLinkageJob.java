package br.com.ribeiro.fernando.springbatchdemo.application.batches.jobs;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;

import br.com.ribeiro.fernando.springbatchdemo.application.batches.writers.ContractorItemWriter;
import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;

@Configuration
public class ContractorLinkageJob {
	
	private StepBuilderFactory stepBuilder;
	private JobBuilderFactory jobBuilder;
	private ContractorItemWriter itemWriter;
	
	private final String[] columns = 
			{"CNUM", 
			"OPEN SEAT ID", 
			"CONTRACTOR ASSIGNEE", 
			"TICKET", 
			"CART/REQUISITION ID", 
			"PO", 
			"COMMENTS", 
			"WORK LOCATION CODE", 
			"SUPPLIER SIDE CONTACT", 
			"WORKPLACE INDICATOR", 
			"DEPLOYED BAND EQUIVALENT", 
			"SECTOR"};
	
	private File fileToDelete;
	
	@Autowired
	public ContractorLinkageJob(StepBuilderFactory stepBuilder, JobBuilderFactory jobBuilder, ContractorItemWriter itemWriter) {
		this.stepBuilder = stepBuilder;
		this.jobBuilder = jobBuilder;
		this.itemWriter = itemWriter;
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Lazy
	public Job contractorLinkage() {
		return jobBuilder
				.get("Contractor Linkage Job")
				.incrementer(new RunIdIncrementer())
				.start(importContractor())
				.next(deleteFileTaskletStep())
				.build();
	}
	
	private Step importContractor() {
		return stepBuilder
				.get("Contractor Linkage Step")
				.<Contractor, Contractor>chunk(3)
				.reader(flatFileItemReader(null))
				.writer(itemWriter)
				.build();
	}
	
	@StepScope
	@Bean
	public FlatFileItemReader<Contractor> flatFileItemReader(@Value("#{jobParameters['file']}") FileSystemResource fileSystemResource) {
		
		fileToDelete = fileSystemResource.getFile();
		
		DelimitedLineTokenizer delimiter = new DelimitedLineTokenizer();
		delimiter.setNames(columns);
		
		BeanWrapperFieldSetMapper<Contractor> fieldMapper = new BeanWrapperFieldSetMapper<Contractor>();
		fieldMapper.setTargetType(Contractor.class);
		
		DefaultLineMapper<Contractor> lineMapper = new DefaultLineMapper<Contractor>();
		lineMapper.setLineTokenizer(delimiter);
		lineMapper.setFieldSetMapper(fieldMapper);
		
		FlatFileItemReader<Contractor> flatFileItemReader = new FlatFileItemReader<Contractor>();
			
		flatFileItemReader.setResource(fileSystemResource);
		flatFileItemReader.setLineMapper(lineMapper);
			
		// Skip header.
		flatFileItemReader.setLinesToSkip(1);
		
		return flatFileItemReader;
		
	}
	
	private Tasklet deleteFileStep() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				
				fileToDelete.delete();
				
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	private Step deleteFileTaskletStep() {
		return stepBuilder
				.get("Delete File Tasklet Step")
				.tasklet(deleteFileStep())
				.build();
	}
	
}
