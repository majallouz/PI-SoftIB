package tn.esprit.softib.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.softib.dto.PaymentDTO;
import tn.esprit.softib.enums.CreditStatus;
import tn.esprit.softib.utility.SystemDeclarations;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
	@DateTimeFormat(pattern = SystemDeclarations.DATE_FORMAT)
	private LocalDate creationDate;
	@DateTimeFormat(pattern = SystemDeclarations.DATE_FORMAT)
	private LocalDate paymentDate;
	@DateTimeFormat(pattern = SystemDeclarations.DATE_FORMAT)
	private LocalDate paymentDueDate;
    private Double paymentAmount;
    private Double paymentInterest;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    @JsonIgnore
    private Credit credit;
    private Double penality;
    private CreditStatus paymentStatus;
    
	public Payment(PaymentDTO dto) {
		super();
		this.id = dto.getId();
		this.creationDate = dto.getCreationDate();
		this.paymentDate = dto.getPaymentDate();
		this.paymentDueDate = dto.getPaymentDueDate();
		this.paymentAmount = dto.getPaymentAmount();
		this.paymentInterest = dto.getPaymentInterest();
		this.credit = dto.getCredit();
		this.penality = dto.getPenality();
		this.paymentStatus = dto.getPaymentStatus();
	}
    
    

}
