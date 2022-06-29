package br.com.ribeiro.fernando.springbatchdemo.application.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

public abstract class BatchService {
	
	Logger logger = LoggerFactory.getLogger(BatchService.class);
	
	@Autowired
	private JobLauncher jobLauncher;
	
	public abstract BatchService writefile(MultipartFile file) throws IOException;
	
	@Async
	public void startJob(Job job, JobParameters jobParameters) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		
		logger.info("Job execution successfull. JOBID: " + jobExecution.getId());
		
	};

}
