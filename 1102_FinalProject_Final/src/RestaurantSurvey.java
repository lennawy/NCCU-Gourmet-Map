import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RestaurantSurvey extends JFrame {
	//private HomePage homepage;
    Connection conn;
    JButton submit,reset,home;     
    JTextField name,id;
    JTextArea comments;
    Container cp;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    JComboBox<String> restaurant,frequency;
    JFrame f1,f2;
    JRadioButton one;
    JRadioButton two;
    JRadioButton three;
    JRadioButton four;
    JRadioButton five;
    ButtonGroup rating;
    String[] rest= {"","羹大王","浪速食鋪","Come See 披薩","Juicy Bun","大汗","華越","素還真","波波恰恰","菁英便當","美香味","私房麵","東京小城","提洛斯","左撇子","原丼力","MY 麵屋","飽飽食府","樂山食堂","高句麗","滇味廚房","小木屋","摩斯漢堡","麥當勞","吉野家","八方雲集","Subway"};
    String[] freq= {"First time","Once or twice a week","Three times a week","Above three times a week"};
    private String NAME;
        
    public RestaurantSurvey(Connection conn, String NAME) throws SQLException{
    	JOptionPane.showMessageDialog(f1, "ID can be entered at will");
    	this.conn=conn;
        this.setTitle("Feedback Form");
//        this.setDefaultCloseOperation(ABORT);
        this.setResizable(false);
        this.setSize(540, 550);
        cp= getContentPane();
        this.setLayout(null);
        createHeading();
        createRestaurant();
        createName();
        createID();
        createFrequency();
        createRate();
        createComments();
        createButton();
        this.cp.setBackground(new java.awt.Color(0xA6E3D8));
        this.NAME = NAME;
    }

    public void createHeading() {
        JLabel heading= new JLabel();
        heading.setText("FEEDBACK SURVEY");
        heading.setFont(new Font("Arial", Font.BOLD, 36));
        heading.setBounds(80,0,440,45);
        this.cp.add(heading);
    }
    public void createRestaurant() {
        JLabel Restaurant=new JLabel();
        Restaurant.setText("Restaurant: ");
        Restaurant.setFont(new Font("Calibri",Font.PLAIN, 18));
        Restaurant.setBounds(50,80,150,28);
        this.cp.add(Restaurant);
        
        restaurant=new JComboBox(rest);
        restaurant.setBounds(230,80,240,28);
        this.cp.add(restaurant);
    } 
    public void createName() {
        JLabel Name= new JLabel();
        Name.setText("Name: ");
        Name.setFont(new Font("Calibri", Font.PLAIN, 18));
        Name.setBounds(50,125,80,28);
        this.cp.add(Name);

        name = new JTextField();
        name.setBounds(230, 125, 240, 28);
        this.cp.add(name);
    }
    
    public void createID() {
        JLabel ID= new JLabel();
        ID.setText("ID: ");
        ID.setFont(new Font("Calibri", Font.PLAIN, 18));
        ID.setBounds(50,180, 150, 28);
        this.cp.add(ID);

        id = new JTextField();
        id.setBounds(230, 175, 240, 28);
        this.cp.add(id);
    }
    
    public void createFrequency() {
        JLabel Frequency= new JLabel();
        Frequency.setText("Frequency: ");
        Frequency.setFont(new Font("Calibri", Font.PLAIN, 18));
        Frequency.setBounds(50,235,250,28);
        this.cp.add(Frequency);

        frequency= new JComboBox(freq);
        frequency.setBounds(230,235,200,28);
        this.cp.add(frequency);
    }

    public void createRate() {
        JLabel Rating= new JLabel();
        Rating.setText("Rate: ");
        Rating.setFont(new Font("Calibri", Font.PLAIN, 18));
        Rating.setBounds(50,285,100,28);
        this.cp.add(Rating);


        one=new JRadioButton("1");
        two=new JRadioButton("2");
        three=new JRadioButton("3");
        four=new JRadioButton("4");
        five=new JRadioButton("5");
        five.setSelected(true);

        one.setBounds(230, 285, 40, 28);
        two.setBounds(280, 285, 40, 28);
        three.setBounds(330, 285, 40, 28);
        four.setBounds(380, 285, 40, 28);
        five.setBounds(430, 285, 40, 28);
        five.setSelected(true);

        this.add(one);
        this.add(two);
        this.add(three);
        this.add(four);
        this.add(five);

        rating= new ButtonGroup(); 
        rating.add(one);
        rating.add(two);
        rating.add(three);
        rating.add(four);
        rating.add(five);
    }
    
    public void createComments() {
        JLabel Comments= new JLabel();
        Comments.setText("Comments : ");
        Comments.setFont(new Font("Calibri", Font.PLAIN, 18));
        Comments.setBounds(50,340,170,28);
        this.cp.add(Comments);

        comments = new JTextArea(20,40);
        comments.setBounds(230, 340, 240, 84);
        this.cp.add(comments);
     }
    
    public void createButton() throws SQLException {
        submit= new JButton("Submit");
        submit.setFont(new Font("Calibri", Font.PLAIN, 18));
        submit.setBounds(165, 450, 100, 28);
        this.cp.add(submit);
        submit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
                boolean flag=false;
                f2= new JFrame();
                
                	if(restaurant.getSelectedItem().toString().equals(""))
                    {
                    	JOptionPane.showMessageDialog(f2, "Restaurant cannot be empty");
                    }
                	else if((name.getText().isEmpty()) || (name.getText() == null))
                    {
                        JOptionPane.showMessageDialog(f2, "Name cannot be empty");
                    }
                    else if((id.getText().isEmpty()) || (id.getText() == null))
                    {
                        JOptionPane.showMessageDialog(f2, "ID cannot be empty");
                    }
                    else {
                        flag=true; 
                        
	                    if(flag)
	                    {
	                        String r;
	                        int r2;
	                        if(one.isSelected()) {
	                            r="One star";
	                            r2=1;
	                        }
	                        else if(two.isSelected()) {
	                            r="Two stars";
	                            r2=2;
	                        }    
	                        else if(three.isSelected()) {
	                            r="Three stars";   
	                            r2=3;
	                        }
	                        else if(four.isSelected()) {
	                            r="Four stars";
	                            r2=4;
	                        }
	                        else {
	                        	r="Five stars";
	                        	r2=5;
	                        }
	                        
	                        String s1= "Thank you for your valuable Feedback!\n\nYour Responses:\n";
	                        String s2= "Restaurant: "+restaurant.getSelectedItem().toString()+"\nName: "+name.getText()+"\nID: "+id.getText()+"\nFrequency: "+(String)frequency.getSelectedItem()+"\nRate: "+r+"\nComments: "+comments.getText();
	                        String s3= "\nTime: "+dtf.format(LocalDateTime.now());
	                        String disp=s1+s2+s3;
	                        JOptionPane.showMessageDialog(f2, disp);
	                        //homepage=new HomePage();
	                        setVisible(false);
	                        
	                        String r1=restaurant.getSelectedItem().toString();
	                        String t=dtf.format(LocalDateTime.now());
	                        String n = NAME;
	                        //String n=name.getText();
	                        String i=id.getText(); 
	                        String f=frequency.getSelectedItem().toString();
	                        String c=comments.getText();
	                        
	                        
	                        java.sql.PreparedStatement insert;
	                        
	                        try {
	                        	insert=conn.prepareStatement("INSERT INTO RateSurvey(Restaurant, Date, Name, ID, Frequency, Rate, Comments) VALUES (?,?,?,?,?,?,?)");
	                        	insert.setString(1, r1);
	    						insert.setString(2, t);
	    						insert.setString(3, n);
	    						insert.setString(4, i);
	    						insert.setString(5, f);
	    						insert.setInt(6, r2);
	    						insert.setString(7, c);
	    						insert.executeUpdate();
	                        }
	                        catch(SQLException ex) {
	                        	ex.printStackTrace();
	                        }
	                        
	                        
	                    }}}
        });

        reset= new JButton("Reset");
        reset.setFont(new Font("Calibri", Font.PLAIN, 18));
        reset.setBounds(275, 450, 100, 28);
        this.cp.add(reset);

        reset.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 name.setText(null);
                 id.setText(null);
                 comments.setText(null);
                 frequency.setSelectedIndex(0);
                 one.setSelected(false); 
                 two.setSelected(false);   
                 three.setSelected(false); 
                 four.setSelected(false); 
                 five.setSelected(true); 
        	}
             
        	});
        
        home=new JButton("Home");
        home.setFont(new Font("Calibri",Font.PLAIN,18));
        home.setBounds(385,450,100,28);
        this.cp.add(home);
        home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//homepage=new HomePage();
				//setVisible(false);
				dispose();
			}
        	
        });
        


}
}