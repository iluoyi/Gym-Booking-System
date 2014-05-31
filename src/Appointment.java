/**
 * It is the basic class of an appointment and it defines all
 * the integral attributes of an appointment
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Appointment {
	private String Date = null;
	private String timePeriod = null;
	private String memberID = null;
	private String spectID = null;
	private String roomNo = null;
	private String flag = null;

	/**
	 * It sets the date of an appointment
	 * @param string the date to be set
	 */
	public void setData(String string){
		Date = new String(string);
	}
	/**
	 * It sets the time period of an appointment
	 * @param string the time period to be set
	 */
	public void setTimePeriod(String string){
		timePeriod = new String(string);
	}
	/**
	 * It sets the member ID of an appointment
	 * @param string the member ID to be set
	 */
	public void setMemberID(String string){
		memberID = new String(string);
	}
	/**
	 * It sets the specialist ID of an appointment
	 * @param string the specialist ID to be set
	 */
	public void setSpectID(String string){
		spectID = new String(string);
	}
	/**
	 * It sets the room number of an appointment
	 * @param string the room number to be set
	 */
	public void setRoomNo(String string){
		roomNo = new String(string);
	}
	/**
	 * It sets the flag of an appointment to indicate if it is 
	 * a record for leaving, 0 for leaving and  1 for work
	 * @param string the flag to be set
	 */
	public void setFlag(String string){
		flag = new String(string);
	}
	/**
	 * It returns the date
	 * @return String the date
	 */
	public String getData(){
		return Date;
	}
	/**
	 * It returns the time period
	 * @return String the time period
	 */
	public String getTimePeriod(){
		return timePeriod;
	}
	/**
	 * It returns the member ID
	 * @return String the member ID
	 */
	public String getMemberID(){
		return memberID;
	}
	/**
	 * It returns the specialist ID
	 * @return String the specialist ID
	 */
	public String getSpectID(){
		return spectID;
	}
	/**
	 * It returns the room number
	 * @return String the room number
	 */
	public String getRoomNo(){
		return roomNo;
	}
	/**
	 * It returns the flag
	 * @return 1 if the record is for leaving and 0 for work 
	 */
	public String getFlag(){
		return flag;
	}
	/**
	 * It returns the whole information of the appointment
	 * @return String the whole information of the appointment
	 */
	public String toString(){
		String string = null;
		string = new String(Date+" "+timePeriod+" "+memberID+" "+spectID+" "+roomNo+" "+flag);
		return string;
	}
	/**
	 * The constructor of Appointment
	 */
	public Appointment(){
		Date = new String("");
		timePeriod = new String("");
		memberID = new String("");
		spectID = new String("");
		roomNo = new String("");
		flag = new String("");
	}
}
