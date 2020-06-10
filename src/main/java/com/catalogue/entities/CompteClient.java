package com.catalogue.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comptes")
public class CompteClient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String prenom;
	private String nom;
	private String username;
	private String password;

	
	 //@JsonIgnore
	 
/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	 @Cascade(CascadeType.SAVE_UPDATE)
	private List<Commande> commandes = new ArrayList<Commande>();*/
	
	/*@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	@Cascade(CascadeType.SAVE_UPDATE)
	private List<Panier> paniers = new ArrayList<Panier>();*/
	
	public CompteClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteClient(String prenom, String nom, String username, String password) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

/*	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<Panier> getPaniers() {
		return paniers;
	}

	public void setPaniers(List<Panier> paniers) {
		this.paniers = paniers;
	}*/

}
