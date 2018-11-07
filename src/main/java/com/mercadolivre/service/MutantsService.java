package com.mercadolivre.service;

import org.hibernate.service.spi.ServiceException;

import com.mercadolivre.exception.InputValidationException;

public interface MutantsService {
	
	public boolean isMutant(String[] dna) throws ServiceException, InputValidationException;
	
	boolean mutantExists(String[] param);

}
