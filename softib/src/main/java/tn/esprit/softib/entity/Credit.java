package tn.esprit.softib.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.softib.dto.CreditDTO;
import tn.esprit.softib.enums.CreditStatus;
import tn.esprit.softib.enums.TypeCredit;
import tn.esprit.softib.utility.SystemDeclarations;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit implements Serializable{
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    @Column(name = "id", nullable = false, updatable = false)
	    private Long id;
	    @DateTimeFormat(pattern = SystemDeclarations.DATE_FORMAT)
	    private LocalDate creationDate;
	    private CreditStatus creditStatus;
	    private Integer creditTerm;
	    private Double creditAmount;
	    private Boolean creditRepayment;
	    private Double creditRepaymentAmount;
	    private Double creditRepaymentInterest;
	    private Double creditInterest;
	    private Double creditFees;
	    @DateTimeFormat(pattern = SystemDeclarations.DATE_FORMAT)
	    private LocalDate releaseDate;
	    private String agent;
	    private TypeCredit type;
	    private Double payedAmount;
	    private Double remainingAmount;
	@OneToMany(mappedBy="credit")
    @OrderBy("paymentDueDate ASC ")
    private Set<Payment> payments;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creditrequest_id")
    private CreditRequest creditRequest;
	@ManyToOne
	private Compte compte;
	
	
	public Credit(CreditDTO dto) {
		super();
		this.id = dto.getId();
		this.creationDate = dto.getCreationDate();
		this.creditStatus = dto.getCreditStatus();
		this.creditTerm = dto.getCreditTerm();
		this.creditAmount = dto.getCreditAmount();
		this.creditRepayment = dto.getCreditRepayment();
		this.creditRepaymentAmount = dto.getCreditRepaymentAmount();
		this.creditRepaymentInterest = dto.getCreditRepaymentInterest();
		this.creditInterest = dto.getCreditInterest();
		this.creditFees = dto.getCreditFees();
		this.releaseDate = dto.getReleaseDate();
		this.agent = dto.getAgent();
		this.type = dto.getType();
		this.payedAmount = dto.getPayedAmount();
		this.remainingAmount = dto.getRemainingAmount();
		this.payments = dto.getPayments();
		this.creditRequest = dto.getCreditRequest();
		this.compte = dto.getCompte();
	}
	
	
	
	

}
