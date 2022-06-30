package br.com.ribeiro.fernando.springbatchdemo.application.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;

import org.springframework.batch.core.JobParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ribeiro.fernando.springbatchdemo.application.batches.contants.BatchJobsPathContants;

@Service
public class ContractorLinkageBatchServiceImpl extends BatchService {
	
	@Override
	public JobParameter writefile(MultipartFile file) throws IOException {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		// remove colons, dots, whitespaces and replace by -
		String fileName = timestamp
			.toString()
			.replaceAll("[:./\\s+/g]", "-");
		
		File targetFile = new File(BatchJobsPathContants.CSV_TEMP_FOLDER + BatchJobsPathContants.CONTRACTOR_LINKAGE_PATH + fileName + BatchJobsPathContants.CSV);
		
		Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		System.out.println(targetFile.getPath());
		
		return new JobParameter(targetFile.getPath());
	}

}
