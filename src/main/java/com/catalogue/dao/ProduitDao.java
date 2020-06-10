package com.catalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.catalogue.entities.Produit;

@Repository
public interface ProduitDao extends JpaRepository<Produit, Long>{

}
