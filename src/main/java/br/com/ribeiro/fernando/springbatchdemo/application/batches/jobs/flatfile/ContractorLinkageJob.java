package br.com.ribeiro.fernando.springbatchdemo.application.batches.jobs.flatfile;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;

import br.com.ribeiro.fernando.springbatchdemo.application.batches.listeners.SkipListener;
import br.com.ribeiro.fernando.springbatchdemo.application.batches.tasklets.DeleteFileTaskletStep;
import br.com.ribeiro.fernando.springbatchdemo.application.batches.writers.ContractorItemWriter;
import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;
import br.com.ribeiro.fernando.springbatchdemo.ports.spring.beans.names.BeanNames;

@Configuration
public class ContractorLinkageJob {
	
	private StepBuilderFactory stepBuilder;
	private JobBuilderFactory jobBuilder;
	private ContractorItemWriter itemWriter;
	private DeleteFileTaskletStep deleteFileTaskletStep;
	private SkipListener skipListener;
	
	@Autowired
	public ContractorLinkageJob(StepBuilderFactory stepBuilder, JobBuilderFactory jobBuilder, ContractorItemWriter itemWriter, DeleteFileTaskletStep deleteFileTaskletStep, SkipListener skipListener) {
		this.stepBuilder = stepBuilder;
		this.jobBuilder = jobBuilder;
		this.itemWriter = itemWriter;
		this.deleteFileTaskletStep = deleteFileTaskletStep;
		this.skipListener = skipListener;
	}
	
	@Bean(name = BeanNames.CONTRACTOR_LINKAGE)
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
				.get("Contractor Linkage Chunk Step")
				.<Contractor, Contractor>chunk(3)
				.reader(flatFileItemReader(null))
				.writer(itemWriter)
				.faultTolerant()
				//.skip(FlatFileParseException.class) // skip bad records with this exception and continue batch load.
				//.skip(NullPointerException.class)
				//.skipLimit(10)
				//.skipPolicy(new AlwaysSkipItemSkipPolicy())
				.retryLimit(1)
				.retry(NullPointerException.class) // try one more time NPE in processors or readers.
				.listener(skipListener)
				.build();
	}
	
	@StepScope
	@Bean
	public FlatFileItemReader<Contractor> flatFileItemReader(@Value("#{jobParameters['file']}") FileSystemResource fileSystemResource) {
		
		DelimitedLineTokenizer delimiter = new DelimitedLineTokenizer();
		delimiter.setNames(ContractorLinkageTemplate.build());
		
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
	
	private Step deleteFileTaskletStep() {
		return stepBuilder
				.get("Delete File Tasklet Step")
				.tasklet(deleteFileTaskletStep)
				.build();
	}
	
}
