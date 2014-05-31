import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 *This class generates the widely used status bar at the bottom of each 
 *interface for users.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class StatusBar extends JSplitPane {

	private static final long serialVersionUID = 1L;

	public JLabel jLabel_userid = null;
	public JLabel jLabel_identity = null;
	private JTextField jTextField_time = null;
	public static JButton jbt_logout = new JButton("Logout");
	public static JButton jbt_exit = new JButton("Exit");

	/**
	 * This method initializes StatusBar
	 *
	 * 
	 */
	public StatusBar(){
		this.setResizeWeight(0.5D);
		this.setDividerSize(6);
		this.setPreferredSize(new Dimension(780, 30));
		this.setLeftComponent(getJSplitPane_status1());
		this.setRightComponent(getJSplitPane_status2());
		this.setContinuousLayout(true);
	}
	
	/**
	 *  This method initializes jbt_logout
	 * @return jbt_logout
	 */
	public JButton getJbt_Logout(){
		return jbt_logout;
	}
	/**
	 *   This method initializes jbt_exit
	 * @return jbt_exit
	 */
	public JButton getJbt_Exit(){
		return jbt_exit;
	}
	
	/**
	 * This method initializes jSplitPane_status1
	 *
	 * @return javax.swing.JSplitPane
	 */
	private JSplitPane getJSplitPane_status1() {
			JSplitPane jSplitPane_status1 = new JSplitPane();
			jSplitPane_status1.setResizeWeight(0.4D);
			jSplitPane_status1.setDividerSize(4);
			jSplitPane_status1.setRightComponent(getJSplitPane_position());
			jSplitPane_status1.setLeftComponent(getJSplitPane_userid());
		return jSplitPane_status1;
	}

	/**
	 * This method initializes jSplitPane_status2
	 *
	 * @return javax.swing.JSplitPane
	 */
	private JSplitPane getJSplitPane_status2() {
			JSplitPane jSplitPane_status2 = new JSplitPane();
			jSplitPane_status2.setResizeWeight(0.6D);
			jSplitPane_status2.setDividerSize(6);
			JPanel temp = new JPanel(new GridLayout(1,2));
			temp.add(jbt_logout);
			temp.add(jbt_exit);
			jSplitPane_status2.setRightComponent(temp);
			jSplitPane_status2.setLeftComponent(getJTextField_time());
		return jSplitPane_status2;
	}

	/**
	 * This method initializes jSplitPane_userid
	 *
	 * @return javax.swing.JSplitPane
	 */
	private JSplitPane getJSplitPane_userid() {
			JSplitPane jSplitPane_userid = null;
			JLabel jLabel_id = null;
			jLabel_userid = new JLabel();
			jLabel_userid.setText(Login_UI.userID.getText());
			jLabel_id = new JLabel();
			jLabel_id.setText(" Current User:");
			jSplitPane_userid = new JSplitPane();
			jSplitPane_userid.setResizeWeight(0.1D);
			jSplitPane_userid.setDividerSize(3);
			jSplitPane_userid.setLeftComponent(jLabel_id);
			jSplitPane_userid.setRightComponent(jLabel_userid);
		return jSplitPane_userid;
	}

	/**
	 * This method initializes jSplitPane_postion
	 *
	 * @return javax.swing.JSplitPane
	 */
	private JSplitPane getJSplitPane_position() {
			JSplitPane jSplitPane_position = null;
			JLabel jLabel_position = null;
			jLabel_identity = new JLabel();
			jLabel_identity.setText("");
			jLabel_position = new JLabel();
			jLabel_position.setText("  Identity:");
			jSplitPane_position = new JSplitPane();
			jSplitPane_position.setResizeWeight(0.1D);
			jSplitPane_position.setDividerSize(3);
			jSplitPane_position.setRightComponent(jLabel_identity);
			jSplitPane_position.setLeftComponent(jLabel_position);
		return jSplitPane_position;
	}

	/**
	 * This method initializes jTextField_time
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField_time() {
		if (jTextField_time == null) {
			jTextField_time = new JTextField();
			Timer timer = new Timer(1000,  new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					jTextField_time.setText( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				}
			});
			timer.start();
			jTextField_time.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return jTextField_time;
	}

}
