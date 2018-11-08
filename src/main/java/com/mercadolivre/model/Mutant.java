package com.mercadolivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "mutant")
public class Mutant {
	
	public Mutant() {
	}
	
	@Id
	@SequenceGenerator(name = "seq_mutant", sequenceName = "seq_mutant")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mutant")
	private Long id;
	
	@Column(name = "dnaseq", nullable = false, length = 150)
	private String dnaseq;
	
	public Mutant(String[] dnaSequence) {
		this.dnaseq = String.join("", dnaSequence);
	}
	
	public String getDnaSequence() {
		return dnaseq;
	}

	public void setDnaSequence(String dnaSequence) {
		this.dnaseq = dnaSequence;
	}
}
