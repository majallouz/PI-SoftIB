package tn.esprit.softib.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.softib.entity.Compte;
import tn.esprit.softib.entity.CreditRequest;
import tn.esprit.softib.entity.DemandeCnx;
import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.entity.Operation;
import tn.esprit.softib.entity.Question;
import tn.esprit.softib.entity.Role;
import tn.esprit.softib.entity.Transaction;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Type;
import tn.esprit.softib.enums.TypeTransaction;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	   private LocalDate date;
	    private TypeTransaction transactionType;
	    private Boolean isNegativeTx;
	    private Boolean isRevertedTransaction;
	    private BigDecimal movement;

	    @ManyToOne
	  @JoinColumn(name = "Operation_Id")
	    private Operation operation;

}
