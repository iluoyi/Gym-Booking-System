import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.*;
/**
 * This is the registration frame for staff
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Staff_Registration extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel_register = null;
	static JTextField jtf_staffID = new JTextField(10);
	static JTextField jtf_name = new JTextField(10);
	static JPasswordField jpf_pw = new JPasswordField(10);
	static JPasswordField jpf_pw2 = new JPasswordField(10);
	private JComboBox jComboBox_id = new JComboBox(new String[]{"Manager","Receptionist",
            								"Trainer","Physiotherapist","Nutritionist"});
	static JComboBox jComboBox_gender = new JComboBox(new String[]{"Male                 ", "Female              "});
	private JPanel jPanel_button = null;
	private JButton jButton_cancel = null;
	public static JButton jButton_register = new JButton();
	/**
	 * This method initializes jPanel_register
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_register() {
		if (jPanel_register == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(94);
			flowLayout.setVgap(9);
			flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);

			jtf_staffID.setEditable(false);	
			//Initial value of Staff ID of Manager type
			if(jComboBox_id.getSelectedItem().equals("Manager")){
				
				Vector<String> temp = new StaffAccount().getManager();
				int id = Integer.parseInt(temp.get((temp.size()-1)).substring(1, 4)) + 1;
				
				String newID = null;
				
				if(id < 10)
					newID = "M00"+id;
				else if(id > 99)
					newID = "M"+id;
				else
					newID = "M0"+id;
				
				jtf_staffID.setText(newID);
							
			}	
			JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Staff ID"),jtf_staffID);
			JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Password"),jpf_pw);
			JSplitPane jSplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Password again"),jpf_pw2);
			JSplitPane jSplitPane3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Identity"),jComboBox_id);
			JSplitPane jSplitPane4 =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Name"),jtf_name);
			JSplitPane jSplitPane5 =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Gender"),jComboBox_gender);

			jPanel_register = new JPanel();
			jPanel_register.setLayout(flowLayout);
			jPanel_register.add(jSplitPane3, null);
			jPanel_register.add(jSplitPane, null);
			jPanel_register.add(jSplitPane4, null);
			jPanel_register.add(jSplitPane5, null);
			jPanel_register.add(jSplitPane1, null);
			jPanel_register.add(jSplitPane2, null);
			
			
			//Add listener to the ComboBox of Identity
			jComboBox_id.addItemListener(new ItemListener(){
				
				public void itemStateChanged(ItemEvent e){
					setNewID(jComboBox_id.getSelectedIndex());
				}		
			});
			
		}
		return jPanel_register;
	}

	/**
	 * This method generates new ID
	 *
	 * @param selectedItem the item (type of worker) that was selected 
	 */
	private void setNewID(int index){
		
		if(index == 0){     //Manager
			
			Vector<String> temp = new StaffAccount().getManager();
			int id = Integer.parseInt(temp.get((temp.size()-1)).substring(1, 4)) + 1;
			String newID = null;
			
			if(id < 10)
				newID = "M00"+id;
			else if(id > 99)
				newID = "M"+id;
			else
				newID = "M0"+id;
			
			jtf_staffID.setText(newID);
			repaint();
			
		}
		else if(index == 1){
			
			Vector<String> temp = new StaffAccount().getRecep();
			int id = 0;
			if(temp.size()==0)
				id = 1;
			else
			    id = Integer.parseInt(temp.get((temp.size()-1)).substring(1, 4)) + 1;
				
			String newID = null;
			
			if(id < 10)
				newID = "R00"+id;
			else if(id > 99)
				newID = "R"+id;
			else
				newID = "R0"+id;
			
			jtf_staffID.setText(newID);
			repaint();
			
		}
		else if(index == 2){
			
			Vector<String> temp = new StaffAccount().getTra();
			int id = 0;
			if(temp.size()==0)
				id = 1;
			else
			    id = Integer.parseInt(temp.get((temp.size()-1)).substring(1, 4)) + 1;
	
			
			String newID = null;
			
			if(id < 10)
				newID = "T00"+id;
			else if(id > 99)
				newID = "T"+id;
			else
				newID = "T0"+id;
			
			jtf_staffID.setText(newID);
			repaint();
			
		}
		
		else if(index == 3){
			
			Vector<String> temp = new StaffAccount().getPhy();
			int id = 0;
			if(temp.size()==0)
				id = 1;
			else
			    id = Integer.parseInt(temp.get((temp.size()-1)).substring(1, 4)) + 1;
	
			
			String newID = null;
			
			if(id < 10)
				newID = "P00"+id;
			else if(id > 99)
				newID = "P"+id;
			else
				newID = "P0"+id;
			
			jtf_staffID.setText(newID);
			repaint();
			
		}
		
		else {
			
			Vector<String> temp = new StaffAccount().getNutr();
			int id = 0;
			if(temp.size()==0)
				id = 1;
			else
			    id = Integer.parseInt(temp.get((temp.size()-1)).substring(1, 4)) + 1;
	
			
			String newID = null;
			
			if(id < 10)
				newID = "N00"+id;
			else if(id > 99)
				newID = "N"+id;
			else
				newID = "N0"+id;
			
			jtf_staffID.setText(newID);
			repaint();
			
		}
		
	}
	
	/**
	 * This method initializes jPanel1
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel1() {
		if (jPanel_button == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setHgap(23);
			flowLayout1.setVgap(13);
			jPanel_button = new JPanel();
			jPanel_button.setLayout(flowLayout1);
			jPanel_button.add(getJButton_register(), null);
			jPanel_button.add(getJButton_cancel(), null);
		}
		return jPanel_button;
	}

	/**
	 * This method initializes jButton_register
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_register() {
			jButton_register.setText("Register");
			jButton_register.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);				
				}
			});
		return jButton_register;
	}
	/**
	 * This method initializes jButton_cancel
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_cancel() {
		if (jButton_cancel == null) {
			jButton_cancel = new JButton();
			jButton_cancel.setText("Cancel");
			jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jpf_pw.setText("");
					jpf_pw2.setText("");
					jtf_name.setText("");
					setVisible(false);
				}
			});
		}
		return jButton_cancel;
	}

	/**
	 * Default constructor
	 * 
	 */
	public Staff_Registration() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 330);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Staff Registration");
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			Label label = new Label(" Please input the information of the staff.");
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(label, BorderLayout.NORTH);
			jContentPane.add(getJPanel_register(), BorderLayout.CENTER);
			jContentPane.add(getJPanel1(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

}
