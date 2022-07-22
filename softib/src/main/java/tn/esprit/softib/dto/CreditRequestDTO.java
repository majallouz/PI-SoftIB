package tn.esprit.softib.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.softib.entity.Compte;
import tn.esprit.softib.entity.Credit;
import tn.esprit.softib.entity.CreditRequest;
import tn.esprit.softib.entity.Insurance;
import tn.esprit.softib.entity.User;
import tn.esprit.softib.enums.CreditStatus;
import tn.esprit.softib.enums.TypeCredit;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequestDTO {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
	@Column(name = "cin", nullable = false, length = 8)
    private Long cin;
	@Column(name = "age", nullable = false, length = 3)
	private Integer age;
    private String job;
    private String civilState;
    private Date creationDate;
    private String address;
    private CreditStatus creditRequestStatus;
    private Integer creditTerm;
    private Double creditAmount;
    private Boolean creditRepayment;
    private Double creditRepaymentAmount;
   
    private String rejectionReason;
	@ManyToOne
	private User user;
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    private TypeCredit type;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "creditRequest")
    @JsonIgnore
    private Credit credit;
    
    private Double netSalary;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Compte compte;

}
