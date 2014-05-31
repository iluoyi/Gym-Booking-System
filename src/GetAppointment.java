import java.io.RandomAccessFile;
import java.util.Vector;

/**
 * This class defines the method to get appointments from files
 * @author royal (luoyi.zxx@gmail.com)
 *
 */
public class GetAppointment {
	/**
	 * Returns the list of all the appointments 
	 * @return Vector<Appointment> the list of all appointments
	 *
	 */
	public Vector<Appointment> getAppointment(){
		Vector<Appointment> temp = new Vector<Appointment>();
		String fileName ="Data/Appointments.txt";
		RandomAccessFile ra;
		String line = null;
		String[] word = null;
		
		try {
			ra = new RandomAccessFile(fileName,"r");
			while((line = ra.readLine())!=null){
				word = line.split(" ");
				Appointment tempApp = new Appointment();
				tempApp.setData(word[0]);
				tempApp.setTimePeriod(word[1]);
				tempApp.setMemberID(word[2]);
				tempApp.setSpectID(word[3]);
				tempApp.setRoomNo(word[4]);
				tempApp.setFlag(word[5]);
				temp.add(tempApp);
			}
			ra.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
}
