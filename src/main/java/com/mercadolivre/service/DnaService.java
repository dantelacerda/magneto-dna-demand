package com.mercadolivre.service;

import com.mercadolivre.exception.InputValidationException;

public interface DnaService {
	
	/**
	 * Evaluates if a subject is a mutant
	 * 
	 * @param The mutant information to be evaluated in order to check if the subject is mutant or not 
	 * @return True if the subject is mutant or false if the subject is not.
	 */
	boolean isMutant(String[] param) throws InputValidationException;
}
