package com.pfa.virement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.virement.entities.Compte;
import com.pfa.virement.entities.Virement;

@Repository
public interface VirementRepository extends JpaRepository<Virement, Long> {

	Optional<Virement> findById(Long id);
	
	List<Virement> findAllByCompte(Compte compte);
}
