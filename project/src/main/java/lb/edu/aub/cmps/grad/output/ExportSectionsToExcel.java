package lb.edu.aub.cmps.grad.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.classes.Day;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.Professor;
import lb.edu.aub.cmps.grad.classes.Section;
import lb.edu.aub.cmps.grad.classes.TimeSlot;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

/**
 * Exports the results received from the scheduler to an excel file.
 * representing every department with the classes that were scheduled if a class
 * was changed(room/time) it will be gighlighted with red the second sheet in
 * the workbook represents the not scheduled classes and the reason
 * 
 * @author Yasmin Kadah
 */
@SuppressWarnings("deprecation")
public class ExportSectionsToExcel {

	HSSFWorkbook wb;
	HSSFSheet departments;
	HSSFSheet notScheduled;
	int depCount;
	int rowsCount;
	int notCount;
	CellStyle gray;
	CellStyle light_green;
	CellStyle blue;
	CellStyle red;
	private FileOutputStream output;
	private String excelFileName;

	public ExportSectionsToExcel() {
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
		red = wb.createCellStyle();
		red.setFillForegroundColor(IndexedColors.RED.getIndex());
		red.setFillPattern(CellStyle.SOLID_FOREGROUND);
		departments = wb.createSheet("Deparments");
		departments.setColumnWidth(0, 5000);
		departments.setColumnWidth(1, 3000);
		departments.setColumnWidth(2, 1000);
		departments.setColumnWidth(3, 1300);
		departments.setColumnWidth(4, 1300);
		departments.setColumnWidth(5, 1300);
		departments.setColumnWidth(6, 1300);
		departments.setColumnWidth(7, 1300);
		departments.setColumnWidth(8, 1300);
		departments.setColumnWidth(9, 1300);
		departments.setColumnWidth(10, 1300);
		departments.setColumnWidth(11, 1300);
		departments.setColumnWidth(12, 1300);
		departments.setColumnWidth(13, 1300);
		departments.setColumnWidth(14, 1300);
		departments.setColumnWidth(15, 3000);
		departments.setColumnWidth(16, 10000);
		departments.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));
		departments.addMergedRegion(new CellRangeAddress(0, 0, 5, 6));
		departments.addMergedRegion(new CellRangeAddress(0, 0, 7, 8));
		departments.addMergedRegion(new CellRangeAddress(0, 0, 9, 10));
		departments.addMergedRegion(new CellRangeAddress(0, 0, 11, 12));
		departments.addMergedRegion(new CellRangeAddress(0, 0, 13, 14));
		excelFileName = "Outputs\\Departments-Courses.xls";
		rowsCount = 2;
		Row firstRow = departments.createRow(0);

		Cell depName = firstRow.createCell(0);
		depName.setCellValue("Dept");
		Cell courseName = firstRow.createCell(1);
		courseName.setCellValue("Course");
		Cell section = firstRow.createCell(2);
		section.setCellValue("Sec");
		Cell mon = firstRow.createCell(3);
		mon.setCellValue("Monday");
		firstRow.createCell(4);
		firstRow.createCell(6);
		firstRow.createCell(8);
		firstRow.createCell(10);
		firstRow.createCell(12);
		firstRow.createCell(14);
		Cell tues = firstRow.createCell(5);
		tues.setCellValue("Tuesday");
		Cell wed = firstRow.createCell(7);
		wed.setCellValue("Wednesday");
		Cell thurs = firstRow.createCell(9);
		thurs.setCellValue("Thursday");
		Cell fri = firstRow.createCell(11);
		fri.setCellValue("Friday");
		fri.setCellStyle(blue);
		Cell sat = firstRow.createCell(13);
		sat.setCellValue("Saturday");
		Cell room = firstRow.createCell(15);
		room.setCellValue("Room");
		Cell prof = firstRow.createCell(16);
		prof.setCellValue("Prof");

		Row secondRow = departments.createRow(1);
		secondRow.createCell(0);
		secondRow.createCell(1);
		secondRow.createCell(2);
		secondRow.createCell(15);
		secondRow.createCell(16);
		Cell mS = secondRow.createCell(3);
		mS.setCellValue("Start");
		Cell mE = secondRow.createCell(4);
		mE.setCellValue("End");
		Cell tS = secondRow.createCell(5);
		tS.setCellValue("Start");
		Cell tE = secondRow.createCell(6);
		tE.setCellValue("End");
		Cell wS = secondRow.createCell(7);
		wS.setCellValue("Start");
		Cell wE = secondRow.createCell(8);
		wE.setCellValue("End");
		Cell rS = secondRow.createCell(9);
		rS.setCellValue("Start");
		Cell rE = secondRow.createCell(10);
		rE.setCellValue("End");
		Cell fS = secondRow.createCell(11);
		fS.setCellValue("Start");
		Cell fE = secondRow.createCell(12);
		fE.setCellValue("End");
		Cell sS = secondRow.createCell(13);
		sS.setCellValue("Start");
		Cell sE = secondRow.createCell(14);
		sE.setCellValue("End");
		for (int i = 0; i < firstRow.getLastCellNum(); i++) {
			firstRow.getCell(i).setCellStyle(blue);
			secondRow.getCell(i).setCellStyle(light_green);
		}
		notScheduled = wb.createSheet("Failed to Schedule");
		departments.createFreezePane(0, 2);

	}

	/**
	 * @param map
	 *            the map from dep to a set of given courses
	 */
	public void export(Map<Department, Set<Course>> map) {
		// here you can refer to course.getSections() that returns a set of
		// sections
		int sectionsCount = 0;
		for (Department d : map.keySet()) {
			for (Course course : map.get(d)) {

				if (course.getSections() != null) {
					for (Section s : course.getSections()) {

						Row row1 = departments.createRow(rowsCount);
						rowsCount++;

						Cell dep = row1.createCell(0);
						dep.setCellValue(d.getName());
						Cell crs = row1.createCell(1);
						crs.setCellValue(course.getCourse_name());
						Cell section = row1.createCell(2);
						section.setCellValue(s.getSection_number());
						Cell mstartTime = row1.createCell(3);
						Cell mendTime = row1.createCell(4);
						Cell tstartTime = row1.createCell(5);
						Cell tendTime = row1.createCell(6);
						Cell wstartTime = row1.createCell(7);
						Cell wendTime = row1.createCell(8);
						Cell rstartTime = row1.createCell(9);
						Cell rendTime = row1.createCell(10);
						Cell fstartTime = row1.createCell(11);
						Cell fendTime = row1.createCell(12);
						Cell sstartTime = row1.createCell(13);
						Cell sendTime = row1.createCell(14);
						Cell room = row1.createCell(15);
						/** TODO for all professors **/
						Cell profCell = row1.createCell(16);

						int classCount = 0;
						for (Class c : s.getClasses()) {

							if (classCount > 0) {
								row1 = departments.createRow(rowsCount);
								rowsCount++;
								dep = row1.createCell(0);
								dep.setCellValue(d.getName());
								crs = row1.createCell(1);
								crs.setCellValue(course.getCourse_name());
								section = row1.createCell(2);
								section.setCellValue(s.getSection_number());
								mstartTime = row1.createCell(3);
								mendTime = row1.createCell(4);
								tstartTime = row1.createCell(5);
								tendTime = row1.createCell(6);
								wstartTime = row1.createCell(7);
								wendTime = row1.createCell(8);
								rstartTime = row1.createCell(9);
								rendTime = row1.createCell(10);
								fstartTime = row1.createCell(11);
								fendTime = row1.createCell(12);
								sstartTime = row1.createCell(13);
								sendTime = row1.createCell(14);
								room = row1.createCell(15);
								profCell = row1.createCell(16);
							}
							for (int i = 0; i < row1.getLastCellNum(); i++) {
								if (sectionsCount % 2 == 0) {
									row1.getCell(i).setCellStyle(gray);
								}
							}
							boolean ismet = c.getIsMet();

							if (c.getGivenTime() != null
									&& c.getGivenTime().getTimeSlots() != null) {
								TimeSlot[] t = c.getGivenTime().getTimeSlots();

								if (c.getGivenTime() != c.getRequestedTime()) {

								}

								for (int i = 0; i < t.length; i++) {

									if (t[i].getDay() == Day.M) {

										mstartTime.setCellValue(t[i].getStart()
												.substring(0, 2)
												+ ":"
												+ t[i].getStart().substring(2,
														4));
										mendTime.setCellValue(t[i].getEnd()
												.substring(0, 2)
												+ ":"
												+ t[i].getEnd().substring(2, 4));
									} else if (t[i].getDay() == Day.T) {

										tstartTime.setCellValue(t[i].getStart()
												.substring(0, 2)
												+ ":"
												+ t[i].getStart().substring(2,
														4));
										tendTime.setCellValue(t[i].getEnd()
												.substring(0, 2)
												+ ":"
												+ t[i].getEnd().substring(2, 4));
									} else if (t[i].getDay() == Day.W) {

										wstartTime.setCellValue(t[i].getStart()
												.substring(0, 2)
												+ ":"
												+ t[i].getStart().substring(2,
														4));
										wendTime.setCellValue(t[i].getEnd()
												.substring(0, 2)
												+ ":"
												+ t[i].getEnd().substring(2, 4));
									} else if (t[i].getDay() == Day.R) {

										rstartTime.setCellValue(t[i].getStart()
												.substring(0, 2)
												+ ":"
												+ t[i].getStart().substring(2,
														4));
										rendTime.setCellValue(t[i].getEnd()
												.substring(0, 2)
												+ ":"
												+ t[i].getEnd().substring(2, 4));
									} else if (t[i].getDay() == Day.F) {

										fstartTime.setCellValue(t[i].getStart()
												.substring(0, 2)
												+ ":"
												+ t[i].getStart().substring(2,
														4));
										fendTime.setCellValue(t[i].getEnd()
												.substring(0, 2)
												+ ":"
												+ t[i].getEnd().substring(2, 4));
									} else if (t[i].getDay() == Day.S) {

										sstartTime.setCellValue(t[i].getStart()
												.substring(0, 2)
												+ ":"
												+ t[i].getStart().substring(2,
														4));
										sendTime.setCellValue(t[i].getEnd()
												.substring(0, 2)
												+ ":"
												+ t[i].getEnd().substring(2, 4));
									}
								}
							}

							if (c.getGivenRoom() != null) {
								room.setCellValue(c.getGivenRoom().getNumber());

							}

							Set<Professor> profs= c.getProfessors();
							if (!c.getProfessors().isEmpty()) {
								String profStr= "";
								for(Professor p: profs){
									profStr+=p.getName()+", ";
								}
								profCell.setCellValue(profStr.substring(0, profStr.length()-2));
							}

							if (!ismet) {
								for (int i = 3; i < 16; i++) {
									row1.getCell(i).setCellStyle(red);
								}
							}

							/*
							 * if (c.getProfessor() != null) {
							 * profCell.setCellValue(c.getProfessor()
							 * .getName()); ; }
							 */
							classCount = 5;
						}

						sectionsCount++;
					}
				}
			}

		}
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

	public void generateInfo(Map<Class, String> noSched) {
		// loop through all the not scheduled classes and print them and the
		// cause they couldn't be scheduled
		// the string in the map actually hides the reason why the class was not
		// scheduled
		notScheduled.setColumnWidth(0, 5000);
		notScheduled.setColumnWidth(1, 10000);
		notCount = 0;
		Row firstRow = notScheduled.createRow(0);
		Cell cName = firstRow.createCell(0);
		cName.setCellStyle(blue);
		cName.setCellValue("Class not Scheduled");
		Cell reasons = firstRow.createCell(1);
		reasons.setCellStyle(blue);
		reasons.setCellValue("Reason");
		notCount++;
		for (Class c : noSched.keySet()) {
			Row row = notScheduled.createRow(notCount);
			Cell className = row.createCell(0);
			className.setCellValue(c.getCourse_name());
			Cell reason = row.createCell(1);
			reason.setCellValue(noSched.get(c));
			notCount++;
		}
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
