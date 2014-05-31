import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * This is the panel for checking appointments for a specialist.
 * Especially for physiotherapist, he/she is able to change the 
 * room according to his/her preference.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Appointment_Panel extends JPanel{

	private static final long serialVersionUID = 1L;
	JTextField memberID = new JTextField("");
	JTextField memberName = new JTextField("");
	JTextField memberGender = new JTextField("");
	JTextField memberPhone = new JTextField("");
	JTextField memberBirth = new JTextField("");
	JTextArea memberNotes = new JTextArea("");
	JTable memberAppointment;
	Vector<String> appColumn = new Vector<String>();
	Vector<Vector<String>> appRow = new Vector<Vector<String>>();

	JScrollPane notesPane = null;
	
	JButton changeRoom = new JButton("Change Room");
	JButton cancelChange = new JButton("Cancel");
	JButton makeFinalChange = new JButton("OK");
	JComboBox alternaltiveRoom = new JComboBox();
	/**
	 * This is constructor for the class.
	 */
	public Appointment_Panel(){
		setSize(780,460);
		setLayout(null);

		//put in the information of member
		Font labelFont = new Font("Arial", Font.PLAIN, 15 );
		int lap = 40;
		JLabel memberID_Label = new JLabel("Member ID");
		memberID_Label.setFont(labelFont);
		memberID_Label.setBounds(590, 25, 80, 15);
		add(memberID_Label);
		memberID.setBounds(700, 20, 160, 25);
		memberID.setEditable(false);
		add(memberID);

		JLabel memberName_Label = new JLabel("Name");
		memberName_Label.setFont(labelFont);
		memberName_Label.setBounds(590, 25+lap, 80, 15);
		add(memberName_Label);
		memberName.setBounds(700, 20+lap, 160, 25);
		memberName.setEditable(false);
		add(memberName);

		JLabel memberGender_Label = new JLabel("Gender");
		memberGender_Label.setFont(labelFont);
		memberGender_Label.setBounds(590, 25+lap*2, 80, 15);
		add(memberGender_Label);
		memberGender.setBounds(700, 20+lap*2, 160, 25);
		memberGender.setEditable(false);
		add(memberGender);

		JLabel memberPhone_Label = new JLabel("Phone Number");
		memberPhone_Label.setFont(labelFont);
		memberPhone_Label.setBounds(590, 25+lap*3, 100, 15);
		add(memberPhone_Label);
		memberPhone.setBounds(700, 20+lap*3, 160, 25);
		memberPhone.setEditable(false);
		add(memberPhone);

		JLabel memberBirth_Label = new JLabel("Date of Birth");
		memberBirth_Label.setFont(labelFont);
		memberBirth_Label.setBounds(590, 25+lap*4, 100, 15);
		add(memberBirth_Label);
		memberBirth.setBounds(700, 20+lap*4, 160, 25);
		memberBirth.setEditable(false);
		add(memberBirth);

		JLabel memberNote_Label = new JLabel("Notes");
		memberNote_Label.setFont(labelFont);
		memberNote_Label.setBounds(590, 25+lap*5, 100, 15);
		add(memberNote_Label);
		memberNotes.setSize(150, 220);
		memberNotes.setLineWrap(true);
		memberNotes.setEditable(false);
		notesPane = new JScrollPane(memberNotes);
		notesPane.setBackground(Color.white);
		notesPane.setBounds(700, 20+lap*5, 160, 270);
		add(notesPane);


		cancelChange.setBounds(590, 380, 270, 30);
		add(cancelChange);
		alternaltiveRoom.setBounds(590, 420, 120, 30);
		add(alternaltiveRoom);
		makeFinalChange.setBounds(750, 420, 110, 30);
		add(makeFinalChange);
		changeRoom.setBounds(590, 460, 270, 30);
		add(changeRoom);
		changeRoom.setVisible(false);
		makeFinalChange.setVisible(false);
		cancelChange.setVisible(false);
		alternaltiveRoom.setVisible(false);

		if (Login_UI.userID.getText().charAt(0)=='P') {modeForP();}
		
		//Now put in the table
		appColumn.add("Member ID");
		appColumn.add("Date");
		appColumn.add("Time");
		appColumn.add("Room NO.");

		memberAppointment = new JTable(new AllTableModel(appRow, appColumn));
		display();
		
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
	    tablePane.setBounds(20, 20, 550, 470);
	    add(tablePane);
	    
	    changeRoom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				transformForP();
			}
			});
	   
	  
	    cancelChange.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				modeForP();
			}
			});
	    
	    makeFinalChange.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (!alternaltiveRoom.getItemAt(0).equals("No room available")){
					HandleAppointment dealApp = new HandleAppointment();
					dealApp.changeAppRoom((String) memberAppointment.getValueAt(memberAppointment.getSelectedRow(), 0), (String) alternaltiveRoom.getSelectedItem(),(String) memberAppointment.getValueAt(memberAppointment.getSelectedRow(), 1),(String) memberAppointment.getValueAt(memberAppointment.getSelectedRow(), 2));
					
					JOptionPane.showMessageDialog(null, "Room changing succeeds !", "Warning", JOptionPane.INFORMATION_MESSAGE);
					display();
					alternaltiveRoom.removeAllItems();
					}
				}
			});
	    
	    //To immediately display the details of a selected appointment
	    memberAppointment.getSelectionModel().addListSelectionListener(new ListSelectionListener(){      
            public void valueChanged(ListSelectionEvent e){
            	      	
            	Vector<Appointment> tempBookApp = new GetSpectBook().getAllBook(UserInterface.getUserID());
            	int selectedRow = memberAppointment.getSelectedRow();
            	String memID = tempBookApp.get(selectedRow).getMemberID();
            	String[] memInfo = new String[5];
            	
            	new Member().getMemberInfo(memID,memInfo);
            	
            	memberID.setText(memID);
            	memberName.setText(memInfo[0]);
            	memberGender.setText(memInfo[1]);
            	memberPhone.setText(memInfo[2]);
            	memberBirth.setText(memInfo[3]);
            	memberNotes.setText(memInfo[4]);
            	
            	if (Login_UI.userID.getText().charAt(0)=='P'){
            		boolean roomFlag;
            		Vector<String> tempRoom = new Room().getRoom();
            		Vector<Appointment> tempApp = UserInterface.getCurrentApp();
            		
            		alternaltiveRoom.removeAllItems();
            		
            		for (int i=0; i<tempRoom.size(); i++){
            			roomFlag  = true;
            			for (int j=0; j<tempApp.size(); j++){
            				if (tempApp.get(j).getData().equals(tempBookApp.get(selectedRow).getData())
            						&& tempApp.get(j).getTimePeriod().equals(tempBookApp.get(selectedRow).getTimePeriod())){
            					if (tempRoom.get(i).equals(tempApp.get(j).getRoomNo())){
            						roomFlag = false;
            					}
            				}
            			}
            			if (roomFlag) {
            				alternaltiveRoom.addItem(tempRoom.get(i));
            			}
            		}
            		if (alternaltiveRoom.getItemCount()==0) alternaltiveRoom.addItem("No room available");
            	}
        
            }  
        	});
	    
	}
		/**
		 * It is used to generate the display mode for Physiotherapist
		 */
	    public void modeForP(){
			notesPane.setBounds(700, 20+40*5, 160, 230);
			changeRoom.setVisible(true);
			makeFinalChange.setVisible(false);
			cancelChange.setVisible(false);
			alternaltiveRoom.setVisible(false);
		}
		
	    /**
		 * It is used to generate the display mode for Physiotherapist to change to room
		 */
		public void transformForP(){
			notesPane.setBounds(700, 20+40*5, 160, 150);
			makeFinalChange.setVisible(true);
			cancelChange.setVisible(true);
			alternaltiveRoom.setVisible(true);		
		}
		
		/**
		 * This is used to refresh the member appointment table
		 */
		public void display(){
			Vector<Appointment> temp = new GetSpectBook().getAllBook(UserInterface.getUserID());
			appRow.clear();
			for (int i=0; i<temp.size(); i++){
				
				Vector<String> row = new Vector<String>();
				
				row.add(temp.get(i).getMemberID());
				row.add(temp.get(i).getData());
				row.add(temp.get(i).getTimePeriod());
				if(temp.get(i).getRoomNo().equals("aaa"))
					{row.add("");}
				else
					{row.add(temp.get(i).getRoomNo());}
				appRow.add(row);
			}
        	SwingUtilities.updateComponentTreeUI(memberAppointment);
		}
}