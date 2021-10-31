//Login Page Controller: 

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.application.Platform;

public class log_inController implements Initializable {
	
//This is the action event for that takes you to the sign up page:
	@FXML
	void signup(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/signup.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
//Variables used in the Login page:
	@FXML
	private Label label;

	@FXML
	private TextField username;

	@FXML
	private PasswordField txtpass;

	@FXML
	private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private Media media;

//This is the codes I used in initialize method for my video display in Login page:
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String path = new File("src/media/red carpet music.mp4").getAbsolutePath();
		media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaPlayer.setVolume(0);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		DoubleProperty width = mediaView.fitWidthProperty();
		DoubleProperty height = mediaView.fitHeightProperty();
		width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

	}
//login action logins the user by considering all the conditions and takes you to the movies list:
	@FXML
	void login(ActionEvent event) {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		connection = DBConnect.connect();
		try {

			String sql = "Select * from ticketbooking where username = ? and password = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, username.getText());
			pst.setString(2, txtpass.getText());

			rs = pst.executeQuery();
			if (String.valueOf(username.getText()).equals("") || String.valueOf(txtpass.getText()).equals("")) {
				JOptionPane.showMessageDialog(null, "Empty user name or password!");
			} else if (rs.next() == true) {

				Parent root = FXMLLoader.load(getClass().getResource("/application/allMovies.fxml"));
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.setScene(new Scene(root));

			} else {
				JOptionPane.showMessageDialog(null, "Wrong user name or password!");
			}
		} catch (SQLException | IOException throwables) {
			throwables.printStackTrace();

		}
	}
//this is for the close button that closes all the system:
	public void close() {
		Platform.exit();
	}

}
