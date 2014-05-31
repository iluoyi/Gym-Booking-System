import java.io.*;
import javax.swing.*;
/**
 * It is the basic class of a member and it defines all
 * the integral attributes of a member
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Member {

	String memName = null;
	String memGender = null;
	String memPhone = null;
	String memDOB = null;
	String memNotes = null;
	
	/**
	 * Default constructor
	 */
	public Member(){}
	/**
	 * The constructor with member ID
	 * @param id The member's ID number
	 */
	public Member(String id){
		
		String fileName ="Data/Member/" + id + ".txt";
		try{	
			RandomAccessFile ra = new RandomAccessFile(fileName,"r");	
			memName = ra.readLine();
			ra.close();
		}
		catch(Exception ex){	
			JOptionPane.showMessageDialog(null, "The member doesn't exist!", "Warning", JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	/**
	 * To acquire member's gender
	 * @return the gender of the member in String
	 */
	public String getMemGender() {
		return memGender;
	}
	/**
	 * To set member's gender in String
	 * @param memGender the member's gender
	 */
	public void setMemGender(String memGender) {	
		this.memGender = memGender;
	}
	/**
	 * To acquire member's phone
	 * @return the phone number of the member in String
	 */
	public String getMemPhone() {
		return memPhone;
	}
	/**
	 * To set member's phone number in String
	 * @param memPhone the member's phone number in String
	 */
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	/**
	 * To acquire member's date of birth 
	 * @return the date of birth of the member in String
	 */
	public String getMemDOB() {
		return memDOB;
	}
	/**
	 * To set member's date of birth in String
	 * @param memDOB the member's date of birth in String
	 */
	public void setMemDOB(String memDOB) {
		this.memDOB = memDOB;
	}
	/**
	 * To acquire member's notes
	 * @return the notes of the member in String
	 */
	public String getMemNotes() {
		return memNotes;
	}
	/**
	 * To set member's notes in String
	 * @param memNotes the notes of the member in String
	 */
	public void setMemNotes(String memNotes) {
		this.memNotes = memNotes;
	}
	/**
	 * To set member's name in String
	 * @param memName  member's name in String
	 */
	public void setMemName(String memName) {
		this.memName = memName;
	}
	/**
	 * To acquire member's name
	 * @return the name of the member in String
	 */
	public String getMemName(){

		return memName;
		
	}
	/**
	 * To check if a member exists or not
	 * @param id The member's ID
	 * @param info member's detailed information
	 * @return <tt>true</tt> if the member exists
	 */
	public Boolean getMemberInfo(String id,String[] info){
	    
		String fileName ="Data/Member/" + id + ".txt";
		Boolean exist = true;

		try{	
			RandomAccessFile ra = new RandomAccessFile(fileName,"r");	
			for(int i = 0;i<5;i++){		
				info[i]= ra.readLine();			
			}		
			ra.close();
			return exist;	
		}
		catch(Exception ex){		
			JOptionPane.showMessageDialog(null, "The member doesn't exist!", "Warning", JOptionPane.INFORMATION_MESSAGE);
			exist = false;
			return exist;
		}
	}
}
