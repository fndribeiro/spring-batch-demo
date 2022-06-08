package br.com.ribeiro.fernando.springbatchdemo.ports.controllers;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ribeiro.fernando.springbatchdemo.application.services.DemoJobService;
import br.com.ribeiro.fernando.springbatchdemo.ports.controllers.contants.ControllersURIs;

@RestController
@RequestMapping(ControllersURIs.DEMO_JOB)
public class DemoJobController {
	
	private DemoJobService jobService;
	private JobOperator jobOperator;
	
	@Autowired
	public DemoJobController(DemoJobService jobService, JobOperator jobOperator) {
		this.jobService = jobService;
		this.jobOperator = jobOperator;
	}

	@GetMapping("/start")
	public String startJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		jobService.startJob();
		
		return "Job started...";
	}
	
	@GetMapping("/stop/{jobExecutionId}")
	public String stopJob(@PathVariable long jobExecutionId) throws NoSuchJobExecutionException, JobExecutionNotRunningException {
		
		jobOperator.stop(jobExecutionId);
		
		return "Job stopped...";
	}

}
