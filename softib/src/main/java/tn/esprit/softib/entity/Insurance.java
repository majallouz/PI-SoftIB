package tn.esprit.softib.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.softib.dto.InsuranceDTO;
import tn.esprit.softib.enums.TypeCredit;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {
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
	public Insurance(InsuranceDTO dto) {
		super();
		this.id = dto.getId();
		this.creationDate = dto.getCreationDate();
		this.expiryDate = dto.getExpiryDate();
		this.type = dto.getType();
		this.amount = dto.getAmount();
		this.beneficiary = dto.getBeneficiary();
		this.creditRequest = dto.getCreditRequest();
	}
    
    

}
