package com.catalogue.services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.catalogue.entities.Panier;
import com.catalogue.entities.Produit;

public interface PanierService {

	public void creerPanier(Panier panier);
	public void ajouterProduit(Long idPanier, Long idProd, int quantite);
	public void commander(Long idPanier);
	public void supprimerProduit(Long idPanier, Long idProd) throws NotFoundException;
	public List<Produit> listerProduitsPanier(Long idPanier);
	
}
