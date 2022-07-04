package br.com.ribeiro.fernando.springbatchdemo.application.batches.tasklets;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import br.com.ribeiro.fernando.springbatchdemo.application.batches.contants.JobParameterConstants;

@Component
public class DeleteFileTaskletStep implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		String filePath = (String) chunkContext
			.getStepContext()
			.getJobParameters()
			.get(JobParameterConstants.FILE);
		
		Files.delete(Paths.get(filePath));
		
		return RepeatStatus.FINISHED;
	}

}
