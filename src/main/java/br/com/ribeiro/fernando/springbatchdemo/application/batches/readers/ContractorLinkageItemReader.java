package br.com.ribeiro.fernando.springbatchdemo.application.batches.readers;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.web.multipart.MultipartFile;

public class ContractorLinkageItemReader implements ItemReader<MultipartFile> {

	@Override
	public MultipartFile read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		
		
		return null;
	}

}
