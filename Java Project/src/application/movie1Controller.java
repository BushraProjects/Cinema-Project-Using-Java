package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class movie1Controller {

// All the variables used in the poster for more information about the movie, poster, date, and time:	
	@FXML
	private ImageView selectedFilmPoster;
	@FXML
	private Text title;
	@FXML
	private Text description;
	@FXML
	private Text startDate;
	@FXML
	private Text endDate;
	@FXML
	private Text time;
// This method takes the user back to all movies for more options:	
	public void back(MouseEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/application/allMovies.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

//This method is used when the user decides to book a movie and it takes them to booking page where they can process the booking there:
	public void bookNow(MouseEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/application/bookings.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

}
