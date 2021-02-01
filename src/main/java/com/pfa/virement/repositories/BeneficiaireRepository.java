package com.pfa.virement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.virement.entities.Beneficiaire;


@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {

	Optional<Beneficiaire> findByNumeroCompte(String numeroCompte);

	Optional<Beneficiaire> findById(Long id);

}
