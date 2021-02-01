package com.pfa.virement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.virement.entities.Beneficiaire;
import com.pfa.virement.exceptions.ConflictException;
import com.pfa.virement.exceptions.NotFoundException;
import com.pfa.virement.repositories.BeneficiaireRepository;

@Service
public class BeneficiaireService {
	
	
	@Autowired
	BeneficiaireRepository beneficiaireRepository;
	
	
	
	public void addBeneficiaire(Beneficiaire beneficiaire)
	{
		if(beneficiaireRepository.findByNumeroCompte(beneficiaire.getNumeroCompte()).isPresent()) 
			throw new ConflictException("Un bénéficiaire avec le numéro de compte "+beneficiaire.getNumeroCompte()+" existe déjé.");
		
		beneficiaireRepository.save(beneficiaire);
		
	}
	
	
	public Beneficiaire getBeneficiaire(Long id)
	{
		
		Beneficiaire beneficiaire = beneficiaireRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun bénéficiaire avec l'id "+id+" trouvé."));
		
		return beneficiaire;
	}
	
	
	
	
	public void deleteBeneficiaire(Long id)
	{
		if(!beneficiaireRepository.findById(id).isPresent())  throw new NotFoundException("Aucun bénéficiaire avec l'id "+id+" trouvé.");
		beneficiaireRepository.deleteById(id);
	}

}
