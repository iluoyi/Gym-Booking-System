import java.io.RandomAccessFile;
/**
 * This auxiliary class is used to deal with operations on staff.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Staff{
	
	String staffName = null;
	String staffGender = null;
	String spectType = null;
	String staffID = null;
	/**
	 * To acquire a worker's name
	 * @return worker's name in String
	 */
	public String getStaffName(){
		return staffName;
	}
	/**
	 * To acquire a worker's gender
	 * @return worker's gender in String
	 */
	public String getStaffGender(){
		return staffGender;
	}
	/**
	 * To acquire a specialist's type
	 * @return specialist's type in String
	 */
	public String getSpectType(){
		return spectType;
	}
	/**
	 * Default constructor
	 * @param staffID the ID number of a worker
	 */
	public Staff (String staffID){
		
		String fileName ="Data/" + staffID + ".txt";
		RandomAccessFile ra;
		this.staffID = staffID;
		
		try {
			ra = new RandomAccessFile(fileName,"r");
		
			staffName = ra.readLine();
			staffGender = ra.readLine();
			
			ra.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Identify the type of the specialist
		if (staffID.charAt(0)=='N'){
			spectType = "Nutritionist";
		}
		else if (staffID.charAt(0)=='T'){
			spectType = "Trainer";
		} 
		else spectType = "Physiotherapist";	
	}
	/**
	 * It is the rewrite tostring() method
	 * @return the details of the current worker(specialist)
	 */
	public String toString(){
		
		return "\r\n" + "    Specialist ID: " + staffID + "\r\n\r\n" + "    Specialist Type: " + spectType + "\r\n\r\n" + "    Specialist Name: " + staffName + "\r\n\r\n" +"    Specialist Gender: " + staffGender + "\r\n\r\n" ;
	}
	
}