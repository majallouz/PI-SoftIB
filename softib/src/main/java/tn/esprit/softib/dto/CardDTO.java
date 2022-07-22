package tn.esprit.softib.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
import tn.esprit.softib.enums.CardType;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Type;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CardSequence")
	private long id;
	private String rib;
	private CardType cardType;

}
