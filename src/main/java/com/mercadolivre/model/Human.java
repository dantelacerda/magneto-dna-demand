package com.mercadolivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "human")
public class Human {
	
	public Human() {
	}
	
	@Id
	@SequenceGenerator(name = "seq_human", sequenceName = "seq_human")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_human")
	private Long id;
	
	@Column(name = "dnaseq", nullable = false, length = 150)
	private String dnaseq;
	
	public Human(String[] dnaSequence) {
		this.dnaseq = String.join("", dnaSequence);
	}
	
	public String getDnaSequence() {
		return dnaseq;
	}

	public void setDnaSequence(String dnaSequence) {
		this.dnaseq = dnaSequence;
	}
}
