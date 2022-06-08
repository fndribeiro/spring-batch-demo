package br.com.ribeiro.fernando.springbatchdemo.application.batches.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class IntegerToLongItemProcessor implements ItemProcessor<Integer, Long> {

	@Override
	public Long process(Integer item) throws Exception {
		
		System.out.println("Processing item...");
		
		return Long.valueOf(item + 20);
	}

	
	
}
