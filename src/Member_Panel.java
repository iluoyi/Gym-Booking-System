import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * This is the panel for maintain member's profile, including reading
 * in member's information, his/her history appointments and some other
 * details
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Member_Panel extends JPanel{

	private static final long serialVersionUID = 1L;

	final static JTextField memberID = new JTextField("");
	JTextField memberName = new JTextField("");
	JTextField memberGender = new JTextField("");
	JTextField memberPhone = new JTextField("");
	JTextField memberBirth = new JTextField("");
	JTextArea memberNotes = new JTextArea("");
	JTable memberAppointment;
	JTextArea appointmentDetails = new JTextArea("");
	Vector<String> tableColumn = new Vector<String>();
	static Vector<Vector<String>> tableRow = new Vector<Vector<String>>();
	JTextField memberInput= new JTextField("");
	JButton memberLogin = new JButton("Member Check in");
	JButton memberMaintain = new JButton("Maintain Member Profile");
	JButton creatMember = new JButton("Creat New Member");
	JButton memberLogout = new JButton("Member Check out");
	JButton refresh = new JButton("Refresh");
	
	String[] memberInfo = new String[5];
	String[] bookInfo = new String[20];

	/**
	 * Returns the current member's ID number 
	 * @return String current member's ID
	 */
	public static String getCurrentMemberID(){
		return memberID.getText();
	}
	
	/**
	 * The default constructor
	 */
	public Member_Panel(){
		setSize(780,460);
		setLayout(null);

		//put in the information of member
		Font labelFont = new Font("Arial", Font.PLAIN, 15 );
		int lap = 50;
		JLabel memberID_Label = new JLabel("Member ID");
		memberID_Label.setFont(labelFont);
		memberID_Label.setBounds(10, 25, 80, 15);
		add(memberID_Label);
		memberID.setBounds(120, 20, 160, 25);
		memberID.setEditable(false);
		add(memberID);

		JLabel memberName_Label = new JLabel("Name");
		memberName_Label.setFont(labelFont);
		memberName_Label.setBounds(10, 25+lap, 80, 15);
		add(memberName_Label);
		memberName.setBounds(120, 20+lap, 160, 25);
		memberName.setEditable(true);
		add(memberName);

		JLabel memberGender_Label = new JLabel("Gender");
		memberGender_Label.setFont(labelFont);
		memberGender_Label.setBounds(10, 25+lap*2, 80, 15);
		add(memberGender_Label);
		memberGender.setBounds(120, 20+lap*2, 160, 25);
		memberGender.setEditable(true);
		add(memberGender);

		JLabel memberPhone_Label = new JLabel("Phone Number");
		memberPhone_Label.setFont(labelFont);
		memberPhone_Label.setBounds(10, 25+lap*3, 100, 15);
		add(memberPhone_Label);
		memberPhone.setBounds(120, 20+lap*3, 160, 25);
		memberPhone.setEditable(true);
		add(memberPhone);

		JLabel memberBirth_Label = new JLabel("Date of Birth");
		memberBirth_Label.setFont(labelFont);
		memberBirth_Label.setBounds(10, 25+lap*4, 100, 15);
		add(memberBirth_Label);
		memberBirth.setBounds(120, 20+lap*4, 160, 25);
		memberBirth.setEditable(true);
		add(memberBirth);

		JLabel memberNote_Label = new JLabel("Notes");
		memberNote_Label.setFont(labelFont);
		memberNote_Label.setBounds(10, 25+lap*5, 100, 15);
		add(memberNote_Label);
		memberNotes.setSize(150, 220);
		memberNotes.setLineWrap(true);
		memberNotes.setEditable(true);
		JScrollPane notesPane = new JScrollPane(memberNotes);
		notesPane.setBackground(Color.white);
		notesPane.setBounds(120, 20+lap*5, 160, 220);
		add(notesPane);
		
		//Now put in the table
		JLabel memberAppointment_Label = new JLabel("The member's appoinments:");
		memberAppointment_Label.setFont(labelFont);
		memberAppointment_Label.setBounds(300, 20, 400, 20);
		add(memberAppointment_Label);
		
		tableColumn.add("Date");
		tableColumn.add("Time-Period");


		for (int i=0; i<10; i++){
			Vector<String> row = new Vector<String>();
			row.add("");
			row.add("");
			tableRow.add(row);
		}

		memberAppointment = new JTable(new AllTableModel(tableRow, tableColumn));
		memberAppointment.setRowHeight(30);
		memberAppointment.setRowSelectionAllowed(true);
		memberAppointment.setSelectionBackground(Color.lightGray);
		memberAppointment.setSelectionForeground(Color.white);
		memberAppointment.setGridColor(Color.black);
		memberAppointment.setShowGrid(true);
		memberAppointment.setShowHorizontalLines(true);
		memberAppointment.setShowVerticalLines(true);
		memberAppointment.setBackground(Color.white);
	    JScrollPane tablePane = new JScrollPane(memberAppointment);
	    tablePane.setBackground(Color.white);
	    tablePane.setBounds(300, 45, 350, 200);
	    add(tablePane);
	    
	    //put in the details of appointments
		JLabel appointmentDetails_Label = new JLabel("Details of appoinments:");
		appointmentDetails_Label.setFont(labelFont);
		appointmentDetails_Label.setBounds(300, 250, 400, 20);
		add(appointmentDetails_Label);
		
		appointmentDetails.setSize(150, 220);
		appointmentDetails.setLineWrap(true);
		appointmentDetails.setEditable(true);
		JScrollPane detailsPane = new JScrollPane(appointmentDetails);
		detailsPane.setBackground(Color.white);
		detailsPane.setBounds(300, 275, 350, 215);
		add(detailsPane);

	    //Put in the buttons and some operational text fields
		JLabel ID_Label = new JLabel("Member ID");
		ID_Label.setFont(labelFont);
		ID_Label.setBounds(680, 50, 80, 15);
		add(ID_Label);
		memberInput.setBounds(680, 80, 180, 30);
		add(memberInput);

		memberLogin.setBounds(680, 200, 180, 30);
		creatMember.setBounds(680, 250, 180, 30);
		memberMaintain.setBounds(680, 300, 180, 30);
		memberLogout.setBounds(680, 350, 180, 30);
		refresh.setBounds(570, 16, 80, 25);
		add(memberLogin);
		add(creatMember);
		add(memberMaintain);
		add(memberLogout);
		add(refresh);

		//add listener to the "create member" button
		creatMember.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Member_Registration().setVisible(true);
			}
		});
		//Enter member ID and display profile
		refresh.addMouseListener(new MouseAdapter(){

			//Check the account
			public void mouseClicked(MouseEvent e){			
				refreshTable();
			}
			
		});
		
		//for member login button
		memberLogin.addMouseListener(new MouseAdapter(){

			//Check the account
			public void mouseClicked(MouseEvent e){
			
			if(memberInput.getText().length()==0){
				
				JOptionPane.showMessageDialog(null, "Please input the member ID!", "Warning", JOptionPane.INFORMATION_MESSAGE);
			}	
			else{
				String currentMem = memberInput.getText();
				Member gm = new Member();
			Boolean exist = gm.getMemberInfo(currentMem, memberInfo);
			
			if(exist){
                 
				appointmentDetails.setText("");
				memberID.setText(currentMem);
				memberName.setText(memberInfo[0]);
				memberGender.setText(memberInfo[1]);
				memberPhone.setText(memberInfo[2]);
				memberBirth.setText(memberInfo[3]);
				memberNotes.setText(memberInfo[4]);
				//Display booking information
				
				refreshTable();
				
				// Display the detailed information of the book selected
				memberAppointment.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	                
	                public void valueChanged(ListSelectionEvent e){
	                	
	                	
	                	Vector<Appointment> tempBookApp = UserInterface.getCurrentApp();
	                	int selectedRow = memberAppointment.getSelectedRow();
	                	String room = null;
	                	String spectID = null;
	                	for (int i=0; i<tempBookApp.size(); i++){
	                		if ((tempBookApp.get(i).getData().equals((String) memberAppointment.getValueAt(selectedRow, 0)))
	                			&& (tempBookApp.get(i).getTimePeriod().equals((String) memberAppointment.getValueAt(selectedRow, 1)))
	                			&& (tempBookApp.get(i).getMemberID().equals(memberID.getText()))){
	                			room = tempBookApp.get(i).getRoomNo();
	    	                	spectID = tempBookApp.get(i).getSpectID();
	                		}
	                	}
         	
	                	Staff tempSpect = new Staff(spectID);          
	                	String detailBookInfo = tempSpect.toString();
	                	
	                	if (!room.equals("aaa")){
	                		
	                		detailBookInfo += "    Room Number: ";
	                		detailBookInfo += room;
	                	}
	                	appointmentDetails.setEditable(false);
	                	appointmentDetails.setText(detailBookInfo);
	                	appointmentDetails.setFont(new Font("Arial",0,15));
	                }  
	            });
			}
			}
			}
		});

		//for maintain button
		memberMaintain.addMouseListener(new MouseAdapter(){

			//Check the account
			public void mouseClicked(MouseEvent e){

				String[] existMemberInfo = {"","","","",""};
				String currentMem = memberInput.getText();
				new Member().getMemberInfo(currentMem, existMemberInfo);
				
				memberInfo[0]= memberName.getText();
				memberInfo[1]= memberGender.getText();
				memberInfo[2]= memberPhone.getText();
	        	memberInfo[3]= memberBirth.getText();
	        	memberInfo[4]= memberNotes.getText();

	        	Boolean flag = true;
				int validName = 0;
				int validPhone = 0;
				int validDob = 0;
				
				for(int i = 0;i<4;i++){
					
					if(memberInfo[i].length()==0){
						
						flag = false;
						break;
					}
				}
				
			   	
				for(int i=0;i<memberInfo[0].length();i++){
					
					if(!(memberInfo[0].charAt(i)>='A'&&memberInfo[0].charAt(i)<='Z')&&!(memberInfo[0].charAt(i)>='a'&&memberInfo[0].charAt(i)<='z')){
						validName = 1;
						if(memberInfo[0].charAt(i)==' ')
							validName = 0;
					}				
				}
				
				for(int j=0;j<memberInfo[2].length();j++){
					
					if(!(memberInfo[2].charAt(j)>='0'&&memberInfo[2].charAt(j)<='9')){
						validPhone = 1;
						break;
						
					}				
				}
				
				for(int m=0;m<memberInfo[3].length();m++){
					
					if(!(memberInfo[3].charAt(m)>='0'&&memberInfo[3].charAt(m)<='9')){
						validDob = 1;
					}
					if((m==4||m==7)&&memberInfo[3].charAt(4)=='.'&&memberInfo[3].charAt(7)=='.'){
							validDob = 0;
				
					}				
				}
				if(memberInfo[3].length()!=10)
					validDob = 1;
				
				else if(memberInfo[3].charAt(4)!='.'||memberInfo[3].charAt(7)!='.')
					validDob = 1;
				
				if(!flag){
					
					JOptionPane.showMessageDialog(null, "The information is not complete!", "Warning", JOptionPane.INFORMATION_MESSAGE);
					memberName.setText(existMemberInfo[0]);
					memberGender.setText(existMemberInfo[1]);
					memberPhone.setText(existMemberInfo[2]);
					memberBirth.setText(existMemberInfo[3]);
				}
				else if(validName == 1){
					
					JOptionPane.showMessageDialog(null, "The Name must contain letters only !", "Warning", JOptionPane.INFORMATION_MESSAGE);
					memberName.setText(existMemberInfo[0]);
					
				}
				else if (!memberInfo[1].equals("Female")&&!memberInfo[1].equals("Male")){
					
					JOptionPane.showMessageDialog(null, "The Gender must be Female or Male only !", "Warning", JOptionPane.INFORMATION_MESSAGE);
					
					memberGender.setText(existMemberInfo[1]);
					
				}
				else if(validPhone == 1){
					
					JOptionPane.showMessageDialog(null, "The Phone must contain digits only!", "Warning", JOptionPane.INFORMATION_MESSAGE);
					
					memberPhone.setText(existMemberInfo[2]);
					
				}
				else if(validDob == 1){
					
					JOptionPane.showMessageDialog(null, "The Date of birth must be in the format: eg.1991.02.06 !", "Warning", JOptionPane.INFORMATION_MESSAGE);
					
					memberBirth.setText(existMemberInfo[3]);
				}
			
				else{
	        	
	        	
	        	ChangeMember gm = new ChangeMember();
	        	gm.changeMemberInfo(currentMem,memberInfo);
	        	
	        	JOptionPane.showMessageDialog(null, "The maintainance succeeds!", "Warning", JOptionPane.INFORMATION_MESSAGE);
			}
			}
		});
		
		//add listener to the logout button
		memberLogout.addMouseListener(new MouseAdapter(){

			//Check the account
			public void mouseClicked(MouseEvent e){

				memberInput.setText("");
				memberID.setText("");
				memberName.setText("");
				memberGender.setText("");
				memberPhone.setText("");
				memberBirth.setText("");
				memberNotes.setText("");
				appointmentDetails.setText("");
				
				tableRow.clear();
				SwingUtilities.updateComponentTreeUI(memberAppointment);
			}
		});
		
		//Clear some components when the user logout
		 StatusBar.jbt_logout.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
				 memberInput.setText("");
					memberID.setText("");
					tableRow.clear();
					SwingUtilities.updateComponentTreeUI(memberAppointment);
			 }
		 });
	}
	
	/**
	 * To refresh the table of appointments
	 */
	public void refreshTable(){
		Vector<Appointment> tempApp = UserInterface.getCurrentApp();
		tableRow.clear();
		
		for (int i=0; i < tempApp.size(); i++){
			String currentMem = memberInput.getText();
			if(tempApp.get(i).getMemberID().equals(currentMem)){
				Vector<String> temp = new Vector<String>();
				temp.add(tempApp.get(i).getData());
				temp.add(tempApp.get(i).getTimePeriod());
				tableRow.add(temp);			
			}		
		}		
		SwingUtilities.updateComponentTreeUI(memberAppointment);	
	}
}
