import java.awt.*;
import javax.swing.*;
import java.util.Vector;

/**
 * This panel is used to maintain the information of all the stuff for 
 * managers
 * @author Group 1
 */
public class Staff_Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	Vector<String> staffColumn = new Vector<String>();
	Vector<Vector<String>> staffRow = new Vector<Vector<String>>();
	private JTable jTable_staff = null;
	private JButton jButton_addstaff = null;
	private JButton jButton_deletestaff = null;
	private JScrollPane jScrollPane = null;
	
	/**
	 * This is the default constructor
	 */
	public Staff_Panel() {
		super();
		initialize();
		
		//Staff_Registeration Button
	Staff_Registration.jButton_register.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				//Get all the information typed in 
				String[] newInfo = new String[2];//Store the registration information
				newInfo[0] = Staff_Registration.jtf_name.getText();
				newInfo[1] = (String)Staff_Registration.jComboBox_gender.getSelectedItem();
				String pw = String.valueOf(Staff_Registration.jpf_pw.getPassword());
				String pw2 = String.valueOf(Staff_Registration.jpf_pw2.getPassword());
				Boolean pwFlag = pw.equals(pw2);
				int validName = 0;
			   		   	
				for(int i=0;i<newInfo[0].length();i++){
					
					if(!(newInfo[0].charAt(i)>='A'&&newInfo[0].charAt(i)<='Z')&&!(newInfo[0].charAt(i)>='a'&&newInfo[0].charAt(i)<='z')){
						validName = 1;
						if(newInfo[0].charAt(i)==' ')
							validName = 0;
					}				
				}
				if(newInfo[0].length()==0||pw.length()==0||pw2.length()==0){
					
					JOptionPane.showMessageDialog(null, "The informatin is not complete!", "Warning", JOptionPane.INFORMATION_MESSAGE);
					new Staff_Registration().setVisible(true);
				}
				                                              
				else if(validName == 1){
					
					JOptionPane.showMessageDialog(null, "The Name must contain letters only !", "Warning", JOptionPane.INFORMATION_MESSAGE);
					new Staff_Registration().setVisible(true);
				}
				else if (!pwFlag){
					
					JOptionPane.showMessageDialog(null, "The passwords entered don't match!", "Warning", JOptionPane.INFORMATION_MESSAGE);
					new Staff_Registration().setVisible(true);
				}
				
				else{				
			           	new AddStaff().createProfile(Staff_Registration.jtf_staffID.getText(),newInfo);
			           	new AddStaff().addToStaffAccount(Staff_Registration.jtf_staffID.getText(),pw);
			           	displayStaff();
			           	Staff_Registration.jpf_pw.setText("");
			           	Staff_Registration.jpf_pw2.setText("");
			           	Staff_Registration.jtf_name.setText("");
				}
			}
		});
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setSize(780, 446);
		this.add(getJScrollPane(), BorderLayout.CENTER);
		this.add(getJPanel_button(), BorderLayout.EAST);
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable_staff());

		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable_staff
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTable_staff() {
		staffColumn.add("Staff Name");
		staffColumn.add("Staff ID");
		staffColumn.add("Identity");
		staffColumn.add("Gender");
			
		if (jTable_staff == null) {
			jTable_staff = new JTable(new AllTableModel(staffRow, staffColumn));
			jTable_staff.setShowGrid(true);
			jTable_staff.setRowHeight(30);
		}
		
		displayStaff();
		return jTable_staff;
	}

	/**
	 * This method is used to refresh the jTable_staff table
	 */
	public void displayStaff(){
		staffRow.clear();
		
		for (int i=0; i<new StaffAccount().getManagerNum(); i++){
			
			Vector<String> row = new Vector<String>();
			Vector<String> list = new StaffAccount().getManager();
			
			String staffID = list.get(i).substring(0, 4);
			
			Staff temp = new Staff(staffID);
			
			row.add(temp.getStaffName());
			row.add(staffID);
			row.add("Manager");
			row.add(temp.getStaffGender());
			
			staffRow.add(row);
		}
		
		for (int i=0; i<new StaffAccount().getRecepNum(); i++){
			
			Vector<String> row = new Vector<String>();
			Vector<String> list = new StaffAccount().getRecep();
			
			String staffID = list.get(i).substring(0, 4);
			
			Staff temp = new Staff(staffID);
			
			row.add(temp.getStaffName());
			row.add(staffID);
			row.add("Receptionist");
			row.add(temp.getStaffGender());
			
			staffRow.add(row);
		}

		for (int i=0; i<new StaffAccount().getPhyNum(); i++){
			
			Vector<String> row = new Vector<String>();
			
			Vector<String> list = new StaffAccount().getPhy();
			String staffID = list.get(i).substring(0, 4);
			
			Staff temp = new Staff(staffID);
			
			row.add(temp.getStaffName());
			row.add(staffID);
			row.add("Physiotherapist");
			row.add(temp.getStaffGender());
			
			staffRow.add(row);
		}
		
		for (int i=0; i<new StaffAccount().getNutrNum(); i++){
			
			Vector<String> row = new Vector<String>();
			Vector<String> list = new StaffAccount().getNutr();
			String staffID = list.get(i).substring(0, 4);
			
			Staff temp = new Staff(staffID);
			
			row.add(temp.getStaffName());
			row.add(staffID);
			row.add("Nutritionist");
			row.add(temp.getStaffGender());
			
			staffRow.add(row);
		}
		for (int i=0; i<new StaffAccount().getTraNum(); i++){
			
			Vector<String> row = new Vector<String>();
			Vector<String> list = new StaffAccount().getTra();
			String staffID = list.get(i).substring(0, 4);
			
			Staff temp = new Staff(staffID);
			
			row.add(temp.getStaffName());
			row.add(staffID);
			row.add("Trainer");
			row.add(temp.getStaffGender());
			
			staffRow.add(row);
		}
		SwingUtilities.updateComponentTreeUI(jTable_staff);

	}	
	
	/**
	 * This method initializes jPanel_button
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_button() {
			JPanel jPanel_button = null;
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(15);
			flowLayout.setVgap(80);
			jPanel_button = new JPanel();
			jPanel_button.setLayout(flowLayout);
			jPanel_button.setPreferredSize(new Dimension(200, 530));
			jPanel_button.add(getJButton_addstaff(), null);
			jPanel_button.add(getJButton_deletestaff(), null);
		return jPanel_button;
	}

	/**
	 * This method initializes jButton_addstaff
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_addstaff() {
		if (jButton_addstaff == null) {
			jButton_addstaff = new JButton();
			jButton_addstaff.setText("Add Staff");
			jButton_addstaff.setPreferredSize(new Dimension(173, 28));
			jButton_addstaff.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					new Staff_Registration().setVisible(true);
				}
			});
		}
		return jButton_addstaff;
	}

	/**
	 * This method initializes jButton_deletestaff
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_deletestaff() {
		if (jButton_deletestaff == null) {
			jButton_deletestaff = new JButton();
			jButton_deletestaff.setText("Remove Staff");
			jButton_deletestaff.setPreferredSize(new Dimension(173, 28));
		}
		
		jButton_deletestaff.addActionListener(new java.awt.event.ActionListener() {
			
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		
        		Vector<String> temp = new StaffAccount().getAllStaff();	
				
	            int selectedRow = jTable_staff.getSelectedRow();
	            
	            try{
	            	String  selectStaff = temp.get(selectedRow).substring(0, 4);
	            	 
	            
	            
	            int flag = checkStaff(selectStaff);//Whether the staff can be removed, 1,2--can not ,0--can
	            	
	            if(flag==1){
	            		
	            	JOptionPane.showMessageDialog(null, "This specialist has been booked!", "Warning", JOptionPane.INFORMATION_MESSAGE);
	            }
	            else if (flag==2){
	            		
	            	JOptionPane.showMessageDialog(null, "Manager can not be removed!", "Warning", JOptionPane.INFORMATION_MESSAGE);
	            }	
	            else{
	            		
	            	temp.remove(selectedRow);
	            
	            	new RemoveStaff().removeFromStaffAccount(temp);
	            	
	            	staffRow.clear();
	            	displayStaff();
	            	SwingUtilities.updateComponentTreeUI(jTable_staff);
	            }	
	            }
	            catch(Exception ex){
	            }
        	}	    
    });
		return jButton_deletestaff;
	}

	/**
	 * To check whether the staff is under an appointment in the following
	 * days.
	 * @param staffID the ID number of the worker
	 * @return 0 if the staff is not booked while 1 if he/she is booked
	 */
	int checkStaff(String staffID){
		int flag = 0;
		Vector<Appointment> temp = new GetAllBooking().getAllBook();
	
		for(int i =0;i<temp.size();i++){
		
			if(temp.get(i).getSpectID().equals(staffID)){
				flag = 1;
				break;
			}
		}
		if(staffID.charAt(0)=='M')
			flag = 2;
		return flag;
	}
}