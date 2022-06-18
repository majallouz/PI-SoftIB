package tn.esprit.softib.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.softib.enums.Nature;
import tn.esprit.softib.enums.TypeTransaction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nomComplet;
	private Nature natureCompte;
	private String rib;
	private String iban;
	private String codeBic;
	private float solde;
	@ManyToOne
	private User user;
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy="compte")
	private Set<Credit> credits;
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy="compte")
	private Set<Transaction> transactions;
	
	
}
