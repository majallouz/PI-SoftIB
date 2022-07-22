package tn.esprit.softib.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.TypeTransaction;
import tn.esprit.softib.enums.Type;

import tn.esprit.softib.dto.UserDTO;

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

	public User(String username, String email, String password) {
		this.username = username;
		String[] codes = username.split("-");
		this.cin = codes[0];
		this.firstName = codes[2];
		this.lastName = codes[1];
		this.email = email;
		this.password = password;
	}
	
	
	




	public User(UserDTO dto) {
		super();
		this.id = dto.getId();
		this.siren = dto.getSiren();
		this.cin = dto.getCin();
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.phone = dto.getPhone();
		this.gender = dto.getGender();
		this.adresse = dto.getAdresse();
		this.email = dto.getEmail();
		this.type = dto.getType();
		this.isSigned = dto.getIsSigned();
		this.isBanned = dto.getIsBanned();
		this.banRaison = dto.getBanRaison();
		this.creationDate = dto.getCreationDate();
		this.job = dto.getJob();
		this.salaireNet = dto.getSalaireNet();
		this.roles = dto.getRoles();
		this.formulaires = dto.getFormulaires();
		this.comptes = dto.getComptes();
		this.questions = dto.getQuestions();
		this.creditRequests = dto.getCreditRequests();
		this.demandeCnx = dto.getDemandeCnx();
	}
}
