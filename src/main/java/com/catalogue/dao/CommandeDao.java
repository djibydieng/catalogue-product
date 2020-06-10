package com.catalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalogue.entities.Commande;

@Repository
public interface CommandeDao extends JpaRepository<Commande, Long>{

}
