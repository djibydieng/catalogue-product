package com.catalogue.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lignecommandes")
public class LigneCommande implements Serializable{

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idpanier", nullable = false)
	private Panier panier;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproduit", nullable = false)
	private Produit produit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcommande")
	private Commande commande;
	private Integer quantite;
	private BigDecimal prix;
	
	public LigneCommande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LigneCommande(Panier panier, Produit produit, Commande commande, Integer quantite, BigDecimal prix) {
		super();
		this.panier = panier;
		this.produit = produit;
		this.commande = commande;
		this.quantite = quantite;
		this.prix = prix;
	}

	public LigneCommande(Panier panier, Produit produit, Integer quantite, BigDecimal prix) {
		super();
		this.panier = panier;
		this.produit = produit;
		this.quantite = quantite;
		this.prix = prix;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long idligne) {
		this.id = idligne;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	
	
}
