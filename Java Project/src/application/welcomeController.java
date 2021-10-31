package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class welcomeController implements Initializable{
	@FXML
	private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private Media media;
	@FXML
	private Label label;
	
	@Override
//Inside the initialize method I wrote the codes for media display in my welcome page:
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
	
//This method takes the user to login page:	
	@FXML
    void customer(MouseEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/application/log_in.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
    }
//This is for closing the whole system:	
	public void close () {
		Platform.exit();
	}
	
	
	
	

}
