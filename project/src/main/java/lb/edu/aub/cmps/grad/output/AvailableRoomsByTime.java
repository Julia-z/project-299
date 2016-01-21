package lb.edu.aub.cmps.grad.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import lb.edu.aub.cmps.grad.algorithm.EnhancedScheduler;
import lb.edu.aub.cmps.grad.algorithm.Scheduler;
import lb.edu.aub.cmps.grad.classes.Day;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.TimeSlot;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

public class AvailableRoomsByTime {
	private static Collection<Room> rooms;
	private HashMap<TimeSlot, Set<Room>> availableRooms;

	@SuppressWarnings("static-access")
	public AvailableRoomsByTime(Collection<Room> rooms) {
		this.rooms = rooms;
	}

	private TimeSlot[] getAllTimeSlots() {

		TimeSlot[] all = new TimeSlot[55];
		// case of MWF classes
		Day[] mwf = { Day.M, Day.W, Day.F };

		int i = 0;
		for (Day d : mwf) {
			TimeSlot slot = new TimeSlot();
			slot.setDay(d);
			slot.setStart("700");
			slot.setEnd("750");
			all[i] = slot;
			i++;
			int count = 1;
			while (count <= 12) {
				slot = slot.nextTimeSlot();
				all[i] = slot;
				i++;
				count++;
			}
		}
		Day[] tr = { Day.T, Day.R };
		for (Day d : tr) {
			TimeSlot slot = new TimeSlot();
			slot.setDay(d);
			slot.setStart("800");
			slot.setEnd("930");
			all[i] = slot;
			i++;
			int count = 1;
			while (count <= 7) {
				slot = slot.nextTimeSlot();
				all[i] = slot;
				i++;
				count++;
			}
		}
		return all;
	}

	public void exportAvailableRoomsByTime() throws IOException {
		TimeSlot[] all = getAllTimeSlots();
		availableRooms = new HashMap<TimeSlot, Set<Room>>();

		for (TimeSlot t : all) {
			if (t != null) {
				Set<Room> availableRoomsOnTime = new HashSet<Room>();

				for (Room r : rooms) {
					TimeSlot[] arr = { t };
					if (r.is_available(arr)) {
						availableRoomsOnTime.add(r);
					}
				}
				availableRooms.put(t, availableRoomsOnTime);
			}
		}

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet rooms = wb.createSheet("Available Rooms By Time");

		CellStyle gray = wb.createCellStyle();
		gray.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		gray.setFillPattern(CellStyle.SOLID_FOREGROUND);


		CellStyle red_gray = wb.createCellStyle();
		red_gray.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		red_gray.setBorderTop(CellStyle.BORDER_MEDIUM);
		red_gray.setBorderBottom(CellStyle.BORDER_MEDIUM);
		red_gray.setBorderLeft(CellStyle.BORDER_MEDIUM);
		red_gray.setBorderRight(CellStyle.BORDER_MEDIUM);
		red_gray.setTopBorderColor(IndexedColors.BLACK.getIndex());
		red_gray.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		red_gray.setRightBorderColor(IndexedColors.BLACK.getIndex());
		red_gray.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		red_gray.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		CellStyle red_white = wb.createCellStyle();
		red_white.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		red_white.setBorderTop(CellStyle.BORDER_MEDIUM);
		red_white.setBorderBottom(CellStyle.BORDER_MEDIUM);
		red_white.setBorderLeft(CellStyle.BORDER_MEDIUM);
		red_white.setBorderRight(CellStyle.BORDER_MEDIUM);
		red_white.setTopBorderColor(IndexedColors.BLACK.getIndex());
		red_white.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		red_white.setRightBorderColor(IndexedColors.BLACK.getIndex());
		red_white.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		red_white.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		CellStyle light_green = wb.createCellStyle();
		light_green
				.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		light_green.setFillPattern(CellStyle.SOLID_FOREGROUND);

		CellStyle blue = wb.createCellStyle();
		blue.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		blue.setFillPattern(CellStyle.SOLID_FOREGROUND);

		rooms.setColumnWidth(0, 5000);
		for (int i = 1; i <= 115; i++) {
			rooms.setColumnWidth(i, 4000);
		}

		Row firstRow = rooms.createRow(0);
		Cell daySlot = firstRow.createCell(0);
		daySlot.setCellValue("Day");
		Cell timeSlot = firstRow.createCell(1);
		timeSlot.setCellValue("Time Slot");
		Cell availableSlot = firstRow.createCell(2);
		availableSlot.setCellValue("Rooms Available");

		for (int j = 0; j < firstRow.getLastCellNum(); j++) {
			firstRow.getCell(j).setCellStyle(blue);
		}

		int i = 2;
		for (TimeSlot t : all) {
			if (t != null) {
				// Create Day cell
				if (t.getStart().equals("0700")) {
					Row dayRow = rooms.createRow(i);
					Cell dayCell = dayRow.createCell(0);

					if (t.getDay() == Day.M)
						dayCell.setCellValue("Monday");
					else if (t.getDay() == Day.W)
						dayCell.setCellValue("Wednesday");
					else if (t.getDay() == Day.F)
						dayCell.setCellValue("Friday");

					dayCell.setCellStyle(light_green);
					i++;
				}

				else if (t.getStart().equals("0800")
						&& (t.getDay() == Day.T || t.getDay() == Day.R)) {
					Row dayRow = rooms.createRow(i);
					Cell dayCell = dayRow.createCell(0);

					if (t.getDay() == Day.T)
						dayCell.setCellValue("Tuesday");
					else if (t.getDay() == Day.R)
						dayCell.setCellValue("Thursday");
					dayCell.setCellStyle(light_green);
					i++;
				}
				// Creating time slot cell
				Row r = rooms.createRow(i);

				Cell ts = r.createCell(1);
				ts.setCellValue(t.toString().substring(3));

				Cell av = r.createCell(2);
				av.setCellValue(availableRooms.get(t).size() + " rooms");
				i++;

				// If any room is available on time we create a cell for it
				if (availableRooms.keySet().contains(t)) {

					int j = 3;

					String[] roomStrs = new String[availableRooms.get(t).size()];
					int k = 0;
					for (Room roomOnTime : availableRooms.get(t)) {
						roomStrs[k] = roomOnTime.getNumber() + " ("
								+ roomOnTime.getRoom_capacity() + ")";
						k++;
					}

					Arrays.sort(roomStrs);
					
					for (int l = 0; l < k; l++) {
						Cell roomCell = r.createCell(j);
						roomCell.setCellValue(roomStrs[l]);
						j++;
					}
				}

				if (i % 2 == 0) {
					for (int j = 1; j < r.getLastCellNum(); j++) {
						if(j!=2)
							r.getCell(j).setCellStyle(gray);
						else
							r.getCell(j).setCellStyle(red_gray);
					}
				}
				else{
					r.getCell(2).setCellStyle(red_white);
				}
			}
		}

		String excelFileName = "Outputs\\AvailableRoomsByTime.xls";
		FileOutputStream output = new FileOutputStream(new File(excelFileName));
		wb.write(output);
		output.close();
	}

	public static void main(String[] args) throws SecurityException,
			IOException {
		System.out.println("starting...");
		Scheduler s = new EnhancedScheduler();

		System.out.println("scheduler created...");
		s.schedule();

		System.out.println("all classes scheduled...");

		AvailableRoomsByTime available_rooms = new AvailableRoomsByTime(
				s.getRooms());
		// available_rooms.generate();
		available_rooms.exportAvailableRoomsByTime();
	}
}
