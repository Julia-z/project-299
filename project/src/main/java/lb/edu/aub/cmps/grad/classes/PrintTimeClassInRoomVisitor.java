package lb.edu.aub.cmps.grad.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

/**
 * Prints the output to an excel file
 * 
 * @author Julia El Zini
 * @author Yasmin Kadah
 */
public class PrintTimeClassInRoomVisitor implements RoomVisitor {

	HSSFWorkbook wb;
	HSSFSheet Rooms;
	int rowsCount;
	CellStyle gray;
	CellStyle dark_green;
	CellStyle light_green;
	CellStyle red;
	CellStyle blue;
	/**
	 * Yasmin's fields
	 */
	private FileOutputStream output;
	private String excelFileName;

	public PrintTimeClassInRoomVisitor() {
		wb = new HSSFWorkbook();
		Rooms = wb.createSheet("Rooms");
		excelFileName = "Outputs\\Rooms.xls";
		rowsCount = 1;
		Rooms.setColumnWidth(0, 4000);
		Rooms.setColumnWidth(1, 3000);
		Rooms.setColumnWidth(2, 3000);
		Rooms.setColumnWidth(3, 3000);
		Rooms.setColumnWidth(4, 3000);
		Rooms.setColumnWidth(5, 3000);
		gray = wb.createCellStyle();
		gray
				.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		gray.setFillPattern(CellStyle.SOLID_FOREGROUND);
		light_green = wb.createCellStyle();
		light_green.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		light_green.setFillPattern(CellStyle.SOLID_FOREGROUND);
		dark_green = wb.createCellStyle();
		dark_green.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		dark_green.setFillPattern(CellStyle.SOLID_FOREGROUND);
		blue = wb.createCellStyle();
		blue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		blue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		HSSFPalette palette = wb.getCustomPalette();
		palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 224, (byte) 248,
				(byte) 255);
		HSSFPalette palette2 = wb.getCustomPalette();
		palette2.setColorAtIndex(HSSFColor.GREEN.index, (byte) 228, (byte) 248,
				(byte) 233);
		HSSFPalette palette3 = wb.getCustomPalette();
		palette3.setColorAtIndex(HSSFColor.LIGHT_CORNFLOWER_BLUE.index, (byte) 169, (byte) 229,
				(byte) 182);
		red = wb.createCellStyle();
		red.setFillForegroundColor(IndexedColors.RED
				.getIndex());
		red.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Row firstRow = Rooms.createRow(0);
		Cell roomName = firstRow.createCell(0);
		roomName.setCellValue("Room Name");
		Cell mon = firstRow.createCell(1);
		mon.setCellValue("Monday");
		Cell wed = firstRow.createCell(2);
		wed.setCellValue("Wednesday");
		Cell fri = firstRow.createCell(3);
		fri.setCellValue("Friday");
		Cell tues = firstRow.createCell(4);
		tues.setCellValue("Tuesday");
		Cell thurs = firstRow.createCell(5);
		thurs.setCellValue("Thursday");
		Cell sat = firstRow.createCell(6);
		sat.setCellValue("Saturday");
		mon.setCellStyle(dark_green);
		wed.setCellStyle(dark_green);
		fri.setCellStyle(dark_green);
		tues.setCellStyle(dark_green);
		thurs.setCellStyle(dark_green);
		sat.setCellStyle(dark_green);
	}

	public void visit(Room r) {

		Map<String, Integer> timeSlotWithoutDay = new HashMap<String, Integer>();
		Row row = Rooms.createRow(rowsCount);
		rowsCount++;
		Cell name = row.createCell(0);
		name.setCellValue(r.getNumber());
		name.setCellStyle(light_green);
		Row row1;
		String Previous = "";
		for (TimeSlot t : r.getReserved().keySet()) {
			String timeSlot = t.toString().substring(3,17);
			if(!timeSlotWithoutDay.containsKey(timeSlot)){
				row1 = Rooms.createRow(rowsCount);
				timeSlotWithoutDay.put(t.toString().substring(3, 17), rowsCount);
				rowsCount++;
			}
			else{
				row1 = Rooms.getRow(timeSlotWithoutDay.get(t.toString().substring(3, 17)));
			}
			if (!t.toString().substring(3, 17).equals(Previous)) {
				Cell firstCell = row1.createCell(0);
				firstCell.setCellValue(t.toString().substring(3, 17));
				
				Previous = t.toString().substring(3, 17);
			}

			if (t.getDay() == Day.M) {
				Cell course = row1.createCell(1);
				course.setCellValue(r.getReserved().get(t).getCourse_name());
				course.setCellStyle(blue);
			} else if (t.getDay() == Day.W) {
				Cell course = row1.createCell(2);
				course.setCellStyle(blue);
				course.setCellValue(r.getReserved().get(t).getCourse_name());
			} else if (t.getDay() == Day.F) {
				Cell course = row1.createCell(3);
				course.setCellStyle(blue);
				course.setCellValue(r.getReserved().get(t).getCourse_name());
			} else if (t.getDay() == Day.T) {
				Cell course = row1.createCell(4);
				course.setCellStyle(blue);
				course.setCellValue(r.getReserved().get(t).getCourse_name());
			} else if (t.getDay() == Day.R) {
				Cell course = row1.createCell(5);
				course.setCellStyle(blue);
				course.setCellValue(r.getReserved().get(t).getCourse_name());
			}else if (t.getDay() == Day.S) {
				Cell course = row1.createCell(6);
				course.setCellStyle(blue);
				course.setCellValue(r.getReserved().get(t).getCourse_name());
			}
			
			
		}

		try {
			output = new FileOutputStream(new File(excelFileName));
			wb.write(output);
			output.close();
		} catch (IOException e) {
		}

	}
}
