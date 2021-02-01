package com.pfa.virement.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Abonne {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(unique=true)
	String username;
	
	String password;
	
	String nom;
	
	String prenom;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="proprietaire")
	List<Compte> comptes;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="abonne")
	List<Beneficiaire> beneficiaires;
	

	public Abonne() {
		super();
	}



	public Abonne(Long id, String username, String password, String nom, String prenom, List<Compte> comptes,
			List<Beneficiaire> beneficiaires) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.comptes = comptes;
		this.beneficiaires = beneficiaires;
	}







	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public List<Compte> getComptes() {
		return comptes;
	}


	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}


	public List<Beneficiaire> getBeneficiaires() {
		return beneficiaires;
	}


	public void setBeneficiaires(List<Beneficiaire> beneficiaires) {
		this.beneficiaires = beneficiaires;
	}

	
}
