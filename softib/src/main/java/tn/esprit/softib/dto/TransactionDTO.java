package tn.esprit.softib.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.softib.entity.Operation;
import tn.esprit.softib.entity.Transaction;
import tn.esprit.softib.enums.TypeTransaction;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
