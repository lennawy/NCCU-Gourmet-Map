import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.awt.Font;
public class HomeFrame extends JFrame{

		private JButton restaurant,view_comment,comment,schoolmeal,setup,login,logout,delete,mycomments,modify;
		private JTextArea activities;
		private JPanel selectpanel,showpanel,mainpanel,basepanel;
		private LoginFrame loginframe;
		private SetupFrame setupframe;
		private JLabel title;
		private boolean success;
		User user = new User();
		Connection conn;
		private String username,userpassword,id,mail;
		String server = "jdbc:mysql://140.119.19.73:3315/";
		String database = "mongroup7";
		String url = server + database + "?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
		String userName = "mongroup7";
		String password = "qug6740";
		private ArrayList <Restaurant> restaurants = new ArrayList <Restaurant>();
		private ArrayList <String> dt = new ArrayList<String>();

		public HomeFrame() throws SQLException {
			try {
				conn = DriverManager.getConnection(url, userName, password);
				Statement stat = conn.createStatement();
				String query = "SELECT * FROM RestaurantsList;";
				boolean hasResultSet = stat.execute(query);
				if (hasResultSet) {
					ResultSet result = stat.getResultSet(); 
					showResultSet(result, restaurants);
					result.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.success=false;
			setTitle("NCCU政大美食地圖");
			setSize(500,320);
			createButton();
			createTextArea();
			createLayout();
		}
		public void createButton() throws SQLException {
			restaurant=new JButton("Restaurant");
			restaurant.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					Page1 frame = new Page1(restaurants);	
				}
			});
			view_comment=new JButton("View All Comments");
			view_comment.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					ViewRatingBoard board = new ViewRatingBoard();
				}
			});
			comment=new JButton("Write Comments");
			comment.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if(success) {
						RestaurantSurvey rs;
						try {
							rs = new RestaurantSurvey(conn, username);
							rs.setVisible(true);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
  					    JOptionPane.showMessageDialog(null,"Please login before writing comments!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			schoolmeal=new JButton("SchoolMeal");
			schoolmeal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					Menu menu=new Menu();
				}
			});
			setup=new JButton("Setting");
			setup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					if(success) {
						try {
							setupframe=new SetupFrame(conn, username, id, userpassword, mail);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please login","Warning",JOptionPane.INFORMATION_MESSAGE);
						try {
							loginframe = new LoginFrame(getHome());
							loginframe.creatAll(conn);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			});
			login=new JButton("Login");
			login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						loginframe = new LoginFrame(getHome());
						loginframe.creatAll(conn);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			logout=new JButton("Logout");
			logout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					setSuccess(false);
					loginframe.clear();
					JOptionPane.showMessageDialog(null, "Successfully logout","Hint",JOptionPane.INFORMATION_MESSAGE);
					refreshAgain();
				}
			});
			mycomments=new JButton("View My Comments");
			mycomments.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					ViewMyComments comment = new ViewMyComments(username);
				}
			});
			
		}
		public void setName(String name) {
			this.username=name;
		}
		public String getName() {
			return this.username;
		}
		public void setPW(String pw) {
			this.userpassword=pw;
		}
		public String getPW() {
			return this.userpassword;
		}
		public void setSuccess(boolean bool) {
			this.success = bool;
		}
		public boolean getSuccess() {
			return this.success;
		}
		public void setID(String id) {
			this.id=id;
		}
		public String getID() {
			return this.id;
		}
		public void setMail(String mail) {
			this.mail=mail;
		}
		public String getMail() {
			return this.mail;
		}
		public HomeFrame getHome() {
			return this;
		}
		public void createTextArea() {
			activities=new JTextArea(10,25);
			activities.setText("�ϥΤ覡:\n");
			activities.setEditable(false);
			showpanel=new JPanel();
			showpanel.add(activities);
		}
		public void createLayout() {
			title=new JLabel("政大美食地圖",JLabel.CENTER);
			Font t=new Font("Times new Roman",Font.ITALIC,35);
			title.setFont(t);
			basepanel=new JPanel(new BorderLayout());
			basepanel.add(title,BorderLayout.NORTH);
			mainpanel=new JPanel(new FlowLayout());
			selectpanel=new JPanel(new GridLayout(6,1));
			selectpanel.add(restaurant);
			selectpanel.add(view_comment);
			selectpanel.add(comment);
			selectpanel.add(schoolmeal);
			selectpanel.add(setup);
			selectpanel.add(login);
			mainpanel.add(selectpanel);
			mainpanel.add(showpanel);
			basepanel.add(mainpanel,BorderLayout.CENTER);
			add(basepanel);
		}
		public void refresh() {
			//setSize(600,420);
			selectpanel.remove(login);
			selectpanel.setLayout(new GridLayout(7,1));
			selectpanel.add(mycomments, 3);
			selectpanel.add(logout);
			selectpanel.updateUI();
			selectpanel.repaint();
		}
		public void refreshAgain() {
			selectpanel.remove(logout);
			selectpanel.remove(mycomments);
			selectpanel.setLayout(new GridLayout(6,1));
			selectpanel.add(login);
			selectpanel.updateUI();
			selectpanel.repaint();
		}
		
		public static void showResultSet(ResultSet result, ArrayList restaurants) throws SQLException { 
			ResultSetMetaData metaData = result.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
			}
			System.out.println();
			while (result.next()) {
				ArrayList <String> dt = new ArrayList<String>();
				for (int i = 1; i <= columnCount; i++) {
					dt.add(result.getString(i));
				}

				Restaurant r = new Restaurant(dt.get(1), dt.get(2), dt.get(3), dt.get(4), dt.get(5), dt.get(6), dt.get(7), dt.get(8), dt.get(9), dt.get(10));
				restaurants.add(r);
				System.out.println(); 
			}
		}
	}