package com.catalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.catalogue.entities.Panier;

@Repository
public interface PanierDao extends JpaRepository<Panier, Long>{

}
