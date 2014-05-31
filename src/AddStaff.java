import java.io.*;
import java.util.Vector;
/**
 * This class is a control class which is responsible for managing information
 * of personnel. It contains 3 major function - createProfile, addLeaveReason,
 * and addToStaffAccount.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class AddStaff {
	/**
	 * It creates new staff profile in an individual file with the id as its name.
	 * This file will contains staff's name and gender.
	 * @param id The ID number of the worker
	 * @param Info New worker's detailed information
	 */
	public void createProfile(String id,String[] Info){
		String fileName ="Data/" + id + ".txt";
		try{
			//Write the changes to the file
			File profile = new File(fileName);
			PrintWriter output = new PrintWriter(profile);	
			for(int j = 0;j<Info.length;j++){
				output.println(Info[j]);	
			}
			output.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.exit(0);
		}
	}
	/**
	 * It is used to attach leaving reasons to the individual profile when a specialist
	 * wants to ask for a leave on a certain time.
	 * @param id The specialist's ID number
	 * @param date The date
	 * @param timePeriod The time period, AM or PM
	 * @param reason The detailed reasons for leaving
	 */
	public void addLeaveReason(String id,String date, String timePeriod, String reason){
		String fileName ="Data/" + id + ".txt";
		try{
			FileWriter profile = new FileWriter(fileName, true);
			PrintWriter output = new PrintWriter(profile);	
			output.printf("\r\n*****************************\r\n");
			output.printf("Date: "+date+"\r\n");
			output.printf("Time: "+timePeriod+"\r\n");
			output.printf("Reason:\r\n"+reason+"\r\n");
			output.printf("*****************************\r\n");
			output.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.exit(0);
		}
	}
	/**
	 * It upgrades the current staff account file with inputed ID number and password.
	 * @param id The worker's ID
	 * @param passwd The worker's password
	 */
	public void addToStaffAccount(String id,String passwd){
	    
			Vector<String> list = new StaffAccount().getAllStaff();
			
			int index = 0;
			int num1 = new StaffAccount().getManagerNum();
			int num2 = new StaffAccount().getRecepNum();
			int num3 = new StaffAccount().getPhyNum();
			int num4 = new StaffAccount().getNutrNum();
			
			if(id.charAt(0)=='M'){
				index = num1;
				list.add(index,id + " " + passwd);
			}		
			else if(id.charAt(0)=='R'){
				index = num1+num2;
				list.add(index,id + " " + passwd);	
			}
			else if(id.charAt(0)=='P'){
				index = num1+num2+num3;
				list.add(index,id + " " + passwd);	
			}			
			else if(id.charAt(0)=='N'){
				index = num1+num2+num3+num4;
				list.add(index,id + " " + passwd);	
			}
			else{
				list.add(id + " " + passwd);	
			}	

			//Update the StaffAccount.txt file
			new StaffAccount().addUpdateFile(list);
		}
}
