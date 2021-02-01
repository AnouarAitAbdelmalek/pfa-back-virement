package com.pfa.virement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.virement.entities.Abonne;
import com.pfa.virement.entities.Beneficiaire;
import com.pfa.virement.entities.Compte;
import com.pfa.virement.entities.Virement;
import com.pfa.virement.services.AbonneService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class AbonneController {

	@Autowired
	AbonneService service;
	
	

	//POST
	@PostMapping("/abonne")
	@ResponseStatus(HttpStatus.CREATED)
	public void addAbonne(@RequestBody Abonne abonne) 
	{
		service.addAbonne(abonne);
	}
	




	//GET
	@GetMapping("/abonne")
	@ResponseStatus(HttpStatus.OK)
	public Abonne getAbonne(@RequestParam(value="id") Long id)
	{
		return service.getAbonne(id);
		
	}
	
	
	//GET
		@GetMapping("/abonnes")
		@ResponseStatus(HttpStatus.OK)
		public List<Abonne> getAbonnes()
		{
			return service.getAbonnes();
			
		}
	
	
	
	//GET BY USERNAME
	@GetMapping("abonne/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Abonne getByUsername(@PathVariable(value="username") String username)
	{
		return service.getByUsername(username);
	}
	
	
	
	//GET COMPTES
	@GetMapping("/abonne/{id}/comptes")
	@ResponseStatus(HttpStatus.OK)
	public List<Compte> getComptes(@PathVariable(value="id") Long id) 
	{
		return service.getComptes(id);
	}
	

	//GET BENEFICIAIRES
	@GetMapping("/abonne/{id}/beneficiaires")
	@ResponseStatus(HttpStatus.OK)
	public List<Beneficiaire> getBeneficiaires(@PathVariable(value="id") Long id) 
	{
		return service.getBeneficiaires(id);
	}
	
	
	//DELETE
	@DeleteMapping("/abonne/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAbonne(@PathVariable(value="id") Long id)
	{
		service.deleteAbonne(id);
	}
	
	
	//Liste virements
	
	@GetMapping("/abonne/{id}/virements")
	public List<Virement> virements(@PathVariable(value="id") Long id)
	{
		return service.virements(id);
	}

}
