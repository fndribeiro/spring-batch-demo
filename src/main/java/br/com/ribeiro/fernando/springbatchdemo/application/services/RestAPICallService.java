package br.com.ribeiro.fernando.springbatchdemo.application.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;

@Service
public class RestAPICallService {
	
	private List<Contractor> contractors;

	private List<Contractor> getContractors() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Contractor[] contractorResponseArray = restTemplate.getForObject("http://localhost:8081/contractors", Contractor[].class);
		
		contractors = new ArrayList<>();
		contractors.addAll(Arrays.asList(contractorResponseArray));
		
		return contractors;
	}
	
	public Contractor getContractor() {
		
		if (contractors == null) {
			getContractors();
		}
		
		if (!contractors.isEmpty()) {
			return contractors.remove(0);
		}
		
		return null;
	}
	
}
