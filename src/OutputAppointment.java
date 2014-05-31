import java.io.*;
import java.util.*;

/**
 * This class is used to print out all the appointments onto the 
 * screen or into the files
 * @author royal (luoyi.zxx@gmail.com)
 */
public class OutputAppointment {

	
	/**
	 * Write all the appointments into files
	 * @throws Exception if file is not found
	 */
	void printInFile(){
		String fileName ="Data/Appointments.txt";
		Vector<Appointment> tempApp = UserInterface.getCurrentApp();

		try{
			//Write the changes to the file
			File profile = new File(fileName);
			PrintWriter output = new PrintWriter(profile);	
			for (int i=0; i<tempApp.size()-1; i++){
				output.println(tempApp.get(i).toString());
			}
			output.println(tempApp.get(tempApp.size()-1).toString());
			output.close();
		}
		catch(Exception ex){		
			ex.printStackTrace();
			System.exit(0);
		}	
	}
}
