package br.com.ribeiro.fernando.springbatchdemo.application.batches.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.ribeiro.fernando.springbatchdemo.application.batches.processors.IntegerToLongItemProcessor;
import br.com.ribeiro.fernando.springbatchdemo.application.batches.readers.IntegerItemReader;
import br.com.ribeiro.fernando.springbatchdemo.application.batches.writers.LongItemWriter;

@Configuration
public class DemoJob {

	private IntegerItemReader itemReader;
	private IntegerToLongItemProcessor itemProcessor;
	private LongItemWriter itemWriter;
	private StepBuilderFactory stepBuilder;
	private JobBuilderFactory jobBuilder;
	
	@Autowired
	public DemoJob(IntegerItemReader itemReader, IntegerToLongItemProcessor itemProcessor, LongItemWriter itemWriter,
			StepBuilderFactory stepBuilder, JobBuilderFactory jobBuilder) {
		this.itemReader = itemReader;
		this.itemProcessor = itemProcessor;
		this.itemWriter = itemWriter;
		this.stepBuilder = stepBuilder;
		this.jobBuilder = jobBuilder;
	}

	@Bean(name = "job")
	public Job job() {
		return jobBuilder
				.get("Job 1")
				.incrementer(new RunIdIncrementer())
				.start(chunkStep1())
				.next(taskletStep1())
				.build();
	}
	
	private Step chunkStep1() {
		return stepBuilder
				.get("Chunk Step 1")
				.<Integer, Long>chunk(3)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
	}
	
	private Tasklet tasklet() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Sending e-mail...");
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	private Step taskletStep1() {
		return stepBuilder
				.get("Tasklet Step 1")
				.tasklet(tasklet())
				.build();
	}
	
}
