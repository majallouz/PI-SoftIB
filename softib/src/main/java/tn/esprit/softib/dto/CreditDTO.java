package tn.esprit.softib.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.softib.entity.Compte;
import tn.esprit.softib.entity.CreditRequest;
import tn.esprit.softib.entity.DemandeCnx;
import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.entity.Payment;
import tn.esprit.softib.entity.Question;
import tn.esprit.softib.entity.Role;
import tn.esprit.softib.enums.CreditStatus;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Type;
import tn.esprit.softib.enums.TypeCredit;
import tn.esprit.softib.utility.SystemDeclarations;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditDTO {
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

}
