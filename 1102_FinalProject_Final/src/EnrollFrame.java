import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class EnrollFrame extends JFrame{
	private JLabel name,gender,userID,password,phon;
	private JTextField names,ID,PW,phone;
	private JButton reset,submit;
	private JRadioButton girl,boy,neither;
	private JPanel panel1,panel5,panel2,panel3,panel4,buttonpanel,operate;
	Connection connect;
	Statement stat;
	
	public EnrollFrame(Connection conn) throws SQLException{
		this.connect=conn;
		
		setTitle("Enroll");
		setSize(400,450);
		setVisible(true);
		createLabel();
		createTextField();
		createRadioButton();
		createButton();
		createLayout();
	}
	public void createLabel() {
		name=new JLabel("Username: ");
		gender=new JLabel("Gender: ");
		userID=new JLabel("StudentID: ");
		password=new JLabel("Password: ");
		phon=new JLabel("Phone: ");
	}
	public void createTextField() {
		names=new JTextField(10);
		ID=new JTextField(10);
		PW=new JTextField(10);
		phone=new JTextField(10);
	}
	public void createRadioButton() {
		girl=new JRadioButton("Girl");
		boy=new JRadioButton("Boy");
		neither=new JRadioButton("Neither");
		ButtonGroup group=new ButtonGroup();
		group.add(girl);
		group.add(boy);
		group.add(neither);
	}
	public void createButton() throws SQLException{
		submit=new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=names.getText();
				String gender="";
				if(girl.isSelected()) {
					gender="Girl";
				}else if(boy.isSelected()) {
					gender="Boy";
				}else if(neither.isSelected()) {
					gender="Neither";
				}
				String UserID=ID.getText();
				String Password=PW.getText();
				String Phone=phone.getText();
				
				PreparedStatement insert;
				try {
					insert=connect.prepareStatement("INSERT INTO personalInfo (Name,Gender,UserID,Password,Phone) VALUES (?,?,?,?,?)");
					insert.setString(1,name);
					insert.setString(2,gender);
					insert.setString(3,UserID);
					insert.setString(4,Password);
					insert.setString(5,Phone);
					insert.executeUpdate();
					stat=connect.createStatement();
					JOptionPane.showMessageDialog(null,"Success!","Info",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}catch(SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,"Error, Please try again!","Error",JOptionPane.ERROR_MESSAGE);
				}			
			}
		});
//		reset=new JButton("Reset");
//		reset.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
	}
	
	public void createLayout() {
		panel1=new JPanel(new FlowLayout());
		panel1.add(name);
		panel1.add(names);
		panel5=new JPanel(new FlowLayout());
		panel5.add(gender);
		panel5.add(girl);
		panel5.add(boy);
		panel5.add(neither);
		panel2=new JPanel(new FlowLayout());
		panel2.add(userID);
		panel2.add(ID);
		panel3=new JPanel(new FlowLayout());
		panel3.add(password);
		panel3.add(PW);
		panel4=new JPanel(new FlowLayout());
		panel4.add(phon);
		panel4.add(phone);
		buttonpanel=new JPanel(new FlowLayout());
		buttonpanel.add(submit);
//		buttonpanel.add(reset);
		operate=new JPanel(new GridLayout(6,1));
		operate.add(panel1);
		operate.add(panel5);
		operate.add(panel2);
		operate.add(panel3);
		operate.add(panel4);
		operate.add(buttonpanel);
		add(operate);
	}
	
}