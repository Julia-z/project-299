package lb.edu.aub.cmps.grad.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import lb.edu.aub.cmps.grad.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.grad.algorithm.Scheduler;
import lb.edu.aub.cmps.grad.classes.Department;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

/**
 * 
 * @author Yasmin Kadah
 * generates an excel file showing statistics info by department and overall percentage
 */
public class GenerateStatisticsInfo {
	HSSFWorkbook wb;
	HSSFSheet statistics;
	int rowsCount;
	CellStyle gray;
	CellStyle light_green;
	CellStyle green;
	CellStyle dark_green;
	private FileOutputStream output;
	private String excelFileName;

	// the map is from dep to a double indicating the percentage of classes that
	// were met
	// the stat is a single double indicating the overall stat
	public GenerateStatisticsInfo() {
		wb = new HSSFWorkbook();
		gray = wb.createCellStyle();
		gray.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		gray.setFillPattern(CellStyle.SOLID_FOREGROUND);
		green = wb.createCellStyle();
		green.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		green.setFillPattern(CellStyle.SOLID_FOREGROUND);
		dark_green = wb.createCellStyle();
		dark_green.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		dark_green.setFillPattern(CellStyle.SOLID_FOREGROUND);
		light_green = wb.createCellStyle();
		light_green.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		light_green.setFillPattern(CellStyle.SOLID_FOREGROUND);
		HSSFPalette palette = wb.getCustomPalette();
		palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 169, (byte) 229,
				(byte) 182);
		HSSFPalette palette2 = wb.getCustomPalette();
		palette2.setColorAtIndex(HSSFColor.GREEN.index, (byte) 228, (byte) 248,
				(byte) 233);
		HSSFPalette palette3 = wb.getCustomPalette();
		palette3.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index, (byte) 220,
				(byte) 220, (byte) 220);
		HSSFPalette palette4 = wb.getCustomPalette();
		palette4.setColorAtIndex(HSSFColor.LIGHT_CORNFLOWER_BLUE.index, (byte) 98,
				(byte) 138, (byte) 108);
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        dark_green.setFont(font);
		statistics = wb.createSheet("Statistics");
		statistics.setColumnWidth(0, 5000);
		statistics.setColumnWidth(1, 3000);
		excelFileName = "Outputs\\Statistics.xls";
		rowsCount = 1;

	}

	public void generate(Map<Department, Double> map, double stat) {
		Row firstRow = statistics.createRow(0);

		Cell depName = firstRow.createCell(0);
		depName.setCellValue("Department");
		depName.setCellStyle(green);
		Cell percent = firstRow.createCell(1);
		percent.setCellValue("Percentage");
		percent.setCellStyle(green);
		statistics.createFreezePane(0, 1);
		
		for (Department d : map.keySet()) {
			Row row1 = statistics.createRow(rowsCount);
			rowsCount++;
			Cell dept = row1.createCell(0);
			dept.setCellValue(d.getName());
			
			Cell statistic = row1.createCell(1);
			double rounded = Math.round(map.get(d) * 100.0) / 100.0;
			statistic.setCellValue(rounded * 100 + "%");
			if(rowsCount%2 == 0){
				dept.setCellStyle(light_green);
				statistic.setCellStyle(light_green);
			}
		}
		Row lastRow = statistics.createRow(rowsCount);
		Cell overall = lastRow.createCell(0);
		overall.setCellValue("OVERALL");
		overall.setCellStyle(dark_green);
		Cell number = lastRow.createCell(1);
		number.setCellValue((Math.round(stat * 100) / 100.0) * 100.0 + "%");
		number.setCellStyle(dark_green);
		try {
			output = new FileOutputStream(new File(excelFileName));
			wb.write(output);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
