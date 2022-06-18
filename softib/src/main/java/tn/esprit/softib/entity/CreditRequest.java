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
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Nature;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequest implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private CreditStatus status;
	private Boolean isApproved;
	private float creditAmount;
	private Date creationDate;
	private Date releaseDate;
	@ManyToOne
	private User user;
	

}
