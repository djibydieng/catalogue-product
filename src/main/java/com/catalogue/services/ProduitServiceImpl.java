package com.catalogue.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogue.dao.ProduitDao;
import com.catalogue.entities.Produit;

@Service
public class ProduitServiceImpl implements ProduitService{

	@Autowired
	private ProduitDao produitDao;
	
	@Override
	public List<Produit> listerProduits() {
		
		List<Produit> produits = this.produitDao.findAll();
		return produits;
	}

	@Override
	public Produit listerProduitParId(Long id) {
		
		Optional<Produit> produit = this.produitDao.findById(id);
		return produit.get();
	}

	@Override
	public void ajouterProduit(Produit prod) {
		System.out.println("Product name: "+prod.getDescription());
		this.produitDao.save(prod);
		System.out.println("Successfully saved");
		
	}

	@Override
	public void supprimerProduit(Long id) {
		this.produitDao.deleteById(id);
		
	}

	@Override
	public void modifierProduit(Produit prod) {
		
		this.produitDao.save(prod);
		
	}

}
