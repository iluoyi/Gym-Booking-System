import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * This is the registration frame for members
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Member_Registration extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel_button = null;
	private JTextField jtf_memberID = new JTextField(10);
	private JTextField jtf_name = new JTextField(10);
	private JComboBox jComboBox_gender = new JComboBox(new String[]{"Male                 ", "Female              "});
	private JTextField jtf_dob = new JTextField(10);
	private JTextField jtf_phone = new JTextField(10);
	private JButton confirm = null;
	private JButton cancel = null;

	String[] newInfo = new String[4];
	String newMem = new GenerateMemID().getMemID();

	/**
	 * This method initializes jPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(100);
			flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
			flowLayout.setVgap(20);
			
			jtf_memberID.setText(newMem);
			jtf_memberID.setEditable(false);
			JSplitPane jsp_memberID = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Member ID"),jtf_memberID);
			JSplitPane jsp_name = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Name"),jtf_name);
			JSplitPane jsp_gender = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Gender"),jComboBox_gender);
			JSplitPane jsp_dob = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Date of Birth"),jtf_dob);
			JSplitPane jsp_phone = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,
					new JLabel("  Phone"),jtf_phone);

			jPanel = new JPanel();
			jPanel.setLayout(flowLayout);
			jPanel.add(jsp_memberID, null);
			jPanel.add(jsp_name, null);
			jPanel.add(jsp_gender, null);
			jPanel.add(jsp_phone, null);
			jPanel.add(jsp_dob, null);

		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel_button
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_button() {
		if (jPanel_button == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setVgap(15);
			flowLayout1.setHgap(15);
			jPanel_button = new JPanel();
			jPanel_button.setLayout(flowLayout1);
			jPanel_button.add(getConfirm(), null);
			jPanel_button.add(getCancel(), null);
		}
		return jPanel_button;
	}

	/**
	 * This method initializes confirm
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getConfirm() {
		if (confirm == null) {
			confirm = new JButton();
			confirm.setText("Confirm");
			confirm.setMnemonic(KeyEvent.VK_ENTER);

			confirm.addMouseListener(new MouseAdapter(){

				//Check the account
				public void mouseClicked(MouseEvent e){

					

					newInfo[0]= jtf_name.getText();
					newInfo[1]= jComboBox_gender.getSelectedItem().toString();
					newInfo[3]= jtf_dob.getText();
					newInfo[2]= jtf_phone.getText();
					Boolean flag = true;
					int validName = 0;
					int validPhone = 0;
					int validDob = 0;
					
					for(int i = 0;i<4;i++){
						
						if(newInfo[i].length()==0){
							
							flag = false;
							break;
						}
					}
					
				   	
					for(int i=0;i<newInfo[0].length();i++){
						
						if(!(newInfo[0].charAt(i)>='A'&&newInfo[0].charAt(i)<='Z')&&!(newInfo[0].charAt(i)>='a'&&newInfo[0].charAt(i)<='z')){
							validName = 1;
							if(newInfo[0].charAt(i)==' ')
								validName = 0;
						}				
					}
					
					for(int j=0;j<newInfo[2].length();j++){
						
						if(!(newInfo[2].charAt(j)>='0'&&newInfo[2].charAt(j)<='9')){
							validPhone = 1;
							break;
							
						}				
					}
					
					if(newInfo[3].length()!=10)
						validDob = 1;
					
					else if(newInfo[3].charAt(4)!='.'||newInfo[3].charAt(7)!='.')
						validDob = 1;
						
					for(int m=0;m<newInfo[3].length();m++){
						
						if(!(newInfo[3].charAt(m)>='0'&&newInfo[3].charAt(m)<='9')){
							validDob = 1;
							if((m==4||m==7)&&newInfo[3].charAt(4)=='.'&&newInfo[3].charAt(7)=='.')
								validDob = 0;
						}				
					}
					
					if(!flag){
						
						JOptionPane.showMessageDialog(null, "The information is not complete!", "Warning", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(validName == 1){
						
						JOptionPane.showMessageDialog(null, "The Name must contain letters only !", "Warning", JOptionPane.INFORMATION_MESSAGE);
						jtf_name.setText("");
						
					}
					else if(validPhone == 1){
						
						JOptionPane.showMessageDialog(null, "The phone must contain digits only!", "Warning", JOptionPane.INFORMATION_MESSAGE);
						jtf_phone.setText("");
					}
					else if(validDob == 1){
						
						JOptionPane.showMessageDialog(null, "The Date of birth must be in the format: eg.1991.02.06 !", "Warning", JOptionPane.INFORMATION_MESSAGE);
						jtf_dob.setText("");
					}
				
					else{
						CreateMember cm = new CreateMember();
						cm.createMemberInfo(newMem, newInfo);
						setVisible(false);
					}
				}
			});
		}
		return confirm;
	}

	/**
	 * This method initializes cancel
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getCancel() {
		if (cancel == null) {
			cancel = new JButton();
			cancel.setText("Cancel");
			cancel.setMnemonic(KeyEvent.VK_ESCAPE);
			cancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return cancel;
	}

	/**
	 * constructor
	 */
	public Member_Registration() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setBounds(new Rectangle(0, 0, 400, 350));
		this.setTitle("Member Registration");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null){
			jContentPane = new JPanel();
			Label label = new Label(" Please input the information of the member.");
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(label, BorderLayout.NORTH);
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
			jContentPane.add(getJPanel_button(), BorderLayout.SOUTH);
	
		}
		return jContentPane;
	}
}
