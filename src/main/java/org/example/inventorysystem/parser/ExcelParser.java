package org.example.inventorysystem.parser;

import org.example.inventorysystem.models.Asset;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ExcelParser implements Workboo {
	public static List<Asset> parseExcel(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		Workbook workbook = new XSSFWorkbook(fis);
	}
}
