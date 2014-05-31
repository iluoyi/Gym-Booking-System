import java.awt.event.*;

import javax.swing.*;
/**
 * This contains the main function to run the DEMO.
 * It controls the transition between distinct interfaces.
 */
public class UI_control {

	/**
	 * This is the main function
	 * @param args Input from the console
	 */
	public static void main(String[] args){
		 final Login_UI loginFrame = new Login_UI();
		 
		 new Initialization().initialization();
		 new UserInterface().setCurrentApp(new GetAppointment().getAppointment());
				 
		 loginFrame.setVisible(true); 
		 loginFrame.loginButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					new UserInterface().setUserID(Login_UI.userID.getText());
					String loginID = Login_UI.userID.getText();
					String loginPW = String.valueOf(loginFrame.password.getPassword());
					
				int flag = new StaffAccount().check(loginID,loginPW);	
				Boolean isSelected = false;
				
				for (int i = 0;i<5;i++){
					
					isSelected=loginFrame.identities[i].isSelected();
					if(isSelected)
						break;
				}
			
				if(loginID.length()==0||loginPW.length()==0){
					
					JOptionPane.showMessageDialog(null, "Invalid Input!", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else if(flag==-1){
					
					JOptionPane.showMessageDialog(null, "User doesn't exist OR incorrect password!", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else if(!isSelected){
					
					JOptionPane.showMessageDialog(null, "Please select your identity!", "Warning", JOptionPane.INFORMATION_MESSAGE);
				
				}
				
				else if(Login_UI.userID.getText().charAt(0)!=loginFrame.identity.charAt(0)){
					
					JOptionPane.showMessageDialog(null, "User ID doesn't match the identity!", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
						
				else{
					UserInterface mainFrame = null;
					switch(loginFrame.identity.charAt(0)){
					case 'M':
						loginFrame.setVisible(false);
						mainFrame = new Manager_UI();
						mainFrame.setVisible(true);
						break;
					case 'R':
						loginFrame.setVisible(false);
						mainFrame = new Receptionist_UI();
						mainFrame.setVisible(true);
						break;
					case 'P':
						loginFrame.setVisible(false);
						mainFrame = new Physiotherapist_UI();
						mainFrame.setVisible(true);
						break;
					case 'N':
						loginFrame.setVisible(false);
						mainFrame = new Spectialist_UI();
						mainFrame.setVisible(true);
						break;
					case 'T':
						loginFrame.setVisible(false);
						mainFrame = new Spectialist_UI();
						mainFrame.setVisible(true);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Please choose your identity!", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
					mainFrame.statusBar.jLabel_identity.setText("   "+loginFrame.identity);
					
				}
				}
			});
		 
		 //Here add listener to the jbt_logout button 
		 StatusBar.jbt_logout.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
				 loginFrame.setVisible(true);
				 Login_UI.userID.setText("");
				 loginFrame.password.setText("");
				 new OutputAppointment().printInFile();
	         }
		 });
		 
		 //Here add listener to the jbt_exit button 
		 StatusBar.jbt_exit.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
				 new OutputAppointment().printInFile();
				 System.exit(0);
			 }
	     });
	}
}