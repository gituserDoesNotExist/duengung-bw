package de.duengung.bw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SchlaginfoRepository {

	public List<Schlaginfo> getSchlaege() {

		XSSFWorkbook workbook = tryToLoadAnbauplan();

		XSSFSheet sheet = workbook.getSheetAt(0);

		Map<Integer, List<String>> data = new HashMap<>();
		int i = 0;
		for (Row row : sheet) {
			data.put(i, new ArrayList<String>());
			for (Cell cell : row) {
				addCellTypeToMap(data, i, cell);
			}
			i++;
		}
		List<Schlaginfo> schlaginfos = new ArrayList<>();
		for (int j = 1; j < 221; j++) {
			System.out.println("sdfs " + j);
			Schlaginfo schlaginfo = new Schlaginfo();
			List<String> datensatz = data.get(j);
			schlaginfo.schlagnummer = datensatz.get(0);
			schlaginfo.schlagname = datensatz.get(1);
			schlaginfo.germarkung = datensatz.get(2);
			schlaginfo.schlaggroesse = datensatz.get(3);
			schlaginfo.pGehaltsklasse = datensatz.get(4);
			schlaginfo.zwischenfrucht = datensatz.get(5);
			schlaginfo.hauptfruchtVorjahr = datensatz.get(6);
			schlaginfo.hauptfrucht = datensatz.get(7);
			schlaginfo.ackerzahl = Integer.valueOf(datensatz.get(8));
			schlaginfo.wasserschutzgebiet = datensatz.get(9) == "Normalgebiet";
			schlaginfo.nMin = Integer.valueOf(datensatz.get(10));
			schlaginfo.isGetreide = Boolean.valueOf(datensatz.get(11));
			schlaginfo.isFeldfutter = Boolean.valueOf(datensatz.get(12));
			schlaginfo.isGruenland = Boolean.valueOf(datensatz.get(13));
			schlaginfo.dungVorjahrGuelle = Integer.valueOf(datensatz.get(14));
			schlaginfo.dungVorjahrRindermist = Integer.valueOf(datensatz.get(15));
			schlaginfo.dungVorjahrPferdemist = Integer.valueOf(datensatz.get(16));
			schlaginfo.dungVorjahrGaerrest = Integer.valueOf(datensatz.get(17));
			schlaginfos.add(schlaginfo);
		}
		return schlaginfos;
	}

	private void addCellTypeToMap(Map<Integer, List<String>> data, int i, Cell cell) {
		DataFormatter dataFormatter = new DataFormatter();
		switch (cell.getCellType()) {
		case STRING:
			data.get(Integer.valueOf(i)).add(cell.getRichStringCellValue().getString());
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				data.get(i).add(cell.getDateCellValue() + "");
			} else {
				data.get(i).add(dataFormatter.formatCellValue(cell));
			}
			break;
		case BOOLEAN:
			data.get(i).add(cell.getBooleanCellValue() + "");
			break;
		default:
			data.get(Integer.valueOf(i)).add(" ");
		}
	}

	private XSSFWorkbook tryToLoadAnbauplan() {
		try {
			return new XSSFWorkbook(new FileInputStream(new File("./anbauplan.xlsx")));
		} catch (IOException e) {
			throw new RuntimeException("Fehler beim Laden des Anbauplans!");
		}
	}

}
