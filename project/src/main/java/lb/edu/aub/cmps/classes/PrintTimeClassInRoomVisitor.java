package lb.edu.aub.cmps.classes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import lb.edu.aub.cmps.algorithm.BasicScheduler;
import lb.edu.aub.cmps.algorithm.IScheduler;
import lb.edu.aub.cmps.algorithm.SetUp;

public class PrintTimeClassInRoomVisitor implements RoomVisitor {
	
	private static int rowsCount;
	private FileOutputStream output;
	private Workbook wb;
	private Sheet Rooms;
	private String excelFileName;
	/**
	 * TODO
	 */
	public void visit(Room r) {
		//PrintStream out = new PrintStream(System.out);
		wb=new HSSFWorkbook();
		Rooms = wb.createSheet("Rooms");
		excelFileName = "C:/Users/Yasmin/Documents/299/Test.xls";
		rowsCount++;
		Row row= Rooms.createRow(rowsCount);
		Cell name= row.createCell(0);
		name.setCellValue(r.getNumber());
		System.out.println(r.getNumber());
		/*for(TimeSlot t: r.getReserved().keySet() ){
			Row row1= Rooms.createRow(rowsCount);
			Cell name1= row1.createCell(0);
			name1.setCellValue(r.getNumber());
			
		}*/
		
		try{
			output= new FileOutputStream(excelFileName);
			wb.write(output);
		}catch(IOException e){
		}
		System.out.println("COUNTING gROWS"+rowsCount);
		
		
	}
	
	public static void main(String[] args){
		rowsCount=0;
		BasicScheduler sched = new BasicScheduler();
		sched.schedule();
		SetUp s = sched.setup();
		System.out.println("_________________________________________________________________________________");
		System.out.println("_________________________________________________________________________________");
		System.out.println("_________________________________________________________________________________");
		
		for(Room r: s.getRooms()){
			r.accept(new PrintTimeClassInRoomVisitor());
		}
	}

}
