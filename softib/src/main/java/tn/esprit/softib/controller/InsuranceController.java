package tn.esprit.softib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.softib.entity.Insurance;
import tn.esprit.softib.service.IInsuranceService;

@RestController
@RequestMapping(value = "/api/insurances")
public class InsuranceController {
	@Autowired
    IInsuranceService insuranceService;

    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createInsurance(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(insuranceService.addInsurance(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deleteInsurance(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(insuranceService.deleteInsurance(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateInsurance(@PathVariable(value = "id") Integer id, @RequestBody Insurance insurance) throws Exception {
        return new ResponseEntity<>(insuranceService.updateInsurance(id, insurance), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Insurance> getInsurance(@PathVariable(value = "id") Integer id) throws Exception {
        return new ResponseEntity<>(insuranceService.getInsurance(id), HttpStatus.OK);
    }

}
