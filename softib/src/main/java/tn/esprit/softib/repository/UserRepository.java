package tn.esprit.softib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.softib.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByCin(String cin);
	
	Optional<User> findByEmail(String email);

	Boolean existsByCin(String cin);

	Boolean existsByEmail(String email);
}
