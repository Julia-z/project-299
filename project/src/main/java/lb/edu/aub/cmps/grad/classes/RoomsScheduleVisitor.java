package lb.edu.aub.cmps.grad.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

public class RoomsScheduleVisitor implements RoomVisitor {

	private TimeSlot[] mwf;
	private TimeSlot[] tr;
	HSSFWorkbook wb;
	HSSFSheet rooms;
	int depCount;
	int rowsCount;
	int notCount;
	CellStyle gray;
	CellStyle light_green;
	CellStyle blue;
	HSSFCellStyle red_gray;
	private FileOutputStream output;
	private String excelFileName;
	private int counter = 1;

	/**
	 * 
	 * @throws IOException
	 */
	public RoomsScheduleVisitor(TimeSlot[] mwf, TimeSlot[] tr)
			throws IOException {
		this.mwf = Arrays.copyOf(mwf, mwf.length);
		this.tr = Arrays.copyOf(tr, tr.length);

		wb = new HSSFWorkbook();
		gray = wb.createCellStyle();
		gray.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		gray.setFillPattern(CellStyle.SOLID_FOREGROUND);
		blue = wb.createCellStyle();
		blue.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		blue.setFillPattern(CellStyle.SOLID_FOREGROUND);
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
		red_gray = wb.createCellStyle();
		red_gray.setFillForegroundColor(IndexedColors.GREY_25_PERCENT
				.getIndex());
		red_gray.setBorderTop(CellStyle.BORDER_MEDIUM);
		red_gray.setBorderBottom(CellStyle.BORDER_MEDIUM);
		red_gray.setBorderLeft(CellStyle.BORDER_MEDIUM);
		red_gray.setBorderRight(CellStyle.BORDER_MEDIUM);
		red_gray.setTopBorderColor(IndexedColors.BLUE_GREY.getIndex());
		red_gray.setBottomBorderColor(IndexedColors.BLUE_GREY.getIndex());
		red_gray.setRightBorderColor(IndexedColors.BLUE_GREY.getIndex());
		red_gray.setLeftBorderColor(IndexedColors.BLUE_GREY.getIndex());
		red_gray.setFillPattern(CellStyle.SOLID_FOREGROUND);

		rooms = wb.createSheet("Rooms");
		excelFileName = "Outputs\\Rooms_fixed_slots.xls";

		rooms.setColumnWidth(0, 5000);
		for (int i = 1; i <= 6; i++) {
			rooms.setColumnWidth(i, 4000);
		}

		Row firstRow = rooms.createRow(0);
		Cell nameSlot = firstRow.createCell(0);
		nameSlot.setCellValue("Room name");
		Cell timeSlot = firstRow.createCell(1);
		timeSlot.setCellValue("MWF-Time Slot");
		Cell monSlot = firstRow.createCell(2);
		monSlot.setCellValue("Monday");
		Cell wedSlot = firstRow.createCell(3);
		wedSlot.setCellValue("Wednesday");
		Cell friSlot = firstRow.createCell(4);
		friSlot.setCellValue("Friday");
		Cell timeSlot2 = firstRow.createCell(5);
		timeSlot2.setCellValue("TR-Time Slot");
		Cell tueSlot = firstRow.createCell(6);
		tueSlot.setCellValue("Tuesday");
		Cell thurSlot = firstRow.createCell(7);
		thurSlot.setCellValue("Thursday");

		rooms.createFreezePane(0, 1);
		for (int j = 0; j < firstRow.getLastCellNum(); j++) {
			firstRow.getCell(j).setCellStyle(blue);
		}
	}

	public void visit(Room r) throws IOException {

		Row roomRow = rooms.createRow(counter);
		Cell roomSlot = roomRow.createCell(0);
		roomSlot.setCellValue(r.getNumber());
		roomSlot.setCellStyle(red_gray);
		counter++;

		TimeSlot time = new TimeSlot();
		time.setDay(Day.M);
		time.setStart("700");
		time.setEnd("750");
		for (int i = 0; i < 13; i++) {
			Row timeRow = rooms.createRow(counter);
			Cell timeSlot = timeRow.createCell(1);
			timeSlot.setCellValue(time.toString().substring(3));
			timeSlot.setCellStyle(blue);
			time = time.nextTimeSlot();
			counter++;
		}
		counter -= 13;

		int local = 0;
		for (TimeSlot t : mwf) {

			Class c = r.getClassDuringTimeSlot(t);
			if (c != null) {
				String course = c.getCourse_name();
				int index = 0;
				if (t.getDay() == Day.M)
					index = 2;
				else if (t.getDay() == Day.W)
					index = 3;
				else
					index = 4;
				Row row = rooms.getRow(counter);
				Cell courseSlot = row.createCell(index);
				courseSlot.setCellStyle(gray);
				courseSlot.setCellValue(course);
			} else {
				int index = 0;
				if (t.getDay() == Day.M)
					index = 2;
				else if (t.getDay() == Day.W)
					index = 3;
				else
					index = 4;
				Row row = rooms.getRow(counter);
				Cell courseSlot = row.createCell(index);
				courseSlot.setCellStyle(light_green);
			}
			counter++;
			local++;
			if (local == 13) {
				local = 0;
				counter -= 13;
			}
		}
		// counter += 13;

		TimeSlot time2 = new TimeSlot();
		time2.setDay(Day.T);
		time2.setStart("800");
		time2.setEnd("915");
		for (int i = 0; i < 7; i++) {
			Row timeRow = rooms.getRow(counter);
			Cell timeSlot = timeRow.createCell(5);
			timeSlot.setCellValue(time2.toString().substring(3));
			timeSlot.setCellStyle(blue);
			time2 = time2.nextTimeSlot();
			counter++;
		}
		counter -= 7;

		for (TimeSlot t : tr) {

			Class c = r.getClassDuringTimeSlot(t);
			if (c != null) {
				String course = c.getCourse_name();
				int index = 0;
				if (t.getDay() == Day.T)
					index = 6;
				else
					index = 7;
				Row row = rooms.getRow(counter);
				Cell courseSlot = row.createCell(index);
				courseSlot.setCellStyle(gray);
				courseSlot.setCellValue(course);
			} else {
				int index = 0;
				if (t.getDay() == Day.T)
					index = 6;
				else
					index = 7;
				Row row = rooms.getRow(counter);
				Cell courseSlot = row.createCell(index);
				courseSlot.setCellStyle(light_green);
			}
			counter++;
			local++;
			if (local == 7) {
				local = 0;
				counter -= 7;
			}
		}
		counter += 13;

		output = new FileOutputStream(new File(excelFileName));
		wb.write(output);
		output.close();
	}

}
