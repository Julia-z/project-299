package lb.edu.aub.cmps.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import lb.edu.aub.cmps.services.RoomService;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class PrintTimeClassInRoomVisitor implements RoomVisitor {
	/**
	 * Julia's fields
	 */
	HSSFWorkbook wb;
	HSSFSheet Rooms;
	int rowsCount;
	
	/**
	 * Yasmin's fields
	 */
	private FileOutputStream output;
	private String excelFileName;
	
	public PrintTimeClassInRoomVisitor(){
		wb = new HSSFWorkbook();
		Rooms = wb.createSheet("Rooms");
		excelFileName = "Outputs\\Test.xls";
		rowsCount = 0;

	}

	public void visit(Room r) {
		Row row= Rooms.createRow(rowsCount);
		rowsCount++;
		Cell name= row.createCell(0);
		name.setCellValue("jello");
		name.setCellValue(r.getNumber());
		for(TimeSlot t: r.getReserved().keySet() ){
			Row row1= Rooms.createRow(rowsCount);
			Cell name1= row1.createCell(1);
			name1.setCellValue(t.toString());
			
			Cell course = row1.createCell(2);
			course.setCellValue(r.getReserved().get(t));
			rowsCount ++;
		}
		
		try{
			output= new FileOutputStream(new File(excelFileName));
			wb.write(output);
			output.close();
		}catch(IOException e){
		}
		System.out.println("COUNTING gROWS"+rowsCount);
		
		
	}
	
	public static void main(String[] args){
		//rowsCount=0;
	//	BasicScheduler sched = new BasicScheduler();
		//sched.schedule();
		//SetUp s = sched.setup();
		System.out.println("_________________________________________________________________________________");
		System.out.println("_________________________________________________________________________________");
		System.out.println("_________________________________________________________________________________");
		RoomVisitor visitor = new PrintTimeClassInRoomVisitor();
		Set<Room> rooms = new RoomService().getAllRooms();
		for(Room r: rooms){
			r.initializeReserved();
		}
		for(Room r: rooms){
			r.accept(visitor);
		}
	}

}
