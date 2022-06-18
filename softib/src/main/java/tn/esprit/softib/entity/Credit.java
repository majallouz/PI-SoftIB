package tn.esprit.softib.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.softib.enums.CreditStatus;
import tn.esprit.softib.enums.TypeCredit;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private TypeCredit creditType ;
	private Date creationDate;
	private int creditTerm;
	private CreditStatus statusCredit;
	private float creditAmount;
	private float creditRepaymentAmount;
	private float creditInterest;
	private float payedAmount;
	private float remainingAmount;
	private Date releaseDate;
	@ManyToOne
	private Compte compte;
	
	

}
