package com.pfa.virement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.virement.entities.Abonne;

@Repository
public interface AbonneRepository extends JpaRepository<Abonne, Long> {

	Optional<Abonne> findByUsername(String username);
	
	Optional<Abonne> findById(Long id);
}
