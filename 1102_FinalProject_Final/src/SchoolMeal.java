import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
public class SchoolMeal extends JFrame{

	Connection connect;
	Statement stat;
	private JTextArea menu;
	private JPanel panel,panel2,panel3,p;
	private JButton home;
	private JLabel label;
	public SchoolMeal(Connection connect)throws SQLException{
		this.connect=connect;
		setTitle("School Meal");
		setSize(300,250);
		setVisible(true);
		CreateTextArea();
		CreateButton();
		SetText();
	}
	public void CreateTextArea() {
		label=new JLabel("本週菜單");
		menu=new JTextArea(8,12);
		menu.setEditable(false);
		
		panel=new JPanel();
		panel.add(menu);
		add(panel);
	}
	public void CreateButton() {
		home=new JButton("Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
	}
	public void SetText() throws SQLException{
		try{
			stat=connect.createStatement();
			String guery="SELECT lunch,dinner FROM schoolmeal";
			boolean hasresult=stat.execute(guery);
			if(hasresult) {
				ResultSet result=stat.getResultSet();
				menu.setText(showResultSet(result));
				result.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static String showResultSet(ResultSet result) throws SQLException {
		
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";
		
		for (int i = 1; i <= columnCount; i++) {			
			output += String.format("%25s", metaData.getColumnLabel(i));
		}
		output += "\n";
		
		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
				output += String.format("%15s", result.getString(i));
			}
			output += "\n";
		}
		return output;
	}
	
}