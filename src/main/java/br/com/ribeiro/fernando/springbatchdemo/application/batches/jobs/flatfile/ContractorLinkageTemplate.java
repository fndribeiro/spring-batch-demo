package br.com.ribeiro.fernando.springbatchdemo.application.batches.jobs.flatfile;

public class ContractorLinkageTemplate {
	
	private static final String[] COLUMNS = 
		{
			"CNUM", 
			"OPEN SEAT ID", 
			"CONTRACTOR ASSIGNEE", 
			"TICKET", 
			"CART/REQUISITION ID", 
			"PO", 
			"COMMENTS", 
			"WORK LOCATION CODE", 
			"SUPPLIER SIDE CONTACT", 
			"WORKPLACE INDICATOR", 
			"DEPLOYED BAND EQUIVALENT", 
			"SECTOR"
		};
	
	public static String[] build() {
		return COLUMNS;
	}

}
