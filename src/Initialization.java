import java.io.*;


/**
 * 
 * @author Group 1
 *
 */
public class Initialization {
	
	public void initialization(){
		String fileDir = "Data";
		try {
		File file = new File(fileDir);
			if (!file.exists()){
				file.mkdir();
				
				File staffAccount = new File(file, "StaffAccount.txt");
				staffAccount.createNewFile();	
				PrintWriter output2 = new PrintWriter(staffAccount);
				output2.print("M001 123456");
				output2.close();
				
				File appointment = new File(file, "Appointments.txt");
				appointment.createNewFile() ;
				PrintWriter output1 = new PrintWriter(appointment);
				output1.println("2011-01-01 AM 000 M000 NONE 0");
				output1.close();
							
				File manager = new File(file, "M001.txt");
				manager.createNewFile() ;
				PrintWriter output3 = new PrintWriter(manager);
				output3.println("Defualt Manager");
				output3.print("Male");
				output3.close();
				
				File room = new File(file, "Rooms.txt");
				room.createNewFile() ;
				PrintWriter output4 = new PrintWriter(room);
				output4.print("100");
				output4.close();
				
				File member = new File("Data/Member");
				member.mkdir();
			}
		}
		catch(Exception ex){	
			ex.printStackTrace();
			System.exit(0);
		}
	}
}
