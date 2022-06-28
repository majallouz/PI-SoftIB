package tn.esprit.softib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.softib.entity.Formulaire;

@Repository
public interface FormulaireRepository extends JpaRepository<Formulaire, Long> {

	Optional<Formulaire> findByEmail(String email);
}
