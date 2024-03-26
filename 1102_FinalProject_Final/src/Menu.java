import java.awt.Image;
import javax.swing.*;
import java.sql.*;

public class Menu extends JFrame{
	
	
	public Menu() {
		ImageIcon icon = new ImageIcon("menu.jpeg");
		Image image=icon.getImage().getScaledInstance(300,400,icon.getImage().SCALE_DEFAULT);
		icon=new ImageIcon(image);
		setTitle("School Meal");
		JLabel show=new JLabel();
		show.setIcon(icon);
		add(show,JLabel.CENTER);
		setVisible(true);
		setSize(330,450);
	}
}