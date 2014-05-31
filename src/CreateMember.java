import java.io.*;
/**
 * This control class is used to create a member's profile.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class CreateMember {
/**
 * Input member ID and related information which is expressed as an array of String.
 * Such information contains member's name, gender, phone number, date of birth and 
 * some additional notes.
 * @param id The member's ID
 * @param Info The member's detailed information
 */
	public void createMemberInfo(String id,String[] Info){   
		String fileName ="Data/Member/" + id + ".txt";
		try{
			//Write the changes to the file
			File profile = new File(fileName);
			PrintWriter output = new PrintWriter(profile);	
			 
			for(int j = 0;j<4;j++){		
				output.println(Info[j]);			
			}
			output.close();
		}
		catch(Exception ex){	
			ex.printStackTrace();
			System.exit(0);
		}
	}
}
