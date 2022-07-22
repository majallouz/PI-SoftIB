package tn.esprit.softib.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.softib.entity.Credit;
import tn.esprit.softib.entity.Operation;
import tn.esprit.softib.enums.CreditStatus;
import tn.esprit.softib.enums.TypeTransaction;
import tn.esprit.softib.utility.SystemDeclarations;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
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

}
