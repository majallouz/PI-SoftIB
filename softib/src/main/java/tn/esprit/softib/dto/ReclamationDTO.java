package tn.esprit.softib.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.softib.entity.Operation;
import tn.esprit.softib.entity.Reclamation;
import tn.esprit.softib.enums.ReclamationStatus;
import tn.esprit.softib.enums.TypeTransaction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReclamationDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReclamationStatus status;

    @Column(name = "reclamation_title")
    private String reclamationTitle;

    @Column(name = "reclamation_description")
    private String reclamationDescription;

    @Column(name = "assignee_name")
    private String assigneeName;

    @Column(name = "user_id")
    private Long userId;

}
