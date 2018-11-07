package com.mercadolivre.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.exception.InputValidationException;
import com.mercadolivre.model.DNANumber;
import com.mercadolivre.service.MutantsService;

@RestController
public class MutantsController {
	
	@Autowired
	private MutantsService mutantsService;
	
	private static Logger logger =  Logger.getGlobal();
	
	@RequestMapping(value="/mutant", method=RequestMethod.POST)
	public ResponseEntity<String> analizeMutantCandidate(@RequestBody DNANumber dna) {
		
		ResponseEntity<String> responseEntity = null;
		boolean isMutant;
		boolean hasMutant;

		try {
			
			hasMutant = mutantsService.mutantExists(dna.getDna());
			
			if(hasMutant) {
				responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
			} else {
				isMutant = mutantsService.isMutant(dna.getDna());
				
				if(isMutant) {
					responseEntity = new ResponseEntity<>(HttpStatus.OK);
				} else {
					responseEntity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
				}
			}
		} catch (ServiceException ex) {
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			logger.log(Level.ALL, ex.getMessage());		
		} catch (InputValidationException ex) {
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			logger.log(Level.ALL, ex.getMessage());
		}
	
		return responseEntity;
	}
}
