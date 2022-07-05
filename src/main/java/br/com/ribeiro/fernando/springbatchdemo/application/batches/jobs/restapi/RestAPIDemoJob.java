package br.com.ribeiro.fernando.springbatchdemo.application.batches.jobs.restapi;

import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ribeiro.fernando.springbatchdemo.application.services.RestAPICallService;
import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;

public class RestAPIDemoJob {
	
	@Autowired
	private RestAPICallService service;
	
	public ItemReaderAdapter<Contractor> itemReaderAdapter() {
		
		ItemReaderAdapter<Contractor> itemReaderAdapter = new ItemReaderAdapter<Contractor>();
		itemReaderAdapter.setTargetObject(service);
		itemReaderAdapter.setTargetMethod("getContractor");
		
		return itemReaderAdapter;
	}

}
