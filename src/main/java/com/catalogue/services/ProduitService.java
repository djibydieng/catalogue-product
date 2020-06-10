package com.catalogue.services;

import java.util.List;

import com.catalogue.entities.Produit;

public interface ProduitService {

	public List<Produit> listerProduits();
	public Produit listerProduitParId(Long id);
	public void ajouterProduit(Produit prod);
	public void supprimerProduit(Long id);
	public void modifierProduit(Produit prod);
}
