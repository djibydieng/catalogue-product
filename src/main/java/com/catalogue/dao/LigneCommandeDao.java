package com.catalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.catalogue.entities.LigneCommande;

@Repository
public interface LigneCommandeDao extends JpaRepository<LigneCommande, Long>{
	
	@Query("FROM LigneCommande l WHERE l.panier.id = :idPanier AND l.produit.id = :idProduit")
	public LigneCommande rechercher(@Param("idPanier")Long idPanier, @Param("idProduit")Long idProduit);

}
