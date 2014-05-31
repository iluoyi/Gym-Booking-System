import java.util.Vector;
/**
 * This control class is used to remove the staff record from its related file
 * @author royal (luoyi.zxx@gmail.com)
 */
public class RemoveStaff {
/**
 * To remove a work by providing his/her identification 
 * @param removedStaff the list of staff after removing someone
 */
	public void removeFromStaffAccount(Vector<String> removedStaff){
	   		//Update the StaffAccount.txt file
			new StaffAccount().removeUpdateFile(removedStaff);		
		}
}
