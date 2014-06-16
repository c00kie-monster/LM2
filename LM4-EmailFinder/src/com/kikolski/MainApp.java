package com.kikolski;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainApp extends Application{
	private Button findButton;
	private ListView<String> resultsList;
	private TextField addressField;
	private EmailFinder finder = new EmailFinder();
	private Label error
	;
	public static void main(String[] params) {
		launch(params);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(root, 600, 460);
          
        stage.setTitle("Email Finder");
        stage.setScene(scene);
        stage.show();
        initialize(scene);		
	}

	public void initialize(Scene scene) {
		findButton = (Button) scene.lookup("#find");
		resultsList = (ListView<String>) scene.lookup("#result");
		addressField = (TextField) scene.lookup("#address");
		error = (Label) scene.lookup("#error");
		
		findButton.setOnAction(new FindHandler());
	}
	
	private class FindHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent arg0) {
			String address = checkAddress(addressField.getText());
			resultsList.setItems(FXCollections.observableArrayList(new ArrayList<String>()));
			addressField.setText(address);
			error.setText("");
			try {
				
				List<String> emails = finder.find(address);
				if (emails.size() != 0) 
					resultsList.setItems(FXCollections.observableArrayList(emails));
				else
					error.setText("No e-mails found");
			} catch (IOException e) {
				error.setText("Cannot connect to the website");
			}
		}
		
		private String checkAddress(String address) {
			if (!address.startsWith("https") || !address.startsWith("http")) {
				return "http://" + address;
			}
			else
				return address;
		}
	}
}
