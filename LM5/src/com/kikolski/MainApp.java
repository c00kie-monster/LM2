package com.kikolski;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainApp extends Application{
	private TextField expressionField;
	private Button startButton;
	private Label resultLabel;
		
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(root, 500, 250);
        stage.setTitle("Regexp");
        stage.setScene(scene);
        stage.show();
        initialize(scene);
	}
	
	public void initialize(Scene scene) {
		resultLabel = (Label) scene.lookup("#result");
		expressionField = (TextField) scene.lookup("#expression");
		startButton = (Button) scene.lookup("#start");
		
		startButton.setOnAction(new ChangeStateHandler());
	}
	
	private class ChangeStateHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			String expression = expressionField.getText().trim();
			Automaton automaton = new Automaton();
			boolean isCorrect = automaton.operate(expression);
			if (isCorrect)
				resultLabel.setText("Prawid³owe");
			else
				resultLabel.setText("B³êdne");
		}
	}
	
}
