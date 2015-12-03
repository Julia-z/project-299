package lb.edu.aub.cmps.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import lb.edu.aub.cmps.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.algorithm.Scheduler;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Day;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Section;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.TimeSlot;

public class ExportSectionsToExcel {

	HSSFWorkbook wb;
	HSSFSheet departments;
	int depCount;
	int rowsCount;
	CellStyle gray;
	CellStyle light_blue;
	CellStyle red;
	private FileOutputStream output;
	private String excelFileName;

	public ExportSectionsToExcel() {
		wb = new HSSFWorkbook();
		departments = wb.createSheet("Deparments");
		departments.setColumnWidth(0, 5000);
		departments.setColumnWidth(1, 3000);
		departments.setColumnWidth(2, 1000);
		departments.setColumnWidth(3, 2000);
		departments.setColumnWidth(4, 2000);
		departments.setColumnWidth(5, 700);
		departments.setColumnWidth(6, 700);
		departments.setColumnWidth(7, 700);
		departments.setColumnWidth(8, 700);
		departments.setColumnWidth(9, 700);
		departments.setColumnWidth(10, 700);
		departments.setColumnWidth(11, 3000);
		departments.setColumnWidth(12, 3000);
		excelFileName = "Outputs\\Test.xls";
		rowsCount = 1;
		Row firstRow = departments.createRow(0);
		Cell depName = firstRow.createCell(0);
		depName.setCellValue("Dept");
		Cell courseName = firstRow.createCell(1);
		courseName.setCellValue("Course");
		Cell section = firstRow.createCell(2);
		section.setCellValue("Sec");
		Cell start = firstRow.createCell(3);
		start.setCellValue("Start");
		Cell end = firstRow.createCell(4);
		end.setCellValue("End");
		Cell mon = firstRow.createCell(5);
		mon.setCellValue("M");
		Cell wed = firstRow.createCell(6);
		wed.setCellValue("T");
		Cell fri = firstRow.createCell(7);
		fri.setCellValue("W");
		Cell tues = firstRow.createCell(8);
		tues.setCellValue("R");
		Cell thurs = firstRow.createCell(9);
		thurs.setCellValue("F");
		Cell sat = firstRow.createCell(10);
		sat.setCellValue("S");
		Cell room = firstRow.createCell(11);
		room.setCellValue("Room");
		Cell prof = firstRow.createCell(12);
		prof.setCellValue("Prof");
		gray = wb.createCellStyle();
		gray.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		gray.setFillPattern(CellStyle.SOLID_FOREGROUND);
		light_blue = wb.createCellStyle();
		light_blue.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE
				.getIndex());
		light_blue.setFillPattern(CellStyle.SOLID_FOREGROUND);
		red = wb.createCellStyle();
		red.setFillForegroundColor(IndexedColors.RED.getIndex());
		red.setFillPattern(CellStyle.SOLID_FOREGROUND);

	}

	/**
	 * TODO YASMIN
	 * 
	 * @param dep_sections
	 */
	public void export(Map<Department, Set<Course>> map) {
		// here you can refer to course.getSections() that returns a set of
		// sections
		for (Department d : map.keySet()) {
			for (Course course : map.get(d)) {
				Row row1 = departments.createRow(rowsCount);
				rowsCount++;
				Cell dep = row1.createCell(0);
				dep.setCellValue(d.getName());
				Cell crs = row1.createCell(1);
				crs.setCellValue(course.getCourse_name());
				System.out.println("\t*" + course.getCourse_name());
				if (course.getSections() != null) {
					for (Section s : course.getSections()) {
						Cell section = row1.createCell(2);
						section.setCellValue(s.getSection_number());
						System.out.println("\t\tSection "
								+ s.getSection_number());
						for (Class c : s.getClasses()) {
	
							Cell startTime = row1.createCell(3);
							if (c.getGivenTime() != null && c.getGivenTime().getTimeSlots()!=null) {
								TimeSlot[] t = c.getGivenTime().getTimeSlots();
								Cell endTime = row1.createCell(4);
								if(c.getGivenTime() != c.getRequestedTime()){
									startTime.setCellStyle(red);
									endTime.setCellStyle(red);
								}
								System.out.println(c.getGivenTime());
								for (int i = 0; i < t.length; i++) {
									startTime.setCellValue(t[i].getStart().substring(0,2)+":"+t[i].getStart().substring(2,4));
									endTime.setCellValue(t[i].getEnd().substring(0,2)+":"+t[i].getEnd().substring(2,4));
									if (t[i].getDay() == Day.M) {
										Cell m = row1.createCell(5);
										
										m.setCellValue("M");
									} else if (t[i].getDay() == Day.T) {
										Cell m = row1.createCell(6);
										m.setCellValue("T");
									} else if (t[i].getDay() == Day.W) {
										Cell m = row1.createCell(7);
										m.setCellValue("W");
									} else if (t[i].getDay() == Day.R) {
										Cell m = row1.createCell(8);
										m.setCellValue("R");
									} else if (t[i].getDay() == Day.F) {
										Cell m = row1.createCell(9);
										m.setCellValue("F");
									} else if (t[i].getDay() == Day.S) {
										Cell m = row1.createCell(10);
										m.setCellValue("S");
									}
								}
							}
							if(c.getGivenRoom() != null){
							Cell room = row1.createCell(11);
							room.setCellValue(c.getGivenRoom().getNumber());
							if(!c.getGivenRoom().getNumber().equals( c.getRequestedRoom().getNumber())){
								
								room.setCellStyle(red);
							}
							System.out.println(c.getGivenRoom().getNumber());
							}
							if (c.getProfessor() != null) {
								Cell profCell = row1.createCell(12);
								profCell.setCellValue(c.getProfessor()
										.getName());
								;
							}
						}
					}
				}
			}

		}
		try {
			output = new FileOutputStream(new File(excelFileName));
			wb.write(output);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * thats how u call on it
	 * 
	 * @param args
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SecurityException,
			IOException {
		Scheduler s = new ByTimeScheduler();
		s.schedule();
		Map<Department, Set<Course>> map = s.getDepCoursesMap();
		ExportSectionsToExcel e = new ExportSectionsToExcel();
		e.export(map);

	}

}
