import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * This is the panel for maintain the information of rooms
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Room_Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	Vector<String> roomColumn = new Vector<String>();
	Vector<Vector<String>> roomRow = new Vector<Vector<String>>();
	private JTable jTable_room = null;
	private JButton jButton_addroom = null;
	private JButton jButton_deleteroom = null;
	private JPanel jPanel_button = null;
	private JScrollPane jScrollPane = null;
		
	/**
	 * This is the default constructor
	 */
	public Room_Panel() {
		super();
		initialize();
		
		//Add Room Button
		Room_Registration.Confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int validRoom = 0;
				int exist = 0;
				
				try {
					Integer.parseInt(Room_Registration.jtf_roomNum.getText());
					
				} catch (NumberFormatException ee) {
					
					validRoom = 1;
				}
			
				Vector<String> room = new Room().getRoom();
				
				for(int i=0;i<room.size();i++){
					
					if(room.get(i).equals(Room_Registration.jtf_roomNum.getText())){						
						exist=1;
						break;
					}						
				}

				if (Room_Registration.jtf_roomNum.getText().length()==0||validRoom == 1||Room_Registration.jtf_roomNum.getText().length()!=3){
				
					JOptionPane.showMessageDialog(null, "Invalid Room Number!", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}	
				
				else if (exist==1){	
					String s = "The Room "+Room_Registration.jtf_roomNum.getText()+" has been registered!";
					JOptionPane.showMessageDialog(null, s, "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else{
					new Room().addUpdateFile(Room_Registration.jtf_roomNum.getText());
					displayStaff();
					Room_Registration.jtf_roomNum.setText("");
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
			jScrollPane.setViewportView(getJTable_room());

		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable_room
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTable_room() {
		roomColumn.add("Room Number");
	
		Vector<String> room = new Room().getRoom();
		roomRow.clear();
		for (int i=0; i<new Room().getRoomNo(); i++){
			
			Vector<String> row = new Vector<String>();
			row.add(room.get(i));
			
			roomRow.add(row);
		}
		if (jTable_room == null) {
			jTable_room = new JTable(new AllTableModel(roomRow, roomColumn));
			jTable_room.setShowGrid(true);
			jTable_room.setRowHeight(30);
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jTable_room.setDefaultRenderer(Object.class,  r); 

		}
		
		SwingUtilities.updateComponentTreeUI(jTable_room);
		return jTable_room;
	}

	/**
	 * This method initializes jPanel_button
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_button() {
		if (jPanel_button == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(15);
			flowLayout.setVgap(80);
			jPanel_button = new JPanel();
			jPanel_button.setLayout(flowLayout);
			jPanel_button.setPreferredSize(new Dimension(200, 530));
			jPanel_button.add(getJButton_addroom(), null);
			jPanel_button.add(getJButton_deleteroom(), null);
		}
		return jPanel_button;
	}

	/**
	 * This method initializes jButton_addroom
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_addroom() {
		if (jButton_addroom == null) {
			jButton_addroom = new JButton();
			jButton_addroom.setText("Add Room");
			jButton_addroom.setPreferredSize(new Dimension(173, 28));
			jButton_addroom.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new Room_Registration().setVisible(true);
				}
			});
		}
		return jButton_addroom;
	}

	
	
	/**
	 * This method initializes jButton_deleteroom
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_deleteroom() {
		if (jButton_deleteroom == null) {
			jButton_deleteroom = new JButton();
			jButton_deleteroom.setText("Remove Room");
			jButton_deleteroom.setPreferredSize(new Dimension(173, 28));
		}	
		// Display the detailed information of the book selected	
            jButton_deleteroom.addActionListener(new java.awt.event.ActionListener() {
    				
            	public void actionPerformed(java.awt.event.ActionEvent e) {
            		
            		try{
    				Vector<String> tempRoom = new Room().getRoom();
    	            int selectedRow = jTable_room.getSelectedRow();
    	            String selectRoom = tempRoom.get(selectedRow);
    	            int flag = checkRoom(selectRoom);//Whether the room is booked, 1--booked,0--Not booked
    	            	
    	            if(flag==1){	            		
    	            	JOptionPane.showMessageDialog(null, "This room has been booked!", "Warning", JOptionPane.INFORMATION_MESSAGE);
    	            }       	
    	            else{	
    	            	tempRoom.remove(selectedRow);
    	            	new Room().removeUpdateFile(tempRoom);      
    	            	displayStaff();
    	            } 
            		}
            		catch(Exception ex){
    	            }
            	}	
        });
	return jButton_deleteroom;
	}

	/**
	 * If refresh the jTable_room table 
	 */
	public void displayStaff(){
		roomRow.clear();
		Vector<String> tempRoom = new Room().getRoom();
    	for (int i=0; i<tempRoom.size(); i++){		
    		Vector<String> row = new Vector<String>();
    		row.add(tempRoom.get(i));
    		roomRow.add(row);
    	}
    	SwingUtilities.updateComponentTreeUI(jTable_room);
	}
	
	//Check whether the room is booked
	/**
	 * Returns 1 if the room is booked
	 * @param roomNo room number
	 * @return 0 if the room is not booked while 1 if the room is booked
	 */
	int checkRoom(String roomNo){
		
		int flag = 0;
		
		Vector<Appointment> temp = new GetAllBooking().getAllBook();	
		for(int i =0;i<temp.size();i++){		
			if(temp.get(i).getRoomNo().equals(roomNo)){			
				flag = 1;
				break;
			}	
		}
		return flag;
	}
	
}
