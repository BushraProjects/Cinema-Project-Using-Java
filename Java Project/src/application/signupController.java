package application;

import java.sql.Connection;
import java.sql.Statement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class signupController implements Initializable {
//All the variables used in the sign up page:
	@FXML
	private PasswordField password;

	@FXML
	private TextField email;

	@FXML
	private TextField UserName;

	@FXML
	private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private Media media;
//Media player codes in the initialize method for my video display in sign up page:
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
//Sign up button event action to Insert the user information in the database:
	@FXML
	void signup(ActionEvent event) {
		Connection connection = null;
		connection = DBConnect.connect();
		try {
			String sql = "INSERT INTO `ticketbooking`(`username`, `email`, `password`) VALUES (\""
					+ (UserName.getText() + "\",\"" + email.getText() + "\",\"" + password.getText() + "\");");
			System.out.println(sql);

			Statement statement = connection.createStatement();
			if (String.valueOf(UserName.getText()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "enter username");
			} else if (String.valueOf(email.getText()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "enter email");

			} else if (String.valueOf(password.getText()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "enter password");
			} else {
				statement.execute(sql);
				Parent root = FXMLLoader.load(getClass().getResource("/application/log_in.fxml"));
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.setScene(new Scene(root));
				System.out.println("Data Inserted");
			}

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Error");
		}

	}
//This is the method for if the user has already an account can go to the login page directly without sign up:
	public void loginGo(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/log_in.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
//This method is for closing all the system:
	public void close() {
		Platform.exit();
	}
}
