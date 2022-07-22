package tn.esprit.softib.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.softib.entity.Compte;
import tn.esprit.softib.entity.CreditRequest;
import tn.esprit.softib.entity.DemandeCnx;
import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.entity.Question;
import tn.esprit.softib.entity.Role;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Type;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSequence")
	@SequenceGenerator(name = "UserSequence", allocationSize = 5)
	private long id;
	private String siren;
	private String cin;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Long phone;
	private Gender gender;
	private String adresse;
	private String email;
	private Type type;
	private Boolean isSigned;
	private Boolean isBanned;
	private String banRaison;
	private Date creationDate;
	private String job;
	private float salaireNet;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	private List<Formulaire> formulaires;
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	private List<Compte> comptes;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Question> questions;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<CreditRequest> creditRequests;
	@OneToOne
	private DemandeCnx demandeCnx;

}
