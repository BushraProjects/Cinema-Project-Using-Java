package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnect {
	public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ticketbooking";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connect to Database");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            ex.printStackTrace();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
