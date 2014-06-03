package com.kikolski;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainApp extends Application{
	private Button fileButton;
	private Button startButton;
	private ListView<String> resultsList;
	private TextArea fileContent;
	private Label filename;
	private final FileChooser fileChooser = new FileChooser();
	private Automaton automaton = new Automaton();
	private String[] series;
	private List<String> results = new ArrayList<>();
	private StartAutomatonHandler startAutomatonHandler; 
	private OpenFileHandler openFileHandler;
	
	public static void main(String[] params) {
		launch(params);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(root, 600, 460);
          
        stage.setTitle("NFA");
        stage.setScene(scene);
        stage.show();
		startAutomatonHandler = new StartAutomatonHandler();
		openFileHandler = new OpenFileHandler(stage);
        initialize(scene);
	}
	
	public void initialize(Scene scene) {
		fileButton = (Button) scene.lookup("#fileChooser");
		startButton = (Button) scene.lookup("#start");
		resultsList = (ListView<String>) scene.lookup("#result");
		fileContent = (TextArea) scene.lookup("#fileContent");
		filename =(Label) scene.lookup("#filename");
			
		fileButton.setOnAction(openFileHandler);
		startButton.setOnAction(startAutomatonHandler);
	}
	
	private class StartAutomatonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			if (series.length > 0) {
				for (int s = 0; s < series.length; s++) {
					for (int i = 0; i < series[s].length(); i++) { 
						automaton.move(String.valueOf(series[s].charAt(i)));
					}
					if (automaton.isInFinalState())
						results.add(s + ") Wyst¹pi³o powtórzenie");
					else
						results.add(s + ") Brak powtórzenia");
					
					System.out.println("Analiza kolejnego ciagu:");
					automaton = new Automaton();
				}
				resultsList.setItems(FXCollections.observableList(results));
			}
		}
	}
	
	
	private class OpenFileHandler implements EventHandler<ActionEvent> {
		private Stage stage;
		
		public OpenFileHandler(Stage stage) {
			this.stage = stage;
		}
		
		@Override
		public void handle(ActionEvent arg0) {
			File file = fileChooser.showOpenDialog(stage);
			String text = "";
			if (file != null)
				try {
					series = readFile(file);
					filename.setText(file.getName());
					for (String s : series)
						text += s + "\n";
					fileContent.setText(text);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		private String[] readFile(File file) throws IOException {
			String text = " ";
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer result = new StringBuffer();
			
			while ((text = reader.readLine()) != null)
				result.append(text);
			
			return result.toString().split("#");
		}
	}
}