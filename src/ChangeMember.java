import java.io.File;
import java.io.PrintWriter;
/**
 * Since member's information maybe changed and maintained, this class is responsible
 * for such kind of functions.
 * @author royal (luoyi.zxx@gmail.com)
 *
 */
public class ChangeMember {
/**
 * By providing member ID and information waiting to be upgraded, the changeMemberInfo
 * function can change relative data in the specific file.
 * @param id The member's ID
 * @param newInfo The member's detailed information
 */
	public void changeMemberInfo(String id,String[] newInfo){
		String fileName ="Data/Member/" + id + ".txt";
		try{
			//Write the changes to the file
			File profile = new File(fileName);
			PrintWriter output = new PrintWriter(profile);	
			 
			for(int j = 0;j<5;j++){			
				output.println(newInfo[j]);				
			}
			output.close();
		}	
		catch(Exception ex){
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
}
