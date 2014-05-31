import java.io.*;
import java.util.*;
/**
 * This auxiliary class is used to deal with operations on rooms.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Room {
	int roomNo = 0;
	
	Vector<String>room = new Vector<String>();
	
	RandomAccessFile ra;
	
	String fileName ="Data/Rooms"  + ".txt";
/**
 * Default constructor
 */
	public Room(){
		try {	
			File account = new File(fileName);
			if(!account.exists()){
				System.out.println("Errors occur at Room's Constructor!");
				System.exit(0);
			}
			Scanner input = new Scanner(account);
			while(input.hasNext()){
				roomNo++;		
				room.add(input.nextLine());
			}	
			input.close();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}		
	}
	/**
	 * To acquire a room's number
	 * @return room number in String
	 */
	public int getRoomNo(){
	   return roomNo;	
	}
	/**
	 * To acquire a list of rooms
	 * @return the list of all rooms
	 */
	public Vector<String> getRoom(){
		return room;
	}
	/**
	 * To insert a new room into the room list and upgrade the related files
	 * @param roomNo the room number wanted to add
	 */
	public void addUpdateFile(String roomNo){	
		int insert = 0;
		
		for(int i = 0;i<room.size();i++){
			if(Integer.parseInt(room.get(i))>Integer.parseInt(roomNo)){	
				insert = i;
				break;
			}
			else 
				insert = room.size();
		}
		room.add(insert,roomNo);
		try{
			//Write the changes to the file
			File profile = new File(fileName);
			PrintWriter output = new PrintWriter(profile);	
		for(int j = 0;j<room.size();j++){
				output.println(room.get(j));
			}
			output.close();
		}
		
		catch(Exception ex){
			ex.printStackTrace();
			System.exit(0);
		}
	}
	/**
	 * To upgraded related files after remove a room
	 * @param changedRoom the newest list of rooms after removing a room
	 */
	public void removeUpdateFile(Vector<String> changedRoom){
		try{			
			//Write the changes to the file
			File profile = new File(fileName);
			PrintWriter output = new PrintWriter(profile);	

			for(int j = 0;j<changedRoom.size();j++){
				output.println(changedRoom.get(j));			
			}
			output.close();
		}
		catch(Exception ex){		
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
}