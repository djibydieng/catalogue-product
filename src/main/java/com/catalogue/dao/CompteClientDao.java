package com.catalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catalogue.entities.CompteClient;

@Repository
public interface CompteClientDao extends JpaRepository<CompteClient, Long>{

	@Query("FROM CompteClient c WHERE c.username = :username")
	public CompteClient findCompteByUsername(@Param("username")String username);
}
