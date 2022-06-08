package br.com.ribeiro.fernando.springbatchdemo.application.batches.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class LongItemWriter implements ItemWriter<Long> {

	@Override
	public void write(List<? extends Long> items) throws Exception {
		
		System.out.println("Writing item...");
		
		items.forEach(System.out::println);
		
	}

}
