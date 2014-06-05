package cz.uhk.ppro.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import cz.uhk.ppro.domain.Aktivita;

public class ExcelView extends AbstractExcelView {

	public void buildExcelDocument(Map model,HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		List<Aktivita> aktivity = (List<Aktivita>) model.get("aktivity");
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename=aktivity.xls"); 
		OutputStream outSteram = response.getOutputStream();
		
		HSSFSheet sheet = workbook.createSheet("Aktivity");
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Druh aktivity");
		header.createCell(1).setCellValue("Datum");
		header.createCell(2).setCellValue("Čas");
		header.createCell(3).setCellValue("Vzdálenost");
		header.createCell(4).setCellValue("Rychlost");
		header.createCell(5).setCellValue("Spálené kalorie");
		
		Format datumformat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Format casformat = new SimpleDateFormat("HH:mm:ss");
		
		int rowNum = 1;
		for (Aktivita aktivita : aktivity) {
			//create the row data
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(aktivita.getTyp());
			row.createCell(1).setCellValue(datumformat.format(aktivita.getDatum()));
			row.createCell(2).setCellValue(casformat.format(aktivita.getDobaTrvani()));
			row.createCell(3).setCellValue(aktivita.getVzdalenost());
			row.createCell(4).setCellValue(aktivita.getRychlost());
			row.createCell(5).setCellValue(aktivita.getKalorie());
                }
		workbook.write(outSteram);
		outSteram.close();
	}
}
