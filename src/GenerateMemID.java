import java.io.File;
/**
 * This class is an auxiliary class used to generate an available ID for the 
 * registration of member so that no duplicated ID exits.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class GenerateMemID {

	String memID = null;
	int memberNo = 0;
	/**
	 * Default constructor
	 */
	public GenerateMemID (){
		
		//Get all the files in the directory
		File file = new File("Data/Member");
		File[] subFile = file.listFiles();
		
		memberNo = subFile.length;	
		if(subFile.length < 10)
			memID = "00" + (subFile.length + 1);
		else if (subFile.length >= 10 && subFile.length < 100)
			memID = "0" + (subFile.length + 1);
		else
			memID = "" + (subFile.length + 1);
	}
	/**
	 * Acquire an appropriate member ID
	 * @return an appropriate member ID
	 */
	public String getMemID(){
		
		return memID;
	}
	/**
	 * Get the number of IDs
	 * @return the number of IDs of members
	 */
	public int getMemNo(){
		
		return memberNo;
	}
}
