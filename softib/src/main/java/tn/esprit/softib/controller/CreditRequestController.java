package tn.esprit.softib.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.softib.entity.Credit;
import tn.esprit.softib.entity.CreditRequest;
import tn.esprit.softib.service.ICreditRequestService;
import tn.esprit.softib.service.IPaymentService;

@RestController
@RequestMapping(value = "/api/creditrequests")
public class CreditRequestController {
	
	@Autowired
    ICreditRequestService creditRequestService;

    @Autowired
    IPaymentService paymentService;

    
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreditRequest> createCreditRequest(@RequestBody CreditRequest creditRequest, @PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(creditRequestService.addCreditRequest(creditRequest,id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deleteCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(creditRequestService.deleteCreditRequest(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateCreditRequest(@PathVariable(value = "id") Integer id, @RequestBody CreditRequest creditRequest) throws Exception {
        return new ResponseEntity<>(creditRequestService.updateCreditRequest(id, creditRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreditRequest> getCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(creditRequestService.getCreditRequest(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/create-credit", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Credit> acceptCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(creditRequestService.createCreditFromCreditRequest(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/reject", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CreditRequest> rejectCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(creditRequestService.rejectCreditRequest(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/client-accept", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CreditRequest> acceptCreditRequestChanges(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(creditRequestService.acceptCreditRequestChanges(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/treat", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CreditRequest> treatCreditRequest(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(creditRequestService.treatCreditRequest(id), HttpStatus.OK);
    }

    @GetMapping(value = "/client-accepted", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Set<CreditRequest>> getAllCreditRequestAcceptedFromClients() throws Exception {
        return new ResponseEntity<>(creditRequestService.getAllCreditRequestAcceptedFromClients(), HttpStatus.OK);
    }

}