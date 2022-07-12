package tn.esprit.softib.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.softib.entity.Formulaire;
import tn.esprit.softib.enums.FormStatus;
import tn.esprit.softib.enums.Gender;
import tn.esprit.softib.enums.Nature;
import tn.esprit.softib.enums.Type;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Cin", "FirstName", "LastName", "Phone", "Gender", "Adresse", "Email", "NatureCompte",
			"SalaireNet", "Job", "Type" };
	static String SHEET = "formulaires";

	public static boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<Formulaire> excelToFormulaires(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			List<Formulaire> formulaires = new ArrayList<Formulaire>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
			//	 skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				Formulaire formulaire = new Formulaire();
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
					case 0:
						formulaire.setCin(String.valueOf((long)currentCell.getNumericCellValue()));
						break;
					case 1:
						formulaire.setFirstName(currentCell.getStringCellValue());
						break;
					case 2:
						formulaire.setLastName(currentCell.getStringCellValue());
						break;
					case 3:
						formulaire.setPhone((long)currentCell.getNumericCellValue());
						break;
					case 4:
						if ("HOMME".equals(currentCell.getStringCellValue().toUpperCase())) {
							formulaire.setGender(Gender.HOMME);
						} else if ("FEMME".equals(currentCell.getStringCellValue().toUpperCase())) {
							formulaire.setGender(Gender.FEMME);
						} else {
							formulaire.setGender(Gender.AUTRE);
						}
						break;
					case 5:
						formulaire.setAdresse(currentCell.getStringCellValue());
						break;
					case 6:
						formulaire.setEmail(currentCell.getStringCellValue());
						break;
					case 7:
						if ("COURANT".equals(currentCell.getStringCellValue().toUpperCase())) {
							formulaire.setNatureCompte(Nature.COURANT);
						} else {
							formulaire.setNatureCompte(Nature.EPARGNE);
						}
						break;
					case 8:
						formulaire.setSalaireNet((float)currentCell.getNumericCellValue());
						break;
					case 9:
						formulaire.setJob(currentCell.getStringCellValue());
						break;
					case 10: 
						if ("PHYSIQUE".equals(currentCell.getStringCellValue().toUpperCase())) {
							formulaire.setType(Type.PHYSIQUE);
						} else if("MORAL".equals(currentCell.getStringCellValue().toUpperCase())){
							formulaire.setType(Type.MORAL);
						}
						break;

					default:
						break;
					}
					cellIdx++;
				}
				formulaire.setFormStatus(FormStatus.PENDING);
				formulaires.add(formulaire);
			}
			workbook.close();
			return formulaires;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}