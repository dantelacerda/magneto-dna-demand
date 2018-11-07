package com.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolivre.model.Mutant;

@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {

	@Query("From mutant where dnaSequence like %?1%")
	public Mutant buscaMutantPorSeq(String seq);
}
