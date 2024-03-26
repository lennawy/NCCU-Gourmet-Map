import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Tester {	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		/*String server = "jdbc:mysql://140.119.19.73:3315/";
		String database = "mongroup7"; // change to your own database
		String url = server + database + "?useSSL=false";
		String username = "mongroup7"; // change to your own username
		String password = "qug6740"; // change to your own password*/
		
		HomeFrame frame=new HomeFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}