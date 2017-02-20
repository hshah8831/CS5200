package edu.neu.cs5200.chaanda.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the donor database table.
 * 
 */
@Entity
@NamedQuery(name="Donor.findAll", query="SELECT d FROM Donor d")
public class Donor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int donorId;

	private int bankAccountId;

	public Donor() {
	}

	public int getDonorId() {
		return this.donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public int getBankAccountId() {
		return this.bankAccountId;
	}

	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

}