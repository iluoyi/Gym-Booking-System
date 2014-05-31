import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This is the registration frame for rooms
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Room_Registration extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	static JTextField jtf_roomNum = null;
	private JButton Cancel = null;
	private JPanel jPanel = null;
	static JButton Confirm = new JButton();
	/**
	 * This method initializes jtf_roomNum
	 *
	 * @return javax.swing.JTextField
	 */
	public JTextField getJtf_roomNum() {
		if (jtf_roomNum == null) {
			jtf_roomNum = new JTextField();
			jtf_roomNum.setColumns(10);
		}
		return jtf_roomNum;
	}
	/**
	 * This method initializes Confirm
	 *
	 * @return javax.swing.JButton
	 */
	 JButton getConfirm() {
			Confirm.setText("Confirm");
			Confirm.setMnemonic(KeyEvent.VK_ENTER);
			Confirm.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
				}
			});	
		return Confirm;
	}
	/**
	 * This method initializes Cancel
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getCancel() {
		if (Cancel == null) {
			Cancel = new JButton();
			Cancel.setText("Cancel");
			Cancel.setMnemonic(KeyEvent.VK_ESCAPE);
			Cancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jtf_roomNum.setText("");
					setVisible(false);
				}
			});
		}
		return Cancel;
	}
	/**
	 * This method initializes jPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new FlowLayout());
			jPanel.add(getConfirm(), null);
			jPanel.add(getCancel(), null);
		}
		return jPanel;
	}

	
	
	/**
	 * 
	 */
	public Room_Registration() {
		super();
		initialize();
		
	}
	/**
	 * This method initializes this
	 *
	 * 
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setTitle("Room Registration");
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
		if (jContentPane == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(6);
			flowLayout.setVgap(40);
			jLabel = new JLabel();
			jLabel.setText("Room NO.");
			jContentPane = new JPanel();
			jContentPane.setLayout(flowLayout);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJtf_roomNum(), null);
			jContentPane.add(getJPanel(), null);
		}
		return jContentPane;
	}
}
