package br.com.ribeiro.fernando.springbatchdemo.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contractor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String cnum;
	private String openSeatId;
	private String contractorAssignee;
	private String ticket;
	private String cartRequisitionId;
	private String po;
	private String comments;
	private String workLocationCode;
	private String supplierSideContact;
	private String workPlaceIndicator;
	private String deployedBandEquivalent;
	private String sector;
	
	public long getId() {
		return id;
	}

	public String getCnum() {
		return cnum;
	}
	
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	
	public String getOpenSeatId() {
		return openSeatId;
	}
	
	public void setOpenSeatId(String openSeatId) {
		this.openSeatId = openSeatId;
	}
	
	public String getContractorAssignee() {
		return contractorAssignee;
	}
	
	public void setContractorAssignee(String contractorAssignee) {
		this.contractorAssignee = contractorAssignee;
	}
	
	public String getTicket() {
		return ticket;
	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public String getCartRequisitionId() {
		return cartRequisitionId;
	}
	
	public void setCartRequisitionId(String cartRequisitionId) {
		this.cartRequisitionId = cartRequisitionId;
	}
	
	public String getPo() {
		return po;
	}
	
	public void setPo(String po) {
		this.po = po;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getWorkLocationCode() {
		return workLocationCode;
	}
	
	public void setWorkLocationCode(String workLocationCode) {
		this.workLocationCode = workLocationCode;
	}
	
	public String getSupplierSideContact() {
		return supplierSideContact;
	}
	
	public void setSupplierSideContact(String supplierSideContact) {
		this.supplierSideContact = supplierSideContact;
	}
	
	public String getWorkPlaceIndicator() {
		return workPlaceIndicator;
	}
	
	public void setWorkPlaceIndicator(String workPlaceIndicator) {
		this.workPlaceIndicator = workPlaceIndicator;
	}
	
	public String getDeployedBandEquivalent() {
		return deployedBandEquivalent;
	}
	
	public void setDeployedBandEquivalent(String deployedBandEquivalent) {
		this.deployedBandEquivalent = deployedBandEquivalent;
	}
	
	public String getSector() {
		return sector;
	}
	
	public void setSector(String sector) {
		this.sector = sector;
	}
	
	@Override
	public String toString() {
		return "Contractor supplier side contact: " + this.supplierSideContact
				+ "CNUM: " + this.cnum
				+ "Work Location Code: " + this.workLocationCode;
	}

}
