package com.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolivre.model.Human;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {

	@Query("SELECT c.id From human c where c.dnaseq like %?1%")
	public Human buscaHumanPorSeq(String seq);

}
