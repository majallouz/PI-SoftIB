package tn.esprit.softib.dto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.softib.entity.Compte;
import tn.esprit.softib.entity.Credit;
import tn.esprit.softib.entity.CreditRequest;
import tn.esprit.softib.entity.DemandeCnx;
import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.entity.Operation;
import tn.esprit.softib.entity.Question;
import tn.esprit.softib.entity.Role;
import tn.esprit.softib.entity.User;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Nature;
import tn.esprit.softib.enums.Type;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompteDTO {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull(message = "Name may not be null")
    private String nomComplet;
    private Nature natureCompte;
    private String rib;
    private String iban;
    private String codeBic;
    private BigDecimal solde;
    private String email;
    private boolean emailsent;
    private boolean status;
    @Lob
    private byte[] data;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="compte")
    private Set<Credit> credits;
     @OneToMany(mappedBy="compte")
        private Collection<Operation> operations;


}
