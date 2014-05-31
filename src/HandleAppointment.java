import java.util.Vector;

/**
 * This class is used to handle various methods of appointments
 * @author royal (luoyi.zxx@gmail.com)
 */
public class HandleAppointment {
	
	/**
	 * Returns <tt>true</tt> if the specialist is available 
	 * on a specific date and time period
	 * @param date the mentioned date
	 * @param timePeriod the mentioned time period
	 * @param spectID the ID number of mentioned specialist
	 * @return <tt>true</tt> if the mentioned specialist is available at the mentioned time
	 */
		public Boolean isSpecialistAvailable(String date, String timePeriod, String spectID){
			Vector<Appointment> tempApp = UserInterface.getCurrentApp();
			Boolean flag =true;
		
			for (int i=0; i<tempApp.size();i++){
				if ((tempApp.get(i).getData().equals(date)) && (tempApp.get(i).getTimePeriod().equals(timePeriod)) && 
						(tempApp.get(i).getSpectID().equals(spectID))){
					flag = false;
				}
			}
			
			return flag;
		}
		
		/**
		 * Returns <tt>true</tt> if the current member has 
		 * no appointment at the mentioned time
		 * @param date the mentioned date
		 * @param timePeriod the mentioned time period
		 * @param memberID the ID number of mentioned member
		 * @return <tt>true</tt> if the current member has no appointment at the mentioned time
		 */
		public Boolean isTimeAvailable(String date, String timePeriod, String memberID){
			Vector<Appointment> tempApp = UserInterface.getCurrentApp();
			Boolean flag =true;
		
			for (int i=0; i<tempApp.size();i++){
				if ((tempApp.get(i).getData().equals(date)) && (tempApp.get(i).getTimePeriod().equals(timePeriod)) && 
						(tempApp.get(i).getMemberID().equals(memberID))){
					flag = false;
				}
			}
			
			return flag;
		}
		
		/**
		 * Returns <tt>true</tt> if the mentioned room is available 
		 * at the mentioned time for all the members
		 * @param date the mentioned date
		 * @param timePeriod the mentioned time period
		 * @param roomNo the number of mentioned room
		 * @return <tt>true</tt> if the mentioned room is available at the mentioned time
		 */
		public Boolean isRoomAvailable(String date, String timePeriod, String roomNo){
			Vector<Appointment> tempApp = UserInterface.getCurrentApp();
			Boolean flag =true;
		
			for (int i=0; i<tempApp.size();i++){
				if ((tempApp.get(i).getData().equals(date)) && (tempApp.get(i).getTimePeriod().equals(timePeriod)) && 
						(tempApp.get(i).getRoomNo().equals(roomNo))){
					flag = false;
				}
			}
			
			return flag;
		}
		
		/**
		 * Returns <tt>true</tt> if it is time for leaving for a specialist
		 * at the mentioned time
		 * @param date the mentioned date
		 * @param timePeriod the mentioned time period
		 * @param spectID the ID number of the specialist
		 * @return <tt>true</tt>  if it is time for leaving for a specialist at the mentioned time
		 */
		public boolean isTimeforLeave(String date, String timePeriod, String spectID){
			boolean flag = false;
			Vector<Appointment> tempApp = UserInterface.getCurrentApp();
			for (int i=0; i<tempApp.size(); i++){
				if ((tempApp.get(i).getFlag().equals("0")) && (tempApp.get(i).getData().equals(date))
					&& (tempApp.get(i).getSpectID().equals(spectID)) && (tempApp.get(i).getTimePeriod().equals(timePeriod))){
					flag = true; //It's time to leave
				}
			}
			return flag;
		}
		
		/**
		 * Returns <tt>true</tt> if it is time for work for a specialist
		 * at the mentioned time
		 * @param date the mentioned date
		 * @param timePeriod the mentioned time period
		 * @param spectID the ID number of the specialist
		 * @return <tt>true</tt>  if it is time for work for a specialist at the mentioned time
		 */
		public boolean isTimeforWork(String date, String timePeriod, String spectID){
			boolean flag = false;
			Vector<Appointment> tempApp = UserInterface.getCurrentApp();
			for (int i=0; i<tempApp.size(); i++){
				if ((tempApp.get(i).getFlag().equals("1")) && (tempApp.get(i).getData().equals(date))
					&& (tempApp.get(i).getSpectID().equals(spectID)) && (tempApp.get(i).getTimePeriod().equals(timePeriod))){
					flag = true; //It's time to leave
				}
			}
			return flag;
		}
		
		/**
		 * It inserts a new appointment into the existing appointments according
		 * to the alphabetical order
		 * @param newest the appointment decided to be inserted into the appointment list
		 */
		public void insertAppointment(Appointment newest){
			int start=0;
			Vector<Appointment> tempApp = UserInterface.getCurrentApp();
			int end=tempApp.size()-1;
			int k=0; //k indicates the final location
			Boolean booleanFlag=true;
			
			//find k through Date 
			if (newest.getData().compareTo(tempApp.get(start).getData())<0){
				k=start-1;
				booleanFlag = false;
			} else if (newest.getData().compareTo(tempApp.get(end).getData())>0){
				k=end;
				booleanFlag = false;
			} else{
			for (int i=start; i<=end; i++){
				if (newest.getData().compareTo(tempApp.get(i).getData())<=0){
					start=i;
					break;
				}
				if (i==end){start=end;}
			}
			
			for (int i=end; i>=start; i--){
		
				if (newest.getData().compareTo(tempApp.get(i).getData())>=0){
					end=i;
					break;
				}
				if (i==start){end=start;}
			}
			}
			
			//find k through period
			if ((k==0) && booleanFlag){//means cannot be located through Date, need period
				booleanFlag = true;
				if (newest.getTimePeriod().compareTo(tempApp.get(start).getTimePeriod())<0){
					k=start-1;
					booleanFlag = false;
				} else if (newest.getTimePeriod().compareTo(tempApp.get(end).getTimePeriod())>0){
					k=end;
					booleanFlag = false;
				} else{
				for (int i=start; i<=end; i++){
					if (newest.getTimePeriod().compareTo(tempApp.get(i).getTimePeriod())<=0){
						start=i;
						break;
					}
					if (i==end) {start=end;}
					}
				for (int i=end; i>=start; i--){
					if (newest.getTimePeriod().compareTo(tempApp.get(i).getTimePeriod())>=0){
						end=i;
						break;
					}
					if (i==start){end=start;}
				}
				}
			}
			
			//find k through memeberID
			if ((k==0) && booleanFlag) {//means cannot be located through Date and period, need memberID
				booleanFlag = true;
				if (newest.getMemberID().compareTo(tempApp.get(start).getMemberID())<0){
					k=start-1;
					booleanFlag = false;
				} else if (newest.getMemberID().compareTo(tempApp.get(end).getMemberID())>0){
					k=end;
					booleanFlag = false;
				} else{
				for (int i=start; i<=end; i++){
					if (newest.getMemberID().compareTo(tempApp.get(i).getMemberID())<=0){
						start=i;
						break;
					}
					if (i==end) {start=end;}
					}
				for (int i=end; i>=start; i--){
					if (newest.getMemberID().compareTo(tempApp.get(i).getMemberID())>=0){
						end=i;
						break;
					}
					if (i==start){end=start;}
				}
				}
				if ((start==end) && booleanFlag) {k=start-1;}
			} 
			
			//now really insert the newest appointment
			UserInterface.getCurrentApp().add(k+1, newest);
		}
		
		/**
		 * It deletes an appointment
		 * @param death the appointment decided to be deleted
		 */
		public void deleteAppointment(Appointment death){
			Vector<Appointment> tempApp = UserInterface.getCurrentApp();
			int k=0;
			for (int i=0; i<tempApp.size(); i++){
				if ((death.getData().equals(tempApp.get(i).getData())) &&
						(death.getTimePeriod().equals(tempApp.get(i).getTimePeriod())) &&
							(death.getMemberID().equals(tempApp.get(i).getMemberID()))){
							k=i;
						}
			}
			 UserInterface.getCurrentApp().remove(k);
		}
		
		/**
		 * It changes the room number of an appointment
		 * @param member the member ID 
		 * @param roomNo the number of the room
		 * @param date the mentioned time
		 * @param timePeriod the time period, AM or PM
		 */
		public void changeAppRoom(String member, String roomNo, String date, String timePeriod){
			Vector<Appointment> tempApp = UserInterface.getCurrentApp();
			for (int i=0; i<tempApp.size(); i++){
				if ((tempApp.get(i).getSpectID().charAt(0)=='P') && (tempApp.get(i).getData().equals(date)) && 
					((tempApp.get(i).getTimePeriod().equals(timePeriod))) && (tempApp.get(i).getMemberID().equals(member))){
					UserInterface.getCurrentApp().get(i).setRoomNo(roomNo);
				}
			}
		}		
	}