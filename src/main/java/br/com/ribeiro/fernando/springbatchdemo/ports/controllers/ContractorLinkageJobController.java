package br.com.ribeiro.fernando.springbatchdemo.ports.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.ribeiro.fernando.springbatchdemo.application.services.BatchService;
import br.com.ribeiro.fernando.springbatchdemo.ports.controllers.contants.ControllersURIs;

@RestController
@RequestMapping(ControllersURIs.CONTRACTOR_LINKAGE)
public class ContractorLinkageJobController {
	
	@Autowired
	private BatchService service;
	
	@Autowired
    ApplicationContext applicationContext;
	
	@PostMapping
	public String start(@RequestParam("file") MultipartFile file) {
		
		try {
			
			JobParameter fileJobParameter = service.writefile(file);
			
			Map<String, JobParameter> params = new HashMap<String, JobParameter>();
			params.put("currentTime", new JobParameter(System.currentTimeMillis()));
			params.put("file", fileJobParameter);
			
			JobParameters jobParameters = new JobParameters(params);
			
			Job job = (Job) applicationContext.getBean("contractorLinkage");
			
			service.startJob(job, jobParameters);
			
			return "File sent to queue.";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "Error sending file to queue!";
			
		}
		
	}
	
}
