package tn.esprit.softib.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.helper.ExcelHelper;
import tn.esprit.softib.repository.FormulaireRepository;


@Service
public class ExcelService {
  @Autowired
  FormulaireRepository repository;
  public void save(MultipartFile file) {
    try {
      List<Formulaire> formulaires = ExcelHelper.excelToFormulaires(file.getInputStream());
      repository.saveAll(formulaires);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }
  
}