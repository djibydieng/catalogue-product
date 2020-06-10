package com.catalogue.entities;

import java.io.Serializable;
import java.math.BigDecimal;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="produits")
public class Produit implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//private Categorie categorie;
	private String description;
	private BigDecimal prix;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "produit")
	//private List<LigneCommande> lignes = new ArrayList<LigneCommande>();
	
	
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produit(String description, BigDecimal prix) {
		super();
		this.description = description;
		this.prix = prix;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long idProduct) {
		this.id = idProduct;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrix() {
		return prix;
	}
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	/*@JsonIgnore
	public List<LigneCommande> getLignes() {
		return lignes;
	}
	@JsonIgnore
	public void setLignes(List<LigneCommande> lignes) {
		this.lignes = lignes;
	}*/
	
	
}
