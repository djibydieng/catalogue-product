package com.catalogue.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

/* Entite Panier */

@Entity
@Table(name="paniers")
public class Panier implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idclient", nullable = false)
	private CompteClient client;
	
	private BigDecimal subtotal;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "panier",cascade=CascadeType.ALL)
	//@Cascade(CascadeType.SAVE_UPDATE)
	private List<LigneCommande> lignes = new ArrayList<LigneCommande>();
	
	/*Constructeur sans parametre */
	public Panier() {
		super();
		
	}
	
	/* Constructeur avec parametres */
	public Panier(Long idPanier, CompteClient client, BigDecimal subtotal) {
		super();
		this.id = idPanier;
		this.client = client;
		this.subtotal = subtotal;
	}

   /* Setters et getters */
	
	public Long getId() {
		return id;
	}
	public void setId(Long idPanier) {
		this.id = idPanier;
	}
	
	public CompteClient getClient() {
		return client;
	}
	public void setClient(CompteClient client) {
		this.client = client;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	//@JsonIgnore
	public List<LigneCommande> getLignes() {
		return lignes;
	}
	public void setLignes(List<LigneCommande> lignes) {
		this.lignes = lignes;
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for (LigneCommande ligne : this.getLignes()) {
			total.add(ligne.getPrix().multiply(new BigDecimal(ligne.getQuantite())));		
		}
		return total;
	}
	
}
