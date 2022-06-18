package tn.esprit.softib.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.TypeTransaction;
import tn.esprit.softib.enums.Type;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
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
	private Type type;
	private Boolean isSigned;
	private Date creationDate;
	private String job;
	private float salaireNet;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	@OneToOne
	private DemandeCnx demandeCnx;
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy="user")
	private Set<Formulaire> formulaires;
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy="user")
	private Set<CreditRequest> creditRequests;
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy="user")
	private Set<Compte> compte;
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy="user")
	private Set<Question> questions;

}
