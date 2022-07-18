package tn.esprit.softib.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.softib.api.OperationAPI;
import tn.esprit.softib.entity.Operation;
import tn.esprit.softib.enums.OperationStatus;
import tn.esprit.softib.service.IOperationService;


@RestController
public class OperationController implements OperationAPI{
	  @Autowired
	    IOperationService operationService;
	 

	  @Override
    public ResponseEntity<Operation> updateCreditStatus(Integer idCredit, OperationStatus operationStatus) {
        return ResponseEntity.ok(operationService.updateOperationStatus(idCredit,operationStatus));
    }
	  @Override
    public ResponseEntity<Operation> save(@RequestBody Operation operation) {
        return ResponseEntity.ok(operationService.save(operation));
    }
   // @GetMapping(value = "/operations/findbyid/{id}")
    @Override
    public Operation getOperationById(Integer id) {
        return operationService.findOperationById(id);
    }

//    @GetMapping(value =  "/operations/")
//	@ResponseBody
    @Override
    public List<Operation> findAllByArchived(Boolean archived) {
        return operationService.getArchivedOperation(archived);
    }

   // @GetMapping(value = "/operations/{id}")
    @Override
    public List<Operation> findAllByUser(Integer id) {
        return operationService.getAllOperationByClient(id);
    }

   // @GetMapping(value = "/operations/{id}/{status}")
    @Override
    public List<Operation> getAllOperationByClientAndStatus(Integer id, OperationStatus operationStatus) {
        return operationService.getAllOperationByClientAndStatus(id,operationStatus);
    }




}
