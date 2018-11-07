package com.mercadolivre.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolivre.exception.InputValidationException;
import com.mercadolivre.model.Human;
import com.mercadolivre.model.Mutant;
import com.mercadolivre.repository.HumanRepository;
import com.mercadolivre.repository.MutantRepository;
import com.mercadolivre.service.DnaService;
import com.mercadolivre.service.MutantsService;

@Service
public class MutantsServiceImpl implements MutantsService {

	@Autowired
	private HumanRepository humanRepository;

	@Autowired
	private MutantRepository mutantRepository;

	@Override
	public boolean isMutant(String[] dna) throws ServiceException, InputValidationException {

		DnaService mutantDetector = new DNAServiceImpl();
		boolean isMutant = false;

		isMutant = mutantDetector.isMutant(dna);

		if (isMutant) {
			mutantRepository.save(new Mutant(dna));
		} else {
			humanRepository.save(new Human(dna));
		}

		return isMutant;
	}
	
	@Override
	public boolean mutantExists(String[] dna) {

		boolean hasMutant = false;

		Mutant mut = mutantRepository.buscaMutantPorSeq(String.join("", dna));
		
		Human hum = humanRepository.buscaHumanPorSeq(String.join("", dna));

		if (mut != null && !"".equals(mut.getDnaSequence())) {
			hasMutant = true;
		} 
		
		if (hum != null && !"".equals(hum.getDnaSequence())) {
			hasMutant = true;
		} 
		
		return hasMutant;
	}
	
	
	

}
