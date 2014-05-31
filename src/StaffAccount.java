import java.io.*;
import java.util.*;
/**
 * This is an auxiliary class used to identify attributes of the account of staff
 * @author royal (luoyi.zxx@gmail.com)
 */
public class StaffAccount{

	int checkResult = -1;
	
	private int managerNum = 0;
	private int recepNum = 0;
	private int phyNum = 0;
	private int nutrNum = 0;
	private int traNum = 0;
	
	String fileName ="Data/StaffAccount.txt";
	
	private Vector<String> manager = new Vector<String>();
	private Vector<String>  receptionist= new Vector<String>();
	private Vector<String> physiotherapist = new Vector<String>();
	private Vector<String> nutritionist = new Vector<String>();
	private Vector<String> trainer = new Vector<String>();
	
	private Vector<String> physiotherapistID = new Vector<String>();
	private Vector<String> nutritionistID = new Vector<String>();
	private Vector<String> trainerID = new Vector<String>();
	
	private Vector<String> allStaff = new Vector<String>();
	private Vector<String> allSpecialist = new Vector<String>();
	
	Scanner input = null;
	/**
	 * Default constructor
	 */
	public StaffAccount(){
		
		try{
			File account = new File(fileName);
			if(!account.exists()){
				System.out.println("Errors occur at StaffAccount's Constructor");
				System.exit(0);
			}
			input = new Scanner(account);
			
			while(input.hasNext()){

				String s = input.nextLine();
				
				if(s.charAt(0)=='M'){			
					managerNum += 1;
					manager.add(s);
				}
		
				else if(s.charAt(0)=='R'){		
					recepNum += 1;
					receptionist.add(s);
				}
					
				else if(s.charAt(0)=='P'){	
					phyNum += 1;
					physiotherapist.add(s);
					physiotherapistID.add(s.substring(0, 4));
				}
					
				else if(s.charAt(0)=='N'){
					nutrNum += 1;
					nutritionist.add(s);
					nutritionistID.add(s.substring(0, 4));
				}
					
				else{
					traNum += 1;
					trainer.add(s);
					trainerID.add(s.substring(0, 4));
				}
			}
			input.close();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * To upgrade the related files according to provided information after adding a worker
	 * @param s a vector contains newest accounts information
	 */
	public void addUpdateFile(Vector<String> s){
		
		try{	
			//Write the changes to the file
			File profile = new File(fileName);
			PrintWriter output = new PrintWriter(profile);	
			for(int j = 0;j<s.size();j++){
				output.println(s.get(j));
			}
			output.close();
		}
	
		catch(Exception ex){	
			ex.printStackTrace();
			System.exit(0);
		}
		
	}
	/**
	 * To upgrade the related files according to provided information after removing a worker
	 * @param removedStaff a vector contains newest accounts information
	 */
	void removeUpdateFile(Vector<String> removedStaff){
		
		try{		
			//Write the changes to the file
			File profile = new File(fileName);
			PrintWriter output = new PrintWriter(profile);	
			
			for(int j = 0;j<removedStaff.size();j++){		
				output.println(removedStaff.get(j));			
			}
			output.close();
		}
			catch(Exception ex){		
			ex.printStackTrace();
			System.exit(0);
		}
		
	}
	/**
	 * This method is used to check if input user ID and password matches those saved in database (files).
	 * @param userID The user's ID number
	 * @param password The user's password
	 * @return 0 if matching 
	 */
	public int check(String userID,String password){
		try{
			File account = new File(fileName);
			if(!account.exists()){
				System.out.println("Errors occur at StaffAccount.check()");
				System.exit(0);
			}
			input = new Scanner(account);		
			String s1 = userID + " " +password;
			while(input.hasNext()){
				String s2 = input.nextLine();	
				if(s1.equalsIgnoreCase(s2)){
					checkResult = 0;
					break;
				}
			}	
			input.close();
	}
	catch (Exception e1) {
		e1.printStackTrace();
	}
		return checkResult;
	}
	/**
	 * To get the number of managers	
	 * @return the number of managers in int
	 */
	public int getManagerNum (){
		return managerNum;
	}
	/**
	 * To get the number of receptionists	
	 * @return the number of receptionists in int
	 */
	public int getRecepNum (){
		
		return recepNum;
	}
	/**
	 * To get the number of Physiotherapist	
	 * @return the number of Physiotherapist in int
	 */
	public int getPhyNum (){
		
		return phyNum;
	}
	/**
	 * To get the number of nutritionist	
	 * @return the number of nutritionist in int
	 */
	public int getNutrNum (){
		
		return nutrNum;
	}
	/**
	 * To get the number of trainer	
	 * @return the number of trainer in int
	 */
	public int getTraNum (){
		
		return traNum;
	}
	/**
	 * To get the list of managers
	 * @return the list of managers
	 */
	public Vector<String> getManager (){
		
		return manager;
	}
	/**
	 * To get the list of receptionist
	 * @return the list of receptionist
	 */
	public Vector<String> getRecep (){
		
		return receptionist;
	}
	/**
	 * To get the list of physiotherapist
	 * @return the list of physiotherapist
	 */
	public Vector<String> getPhy (){	
		return physiotherapist;
	}
	/**
	 * To get the list of nutritionist
	 * @return the list of nutritionist
	 */
	public Vector<String> getNutr (){	
		return nutritionist;
	}
	/**
	 * To get the list of trainer
	 * @return the list of trainer
	 */
	public Vector<String> getTra (){
		return trainer;	
	}
	/**
	 * To get the list of all staff
	 * @return the list of all staff
	 */
	public Vector<String> getAllStaff(){
		
		allStaff.addAll(manager);	
		allStaff.addAll(receptionist);
		allStaff.addAll(physiotherapist);
		allStaff.addAll(nutritionist);
		allStaff.addAll(trainer);
		
		return allStaff;
	}
	/**
	 * To get the list of all specialists
	 * @return the list of all specialists
	 */
	public Vector<String> getAllSpecialist(){
	
		allSpecialist.addAll(physiotherapistID);
		allSpecialist.addAll(nutritionistID);
		allSpecialist.addAll(trainerID);
		
		return allSpecialist;
	}
	/**
	 * To set the list of managers
	 * @param temp the list of managers
	 */
	public void setManager(Vector<String> temp){
		manager = temp;
	}
	/**
	 * To set the list of receptionist
	 * @param temp the list of receptionist
	 */
	public void setRecep(Vector<String> temp){
		receptionist = temp;
	}
	/**
	 * To set the list of physiotherapist
	 * @param temp the list of physiotherapist
	 */
	public void setPhy(Vector<String> temp){
		physiotherapist = temp;
	}
	/**
	 * To set the list of nutritionist
	 * @param temp the list of nutritionist
	 */
	public void setNutr(Vector<String> temp){
		nutritionist = temp;
	}
	/**
	 * To set the list of trainer
	 * @param temp the list of trainer
	 */
	public void setTra(Vector<String> temp){
		trainer = temp;
	}
}
