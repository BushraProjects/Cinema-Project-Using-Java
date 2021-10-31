/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import Model.MdlBookings;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 */
public class bookingsController implements Initializable {

    @FXML
    private ComboBox MovieBox;
    @FXML
    private ComboBox DateBox;
    @FXML
    private ComboBox TimeBox;
    @FXML
    private TextField txtID;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private TableView<MdlBookings> Table;
    @FXML
    private TableColumn<MdlBookings, Integer> IDColumn;
    @FXML
    private TableColumn<MdlBookings, String> EmailColumn;
    @FXML
    private TableColumn<MdlBookings, String> UsernameColumn;
    @FXML
    private TableColumn<MdlBookings, String> MovieColumn;
    @FXML
    private TableColumn<MdlBookings, String> DateColumn;
    @FXML
    private TableColumn<MdlBookings, String> TimeColumn;
    @FXML
    private TableColumn<MdlBookings, String> time;

    /**
     * Initializes the controller class.
     */
    
    //I created the arraylists to set the values inside for the users to choose from
    ObservableList<String> movieList = FXCollections.observableArrayList("JOKER (2019)","2012", "JUSTICE LEAGUE","MARY POPPJINS","STORKS","The Vampire Diaries","Harry Potter","TWILIGHT");
    ObservableList<String> DateList = FXCollections.observableArrayList("Sat 24 Jun","Mon 26 Jun", "Wed 28 Jun", "Fri 30 Jun");
    ObservableList<String> TimeList = FXCollections.observableArrayList("09:00 - 11:30","15:00 - 17:30", "18:00 - 20:30", "21:00 - 23:30");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //I set those values to the combo boxes inside the program
        MovieBox.setValue("JOKER (2019)");
        MovieBox.setItems(movieList);
        DateBox.setValue("Sat 24 Jun");
        DateBox.setItems(DateList);
        TimeBox.setValue("09:00 - 11:30");
        TimeBox.setItems(TimeList);
        
        //This code is to get the info from the database and put it in the columns of the table
        IDColumn.setCellValueFactory(new PropertyValueFactory<MdlBookings, Integer>("ID"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<MdlBookings, String>("Email"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<MdlBookings, String>("Username"));
        MovieColumn.setCellValueFactory(new PropertyValueFactory<MdlBookings, String>("Movie"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<MdlBookings, String>("Date"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<MdlBookings, String>("Time"));
        
        //I made the table and every column inside program editable to let users update when they need it
          Table.setEditable(true);
        
        EmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        UsernameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        EmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        MovieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        TimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
 



    }    
    
    //I made the variables and the method for connecting the 
    //database to the program with a different approach
    static Connection conn = null;
    static PreparedStatement pst = null;
    static ResultSet rs = null;

    public static Connection connect() throws ClassNotFoundException{
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ticketbooking";
            String user = "root";
            String pass = "";
            
            conn = DriverManager.getConnection(url,user,pass);

            System.out.println("connected");
        }
        catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }
        return conn;
    }
    
    //I made this variable to get information from the database using the query
    //And put them inside the arraylist called Bookinglist
     ObservableList<MdlBookings> list;
    
    public static ObservableList<MdlBookings> getInfo(){
        
        ObservableList<MdlBookings> Bookinglist = FXCollections.observableArrayList();

        try{
        Connection conn = connect();
        
        pst = conn.prepareStatement("select * from bookings");
        rs = pst.executeQuery();
        
        while(rs.next()){
            MdlBookings m = new MdlBookings();
            int ID = Integer.parseInt(rs.getString("ID"));
            String email = rs.getString("Email");
            String User = rs.getString("Username");
            String movie = rs.getString("Movie");
            String date = rs.getString("Date");
            String time = rs.getString("Time");
            
            m.setID(ID);
            m.setEmail(email);
            m.setUsername(User);
            m.setMovie(movie);
            m.setDate(date);
            m.setTime(time);
            
            
            Bookinglist.addAll(m);
            
        }
        }
        
        catch(ClassNotFoundException | NumberFormatException | SQLException ex){
            
        }
        return Bookinglist;
        
    }
   
    //I made showlist function to get the information and show it on the table by pressing a button
    @FXML
    void ShowList(ActionEvent event) throws SQLException, ClassNotFoundException{
    
    list = getInfo();
    Table.setItems(list);
        }

    //I made the add booking show for the button to record the movie and the timing in the database
    public void AddNewBooking() throws ClassNotFoundException, SQLException{
        
            
            
            Connection conn = connect();
            String Email = txtEmail.getText();
            String Username = txtUsername.getText();
            String Movie = MovieBox.getSelectionModel().getSelectedItem().toString();
            String Date = DateBox.getSelectionModel().getSelectedItem().toString();
            String Time = TimeBox.getSelectionModel().getSelectedItem().toString();
            
            
            if(Email.equals(null) || Username.equals("") || Movie.equals("") || Date.equals("") || Time.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill all the information!");
            }
            else{
            String msql = "insert into bookings (Email, Username, Movie, Date, Time) values (?,?,?,?,?)";
                    
            PreparedStatement pst = null;
            pst = conn.prepareStatement(msql);
            pst.setString(1,Email);
            pst.setString(2,Username);
            pst.setString(3,Movie);
            pst.setString(4,Date);
            pst.setString(5,Time);
            
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "New Booking added sucessfully");
            txtEmail.setText(null);
            txtUsername.setText(null);
            list = getInfo();
            Table.setItems(list);
            }
            
    }
    //I made the clear fuction to clean the textboxes
    public void clear(){
            txtEmail.setText(null);
            txtUsername.setText(null);
            
    }
    
	
    //I made the delete button to erase any record from the booking inside the table
    public void delete() throws ClassNotFoundException, SQLException{
        String ID = txtID.getText();
        if(ID.equals("")){
            JOptionPane.showMessageDialog(null, "Enter an ID to delete");
        }
        else{
            Connection conn = connect();
            String msql = "delete from bookings where ID = '"+ID+"'";
            
            pst = conn.prepareStatement(msql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Booking Removed sucessfully");
            txtID.setText(null);
            list = getInfo();
            Table.setItems(list);
        }
    }
    

    //I made this function in order to edit and update existing records in the table
    //The first one is to change the Email of the user in case of any mistake
    @FXML
    public void changeEmailCell(TableColumn.CellEditEvent edittedCell) throws SQLException
			 {
			     MdlBookings BookingSelected =  Table.getSelectionModel().getSelectedItem();
			     
			     String email = edittedCell.getNewValue().toString();
			     int ID = IDColumn.getCellData(BookingSelected);
			     
			     try {
			    	 Connection mycon = connect();
			    	 Statement stmt = mycon.createStatement();
					
					String sql = "update bookings set Email = '"+email+"' where ID = '"+ID+"'";
					stmt.executeUpdate(sql);
					
			     }
			     catch(Exception e) {
			     	e.printStackTrace();
			     }
			 }
    
    //The first one is to change the Email of the user in case of any mistake
    @FXML
    public void changeUsernameCell(TableColumn.CellEditEvent edittedCell) throws SQLException
			 {
			     MdlBookings UsernameSelected =  Table.getSelectionModel().getSelectedItem();
			     
			     String Username = edittedCell.getNewValue().toString();
			     int ID = IDColumn.getCellData(UsernameSelected);
			     
			     try {
			    	 Connection mycon = connect();
			    	 Statement stmt = mycon.createStatement();
					
					String sql = "update bookings set Username = '"+Username+"' where ID = '"+ID+"'";
					stmt.executeUpdate(sql);
					
			     }
			     catch(Exception e) {
			     	e.printStackTrace();
			     }
			 }
    //The first one is to change the Email of the user in case of any mistake
    @FXML
    public void changeMovieCell(TableColumn.CellEditEvent edittedCell) throws SQLException
			 {
			     MdlBookings MovieSelected =  Table.getSelectionModel().getSelectedItem();
			     
			     String Movie = edittedCell.getNewValue().toString();
			     int ID = IDColumn.getCellData(MovieSelected);
			     
			     try {
			    	 Connection mycon = connect();
			    	 Statement stmt = mycon.createStatement();
					
					String sql = "update bookings set Movie = '"+Movie+"' where ID = '"+ID+"'";
					stmt.executeUpdate(sql);
					
			     }
			     catch(Exception e) {
			     	e.printStackTrace();
			     }
			 }
    
    //The first one is to change the Email of the user in case of any second thoughts
    @FXML
    public void changeDateCell(TableColumn.CellEditEvent edittedCell) throws SQLException
			 {
			     MdlBookings DateSelected =  Table.getSelectionModel().getSelectedItem();
			     
			     String Date = edittedCell.getNewValue().toString();
			     int ID = IDColumn.getCellData(DateSelected);
			     
			     try {
			    	 Connection mycon = connect();
			    	 Statement stmt = mycon.createStatement();
					
					String sql = "update bookings set Date = '"+Date+"' where ID = '"+ID+"'";
					stmt.executeUpdate(sql);
					
			     }
			     catch(Exception e) {
			     	e.printStackTrace();
			     }
			 }
    //The first one is to change the Email of the user in case of any second thoughts
    @FXML
    public void changeTimeCell(TableColumn.CellEditEvent edittedCell) throws SQLException
			 {
			     MdlBookings TimeSelected =  Table.getSelectionModel().getSelectedItem();
			     
			     String Time = edittedCell.getNewValue().toString();
			     int ID = IDColumn.getCellData(TimeSelected);
			     
			     try {
			    	 Connection mycon = connect();
			    	 Statement stmt = mycon.createStatement();
					
					String sql = "update bookings set Time = '"+Time+"' where ID = '"+ID+"'";
					stmt.executeUpdate(sql);
					
			     }
			     catch(Exception e) {
			     	e.printStackTrace();
			     }
			 }
    
    //I made this fucntion to get the users back to the main movies list
    public void changeToAllMovies(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/application/allMovies.fxml"));
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.setScene(new Scene(root));
    }
    //I made this gmail function to open the browser of the users in order connect with us directly
    @FXML
    public void gmail(){
        try{
            
            Desktop.getDesktop().browse(new URL("http://www.Gmail.com").toURI());
        }catch(IOException | URISyntaxException e){
            
        }
        
    }
    //I made this to close the program 
    public void close() {
		Platform.exit();
	}
    



}
