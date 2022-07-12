package tn.esprit.softib.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	@OneToOne
	private DemandeCnx demandeCnx;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Formulaire> formulaires;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<CreditRequest> creditRequests;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Compte> compte;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Question> questions;

	public User(String username, String email, String password) {
		this.username = username;
		String[] codes = username.split("-");
		this.cin = codes[0];
		this.firstName = codes[2];
		this.lastName = codes[1];
		this.email = email;
		this.password = password;
	}
}
