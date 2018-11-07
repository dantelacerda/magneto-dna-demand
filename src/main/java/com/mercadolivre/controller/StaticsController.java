package com.mercadolivre.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolivre.model.Human;
import com.mercadolivre.model.Mutant;
import com.mercadolivre.model.Stats;
import com.mercadolivre.service.HumanService;
import com.mercadolivre.service.MutantService;

@RestController
public class StaticsController {

	@Autowired
	private HumanService humanService;

	@Autowired
	private MutantService mutantService;

	private static Logger logger = Logger.getGlobal();

	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Stats getStatics() {

		Stats stats = null;

		try {
			stats = new Stats(mutantService.buscarTodosMutants().size(), humanService.buscarTodosHumans().size());
		} catch (ServiceException e) {
			logger.log(Level.ALL, e.getMessage());
		}

		return stats;
	}

	@RequestMapping(value = "/humans", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Human> getHumans() {

		List<Human> humans = null;

		try {
			humans = humanService.buscarTodosHumans();
		} catch (ServiceException e) {
			logger.log(Level.ALL, e.getMessage());
		}

		return humans;
	}

	@RequestMapping(value = "/mutants", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Mutant> getMutants() {

		List<Mutant> mutants = null;

		try {
			mutants = mutantService.buscarTodosMutants();
		} catch (ServiceException e) {
			logger.log(Level.ALL, e.getMessage());
		}

		return mutants;
	}
}
