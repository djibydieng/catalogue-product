package com.catalogue.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.catalogue.dao.CommandeDao;
import com.catalogue.dao.LigneCommandeDao;
import com.catalogue.dao.PanierDao;
import com.catalogue.dao.ProduitDao;
import com.catalogue.entities.Commande;
import com.catalogue.entities.LigneCommande;
import com.catalogue.entities.Panier;
import com.catalogue.entities.Produit;

@Service
public class PanierServiceImpl implements PanierService{

	@Autowired
	private ProduitDao produitDao;
	
	@Autowired
	private PanierDao panierDao;
	
	@Autowired
	private LigneCommandeDao ligneCommandeDao;
	
	@Autowired
	private CommandeDao commandeDao;
	
	@Override
	public void creerPanier(Panier panier) {
		this.panierDao.save(panier);
		
	}

	@Override
	public void ajouterProduit(Long idPanier, Long idProd, int quantite) {
		
		Panier panier = this.panierDao.findById(idPanier).get();
		Produit produit = this.produitDao.findById(idProd).get();
		panier.getLignes().add(new LigneCommande(panier, produit, quantite, produit.getPrix()));
		panierDao.save(panier);;
		
	}

	@Override
	public void commander(Long idPanier) {
		Panier panier = this.panierDao.findById(idPanier).get();
		Commande commande = new Commande();
		List<LigneCommande> lignes = panier.getLignes();
		commande.setClient(panier.getClient());
		commande.setDateCommande(new Date());
		commande.setLignes(lignes);
		commande.setStatus("OK");
		commande.setTotal(panier.getTotal());
		this.commandeDao.save(commande);
	}

	@Override
	public void supprimerProduit(Long idPanier, Long idProd) throws NotFoundException {
		try {
		Panier panier = this.panierDao.findById(idPanier).get();
		LigneCommande ligne  = this.ligneCommandeDao.rechercher(idPanier, idProd);
	    Boolean supp = panier.getLignes().remove(ligne);
	    if(ligne != null)
	    	this.ligneCommandeDao.delete(ligne);
	    if(supp)
	    	System.out.println("Product deleted");
	    else
	    	System.out.println("Product not deleted");
		this.panierDao.save(panier);
		}catch(Exception e) {
			System.out.println("Suppression impossible");
			e.printStackTrace();
		}

	}

	@Override
	public List<Produit> listerProduitsPanier(Long idPanier) {
		Panier panier  = this.panierDao.findById(idPanier).orElse(null);
		if(panier == null)
			return null;
		List<LigneCommande> lignes = panier.getLignes();
		List<Produit> produits = new ArrayList<Produit>();
		for(LigneCommande ligne: lignes) {
			produits.add(ligne.getProduit());
		}
		return produits;
	}

}
