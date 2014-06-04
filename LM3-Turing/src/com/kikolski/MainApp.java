package com.kikolski;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MainApp extends Application{

	private static final String BUTTON_ID_1 = "button1";
	private static final String BUTTON_ID_2 = "button2";
	private static final String BUTTON_ID_5 = "button5";
	private static final String BUTTON_ID_BUY = "buttonBuy";
	
	private Label currentState;
	private Label oddMoney, ticket;
	private Button button1;
	private Button button2;
	private Button button5;
	private Button buttonBuy;
	private TuringMachine2 machine;
	
	String result = "";
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("machineView.fxml"));
        Scene scene = new Scene(root, 600, 460);
          
        stage.setTitle("Turin Machine");
        stage.setScene(scene);
        stage.show();
        initialize(scene);
	}
	
	public void initialize(Scene scene) {
		ChangeStateHandler hander = new ChangeStateHandler();
		machine = new TuringMachine2();
		
		currentState = (Label) scene.lookup("#currentState");
		oddMoney = (Label) scene.lookup("#oddMoney");
		ticket = (Label) scene.lookup("#ticket");
				
		button1 = (Button) scene.lookup("#" + BUTTON_ID_1);
		button2 = (Button) scene.lookup("#" + BUTTON_ID_2);
		button5 = (Button) scene.lookup("#" + BUTTON_ID_5);
		buttonBuy = (Button) scene.lookup("#" + BUTTON_ID_BUY);
		
		button1.setOnAction(hander);
		button2.setOnAction(hander);
		button5.setOnAction(hander);
		buttonBuy.setOnAction(hander);
	}
	
	private class ChangeStateHandler implements EventHandler<ActionEvent> {
		private String coins = "";
		@Override
		public void handle(ActionEvent arg0) {
			String id = ((Button) arg0.getSource()).getId();
				
			if (BUTTON_ID_1.equals(id)) {
				machine.initTape("1");
			} else if (BUTTON_ID_2.equals(id)) {
				machine.initTape("2");
			} else if (BUTTON_ID_5.equals(id)){
				machine.initTape("5");
			} else {
				machine.initTape("_");
				
				while (!machine.getCurrentState().isFinal()) {
					if (!(coins = machine.operate()).equals("_")) 
						result += coins + " Z£ ";
				}
				
				if (machine.getCurrentState().isUnacceptable())
					ticket.setText("Za ma³o.");
				else
					ticket.setText("Wydaje potwierdzenie.");
			
				oddMoney.setText(result);
			}							
			currentState.setText(machine.getCurrentState().toString());
		}
	}
}