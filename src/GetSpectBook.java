import java.util.Vector;
/**
 * To obtain specific booking records which only belongs to the current specialist.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class  GetSpectBook{
	Vector<Appointment> temp = new GetAllBooking().getAllBook();
	/**
	 * By providing specialist's ID number, this method will return the wanted list
	 * of booking recording corresponding to the provided ID.
	 * @param id The specialist's ID number
	 * @return a list of booking records according to the input ID
	 */
	public Vector<Appointment> getAllBook(String id){
		Vector<Appointment> spectBook = new Vector<Appointment>();
		
		for(int i =0;i<temp.size();i++){	
			if(temp.get(i).getSpectID().equals(id)){		
				spectBook.add(temp.get(i));
			}	
		}
		return spectBook;
	}	
}
