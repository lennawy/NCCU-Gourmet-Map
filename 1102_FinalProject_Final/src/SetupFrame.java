import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.sql.*;
public class SetupFrame extends JFrame{

	private JLabel personalinfo;
	private JLabel name,showname;
	private JLabel ID,showID;
	private JLabel password,showpassw;
	private JLabel phone,showphone;
	private JLabel comment;
	private JTextArea allcomments;
	private JButton cpassw,cphone;
	private JButton close;
	private Connection connect;
	private Statement stat;
	private JPanel showpanel, panel1,panel2,panel3,panel4,panel5,panel6,panel7;
	private LoginFrame login;
	private String user,id,pw,phon;
	private ChangePassword changepassword;
	private ChangePhone changephone;
	
	public SetupFrame(Connection conn,String name, String id, String pw, String phone) throws SQLException{
		setTitle("Set up");
		this.connect = conn;
		this.user=name;
		this.id=id;
		this.pw=pw;
		this.phon=phone;
		setSize(400,450);
		setVisible(true);
		CreateTitle();
		CreateLabel();
		CreateTextArea();
		CreateButton();
		CreateLayout();
	}
	public void CreateTitle() {
		personalinfo=new JLabel("Personal Infomation",JLabel.CENTER);
		Font f=new Font("Utopia",Font.ITALIC,25);
		personalinfo.setFont(f);
		
	}
	public void CreateLabel(){
		name=new JLabel("Name: ");
		showname=new JLabel();
		showname.setText(this.user);
		ID=new JLabel("StudentID: ");
		showID=new JLabel();
		showID.setText(this.id);
		password=new JLabel("Password: ");
		showpassw=new JLabel();
		showpassw.setText(this.pw);
		phone=new JLabel("Phone: ");
		showphone=new JLabel();
		showphone.setText(this.phon);
		comment=new JLabel("Comments: ");
	}
	public void CreateTextArea() {
		allcomments=new JTextArea(12,15);
		allcomments.setEditable(false);
	}
	public void CreateButton() throws SQLException{
		
		cpassw=new JButton("Change Password");
		cpassw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					changepassword=new ChangePassword(connect, showpassw);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cphone=new JButton("Change Phone");
		cphone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					changephone=new ChangePhone(connect, showphone);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		close=new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
	}
	public void CreateLayout() {
		showpanel=new JPanel(new GridLayout(7,1));
		
		panel1=new JPanel(new FlowLayout());
		panel1.add(name);
		panel1.add(showname);
		panel2=new JPanel(new FlowLayout());
		panel2.add(ID);
		panel2.add(showID);
		panel3=new JPanel(new FlowLayout());
		panel3.add(password);
		panel3.add(showpassw);
		panel3.add(cpassw);
		panel4=new JPanel(new FlowLayout());
		panel4.add(phone);
		panel4.add(showphone);
		panel4.add(cphone);
		panel5=new JPanel(new FlowLayout());
		panel6=new JPanel(new FlowLayout());
		panel6.add(allcomments);
		panel7=new JPanel(new FlowLayout());
		panel7.add(close);
		showpanel.add(panel1);
		showpanel.add(panel2);
		showpanel.add(panel3);
		showpanel.add(panel4);
		showpanel.add(panel6);
		showpanel.add(panel7);
		add(showpanel);
	}
}