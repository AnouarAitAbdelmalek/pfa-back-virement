package com.pfa.virement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfa.virement.entities.Abonne;
import com.pfa.virement.entities.Beneficiaire;
import com.pfa.virement.entities.Compte;
import com.pfa.virement.entities.Virement;
import com.pfa.virement.exceptions.ConflictException;
import com.pfa.virement.exceptions.NotFoundException;
import com.pfa.virement.repositories.AbonneRepository;
import com.pfa.virement.repositories.VirementRepository;

@Service
public class AbonneService {

	@Autowired
	AbonneRepository abonneRepository;
	
	@Autowired
	VirementRepository virementRepository;



	//Ajouter un abonné
	public void addAbonne(Abonne abonne)
	{
		if(abonneRepository.findByUsername(abonne.getUsername()).isPresent()) 
			throw new ConflictException("Un abonné avec le username "+abonne.getUsername()+" existe déjé.");
		
		abonne.setPassword(new BCryptPasswordEncoder().encode(abonne.getPassword()));
		
		abonneRepository.save(abonne);
	}
	
	
	
	
	public Abonne getAbonne(Long id)
	{
		Abonne abonne = abonneRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun abonné avec l'id "+id+" trouvé."));
		
		return abonne;
	}
	
	
	
	public List<Abonne> getAbonnes()
	{
		List<Abonne> abonnes = abonneRepository.findAll();
		if(abonnes.isEmpty()) throw new NotFoundException("Aucun abonné trouvé.");
		
		return abonnes;
	}
	
	
	
	
	
	
	//Liste des comptes d'un abonné
	public List<Compte> getComptes(Long id)
	{
		Abonne abonne = abonneRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun abonné avec l'id "+id+" trouvé."));
		if(abonne.getComptes().isEmpty()) throw new NotFoundException("Cet abonné n'a aucun compte.");
		
		return abonne.getComptes();
	}
	
	
	
	//Liste des bénéficiaires d'un abonné
		public List<Beneficiaire> getBeneficiaires(Long id)
		{
			Abonne abonne = abonneRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Aucun abonné avec l'id "+id+" trouvé."));
			if(abonne.getBeneficiaires().isEmpty()) throw new NotFoundException("Cet abonné n'a aucun bénéficiaire.");
			
			return abonne.getBeneficiaires();
		}
	
	
	
	
	
	//Trouver un abonné par son username
	public Abonne getByUsername(String username)
	{
		return abonneRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Aucun abonné avec le username "+username+" trouvé."));
	}
	
	
	
	
	
	
	//Supprimer un abonné
	public void deleteAbonne(Long id)
	{
		if(!abonneRepository.findById(id).isPresent()) throw new NotFoundException("Aucun abonné avec l'id "+id+" n'est trouvé.");
		abonneRepository.deleteById(id);
	}
	
	
	public List<Virement> virements(Long id)
	{
		List<Virement> virements = new ArrayList<Virement>();
		Abonne abonne = abonneRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Aucun compte avec l'id "+id+" trouvé"));
		List<Compte> comptes = abonne.getComptes();
		for (Compte compte : comptes) {
			virements.addAll(virementRepository.findAllByCompte(compte));
		}
		
		
		
		if(virements.isEmpty()) throw new NotFoundException("Aucun virement  effectué");
		
		return virements;		
		
	}
	
	
	
	
	
}
