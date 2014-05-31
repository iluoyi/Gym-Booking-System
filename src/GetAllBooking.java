import java.util.Vector;
/**
 * To acquire the whole booking records with appointment flag to be 1 rather than 0.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class GetAllBooking {
	String bookDay = new DateTest().changeDateForm()[0];
	Vector<Appointment> temp = UserInterface.getCurrentApp();
	/**
	 * The method to acquire all booking records
	 * @return the list of booking records
	 */
	public Vector<Appointment> getAllBook(){
		int flag = 0;
		Vector<Appointment> currentBook = new Vector<Appointment>();
		
		for(int i =0;i<temp.size();i++){
			if(temp.get(i).getData().equals(bookDay)){
				flag = i;
				break;
			}
		}
		for(int j = flag;j<temp.size();j++){
			if(!temp.get(j).getMemberID().equals("000")){
				currentBook.add(temp.get(j));
			}
		}
		return currentBook;
	}
}
