package com.mercadolivre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolivre.model.Human;
import com.mercadolivre.repository.HumanRepository;

@Service
public class HumanService {

	@Autowired
	private HumanRepository humanRepository;

	public List<Human> buscarTodosHumans() {
		return humanRepository.findAll();
	}

}
