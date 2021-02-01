package com.pfa.virement.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pfa.virement.entities.Abonne;
import com.pfa.virement.entities.Beneficiaire;
import com.pfa.virement.entities.Compte;
import com.pfa.virement.entities.Virement;
import com.pfa.virement.exceptions.NotFoundException;
import com.pfa.virement.repositories.AbonneRepository;
import com.pfa.virement.repositories.BeneficiaireRepository;
import com.pfa.virement.repositories.CompteRepository;
import com.pfa.virement.repositories.VirementRepository;

@Service
public class VirementService {
	
	@Autowired
	VirementRepository virementRepository;
	
	@Autowired
	BeneficiaireRepository beneficiaireRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	AbonneRepository abonneRepository;
	
	
	
	public void EffectuerVirement(Virement virement)
	{
		Abonne abonne = abonneRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		String numeroCompte = virement.getCompte().getNumeroCompte();
		Compte compte = compteRepository.findByNumeroCompte(numeroCompte)
				.orElseThrow(() -> new NotFoundException("Aucun compte avec le numéro "+numeroCompte+" trouvé."));
		
		String numeroBeneficiaire = virement.getBeneficiaire().getNumeroCompte();
		Beneficiaire beneficiaire = beneficiaireRepository.findByNumeroCompte(numeroBeneficiaire)
				.orElseThrow(() -> new NotFoundException("Aucun bénéficiaire avec le numéro "+numeroBeneficiaire+" trouvé."));
		
		virement.setCompte(compte);
		virement.setDateCreation(new Date());
		virement.setStatut("Enregistré");
		virement.setAbonne(abonne);
		virement.setBeneficiaire(beneficiaire);
		virementRepository.save(virement);
		
		
	}
	
	
	public Virement getVirement(Long id)
	{
		Virement virement = virementRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun virement  avec l'id "+id+" trouvé."));
		
		return virement;
	}

	
	public void modifierVirement(Long id,Virement virement) {
		
		Virement updated = virementRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun virement  avec l'id "+id+" trouvé."));
		
		if(compteRepository.findByNumeroCompte(virement.getCompte().getNumeroCompte()).isPresent())
			updated.setCompte(compteRepository.findByNumeroCompte(virement.getCompte().getNumeroCompte()).get());
		if(beneficiaireRepository.findByNumeroCompte(virement.getBeneficiaire().getNumeroCompte()).isPresent())
			updated.setBeneficiaire(beneficiaireRepository.findByNumeroCompte(virement.getBeneficiaire().getNumeroCompte()).get());
		if(virement.getDateExecution()!=null) updated.setDateExecution(virement.getDateExecution());
		if(virement.getMotif()!=null && !virement.getMotif().isEmpty()) updated.setMotif(virement.getMotif());
		if(virement.getMontant()!=null ) updated.setMontant(virement.getMontant());
		
		virementRepository.save(updated);
	}
	
	
	public void confirmerEtSigner(Long id)
	{
				
		Virement virement = virementRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun virement  avec l'id "+id+" trouvé."));
				virement.setStatut("Signé");
				virementRepository.save(virement);
		
	}
	
	

}
