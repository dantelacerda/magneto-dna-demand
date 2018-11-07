package com.mercadolivre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolivre.model.Mutant;
import com.mercadolivre.repository.MutantRepository;

@Service
public class MutantService {

	@Autowired
	private MutantRepository mutantRepository;

	public List<Mutant> buscarTodosMutants() {
		return mutantRepository.findAll();
	}

}
