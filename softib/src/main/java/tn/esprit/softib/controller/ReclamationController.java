package tn.esprit.softib.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.softib.entity.Compte;
import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.entity.Reclamation;
import tn.esprit.softib.entity.User;
import tn.esprit.softib.enums.ReclamationStatus;
import tn.esprit.softib.repository.UserRepository;
import tn.esprit.softib.service.IReclamationService;




@RestController
@RequestMapping("/reclamation")

public class ReclamationController {
	@Autowired
	IReclamationService reclamationService;
	
	
	@GetMapping("/findAll")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public List<Reclamation> findAll() {
		List<Reclamation> reclamation = (List<Reclamation>) reclamationService.getAllReclamations();
		return reclamation;
	}
	
	
	@GetMapping("/findById/{id}")
	@ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
	public Reclamation findById(@PathVariable("id") Long id) {
		Reclamation reclamation = reclamationService.getReclamationByID(id);
		return reclamation;
	}
	
	
	@PostMapping("/create")
	@ResponseBody
	public Reclamation save(@RequestBody Reclamation reclamation){		
		Reclamation reclamationResult = reclamationService.addReclamation(reclamation);

		return reclamationResult;
	}
	
	
	@PutMapping("/update/{id}")
	@ResponseBody
	public Reclamation update(@PathVariable("id") long id , @RequestBody Reclamation reclamation){
		return reclamationService.updateReclamation(id,reclamation);
		
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id){
		reclamationService.deleteReclamation(id);
	}
	
	
	
	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;
	
	@PostMapping("/uploadFile")
	public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file) throws IOException{
		File myFile = new File(FILE_DIRECTORY+file.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos =new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
		return new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);
	}
	

}
