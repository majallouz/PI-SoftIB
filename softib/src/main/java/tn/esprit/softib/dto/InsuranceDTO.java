package tn.esprit.softib.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.softib.entity.CreditRequest;
import tn.esprit.softib.entity.Operation;
import tn.esprit.softib.enums.TypeCredit;
import tn.esprit.softib.enums.TypeTransaction;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDTO {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private Date creationDate;
    private Date expiryDate;
    private TypeCredit type;
    private Double amount;
    private String beneficiary;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "insurance")
    @JsonIgnore
    private CreditRequest creditRequest;

}
