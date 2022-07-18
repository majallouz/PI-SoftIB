package tn.esprit.softib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.softib.entity.Operation;


@Repository
public interface OperationRepository extends JpaRepository<Operation,Integer> {
}
