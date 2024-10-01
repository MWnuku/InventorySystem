package org.example.inventorysystem.parser;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.inventorysystem.models.Asset;
import org.example.inventorysystem.models.Type;
import org.example.inventorysystem.models.InventoryField;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {
	public static List<Asset> parseExcel(String filename) throws IOException {
		FileInputStream fis = new FileInputStream(filename);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		List<Asset> assets = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			Asset asset = new Asset();
			int cell = row.getFirstCellNum();
			String groupAndInvNr = formatter.formatCellValue(row.getCell(cell));
			String[] data = groupAndInvNr.replace(" ", "").split("/");
			int group = Integer.parseInt(data[0]);
			Integer invNr = Integer.parseInt(data[1]);
			InventoryField inventoryField = new InventoryField();


			asset.setType(Type.getGroup(group));
			asset.setInventoryNumber(invNr);
			asset.setName(row.getCell(++cell).getStringCellValue());
		}
		return assets;
	}
}
