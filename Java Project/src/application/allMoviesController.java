package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class allMoviesController {

//I used ScrollPane to add as much movie as I wanted in one window and scroll it up and down easily:
	@FXML
	private ScrollPane scrollPane;

//I used grid because of the organizing my movies in equal shapes:
	@FXML
	private GridPane grid;

//Back button action to take the user back to login page:
	public void back(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/log_in.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

//These are the methods used for taking the user straight to Movie Poster for getting more detail detail about the movie:		
	public void movie1(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/movie1.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	public void movie2(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/movie2.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	public void movie3(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/movie3.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	public void movie4(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/movie4.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	public void movie5(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/movie5.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	public void movie6(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/movie6.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	public void movie7(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/movie7.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	public void movie8(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/movie8.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

}
