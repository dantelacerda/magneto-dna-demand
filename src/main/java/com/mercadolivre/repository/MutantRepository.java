package com.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolivre.model.Mutant;

@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {

	@Query("Select m From mutant m where m.dnaseq like %?1%")
	public Mutant buscaMutantPorSeq(String seq);
}
