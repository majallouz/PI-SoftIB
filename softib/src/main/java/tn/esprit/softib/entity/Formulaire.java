package tn.esprit.softib.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Nature;
import tn.esprit.softib.enums.Type;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formulaire implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String cin;
	private String firstName;
	private String lastName;
	private Long phone;
	private Gender gender;
	private String adresse;
	private String email;
	private Nature natureCompte;
	private float salaireNet;
	private String job;
	private Type type;

	@ManyToOne
	private User user;



}
