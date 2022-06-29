package br.com.ribeiro.fernando.springbatchdemo.application.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DemoJobService {
	
	private JobLauncher jobLauncher;
	
	@Qualifier("job")
	private Job job;
	
	@Autowired
	public DemoJobService(JobLauncher jobLauncher, Job job) {
		this.jobLauncher = jobLauncher;
		this.job = job;
	}

	/**
	 * Start job via API.
	 */
	@Async
	public void startJob() {
		
		Map<String, JobParameter> params = new HashMap<String, JobParameter>();
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));
		
		JobParameters jobParameters = new JobParameters(params);
		
		try {
			
			JobExecution run = jobLauncher.run(job, jobParameters);
			
			System.out.println("Job finished! Execution ID: " + run.getId());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * Run every 1 minute.
	 */
	// @Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void scheduleJob() {
		
		Map<String, JobParameter> params = new HashMap<String, JobParameter>();
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));
		
		JobParameters jobParameters = new JobParameters(params);
		
		try {
			
			JobExecution run = jobLauncher.run(job, jobParameters);
			
			System.out.println("Job finished! Execution ID: " + run.getId());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
