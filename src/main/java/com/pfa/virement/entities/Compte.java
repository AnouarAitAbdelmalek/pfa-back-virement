package com.pfa.virement.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Compte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(unique=true)
	String numeroCompte;
	
	String intitule;
	
	BigDecimal soldeComptable;
	
	@ManyToOne
	Abonne proprietaire;
	

	public Compte() {
		super();
	}


	public Compte(Long id, String numeroCompte, String intitule, BigDecimal soldeComptable, Abonne proprietaire) {
		super();
		this.id = id;
		this.numeroCompte = numeroCompte;
		this.intitule = intitule;
		this.soldeComptable = soldeComptable;
		this.proprietaire = proprietaire;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNumeroCompte() {
		return numeroCompte;
	}


	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}


	public String getIntitule() {
		return intitule;
	}


	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public BigDecimal getSoldeComptable() {
		return soldeComptable;
	}


	public void setSoldeComptable(BigDecimal soldeComptable) {
		this.soldeComptable = soldeComptable;
	}


	public Abonne getProprietaire() {
		return proprietaire;
	}


	public void setProprietaire(Abonne proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	


}
