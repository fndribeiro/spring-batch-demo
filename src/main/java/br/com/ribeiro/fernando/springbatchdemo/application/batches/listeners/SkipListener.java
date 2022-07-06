package br.com.ribeiro.fernando.springbatchdemo.application.batches.listeners;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import br.com.ribeiro.fernando.springbatchdemo.domain.entities.Contractor;

@Component
public class SkipListener {
	
	@OnSkipInRead
	public void skipInRead(Throwable throwable) {
		
		if (throwable instanceof FlatFileParseException ) {
			
			((FlatFileParseException) throwable).getLineNumber();
			((FlatFileParseException) throwable).getInput();
			
			// save bad records in database
		}
		
	}
	
	@OnSkipInProcess
	public void skipInProcess(Contractor contractor, Throwable throwable) {
		
		// catch and process any errors
		
	}
	
	@OnSkipInWrite
	public void skipInWrite(Contractor contractor, Throwable throwable) {
		
		// catch and process any errors
		
	}
	
}
