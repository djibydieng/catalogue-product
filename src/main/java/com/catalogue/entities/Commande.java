package com.catalogue.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="commandes")
public class Commande implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idclient", nullable = false)
	private CompteClient client;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datecommande", nullable = false, length = 19)
	private Date dateCommande;
	private String status;
	private BigDecimal total;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "commande",cascade=CascadeType.ALL)
	//@Cascade(CascadeType.SAVE_UPDATE)
	private List<LigneCommande> lignes = new ArrayList<LigneCommande>();
	
	public Commande(Long idCommande, CompteClient client, Date dateCommande, String status, BigDecimal total,
			List<LigneCommande> lignes) {
		super();
		this.id = idCommande;
		this.client = client;
		this.dateCommande = dateCommande;
		this.status = status;
		this.total = total;
		this.lignes = lignes;
	}

	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CompteClient getClient() {
		return client;
	}

	public void setClient(CompteClient client) {
		this.client = client;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<LigneCommande> getLignes() {
		return lignes;
	}

	public void setLignes(List<LigneCommande> lignes) {
		this.lignes = lignes;
	}
	
	
	
}
