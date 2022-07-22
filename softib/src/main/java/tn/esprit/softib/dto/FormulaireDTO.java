package tn.esprit.softib.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.softib.entity.Operation;
import tn.esprit.softib.entity.User;
import tn.esprit.softib.enums.FormStatus;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Nature;
import tn.esprit.softib.enums.Type;
import tn.esprit.softib.enums.TypeTransaction;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormulaireDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FormulaireSequence")
	@SequenceGenerator(name = "FormulaireSequence", allocationSize = 5)
	private long id;
	private String cin;
	private String firstName;
	private String lastName;
	private Long phone;
	private Gender gender;
	private String adresse;
	private String email;
	private Nature natureCompte;
	private float salaireNet;
	private String job;
	private Type type;
	private FormStatus formStatus;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

}
