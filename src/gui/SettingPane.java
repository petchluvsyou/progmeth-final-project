package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
//This class create Pane when clicked Setting Button in HomePane
public class SettingPane extends VBox {
	private Button homeButton;
	private VBox gameModeBox;
	private Label easyButton;
	private Label mediumButton;
	private Label hardButton;
	private Label nightmareButton;
	private Text header;
	private static int currentMode = 3;
	
	public SettingPane() {
		this.setPrefHeight(750);
		this.setPrefWidth(600);
		this.setSpacing(20);
		this.setPadding(new Insets(40));
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		initializeHeader();
		initializeHomeButton();
		initializeSetGameModeBox();
		this.getChildren().addAll(header, this.gameModeBox, this.homeButton);
		updateCurrentModeButton();
	}
	
	private void initializeSetGameModeBox() { // Create game mode section
		this.gameModeBox = new VBox();
		Text selectGameModeText = new Text("Select Game Mode");
		selectGameModeText.setFont(Font.font("Impact", FontWeight.BOLD, 16));
		selectGameModeText.setFill(Color.WHITE);
		HBox setGameModeBox = new HBox();
		initializeEasyButton();
		initializeMediumButton();
		initializeHardButton();
		initializeNightmareButton();
		setGameModeBox.getChildren().addAll(easyButton, mediumButton, hardButton, nightmareButton);
		setGameModeBox.setSpacing(20);
		setGameModeBox.setAlignment(Pos.CENTER);
		this.gameModeBox.getChildren().addAll(selectGameModeText, setGameModeBox);
		this.gameModeBox.setSpacing(20);
		this.gameModeBox.setAlignment(Pos.CENTER);
		this.gameModeBox.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		this.gameModeBox.setPadding(new Insets(20));
	}

	private void setCurrentMode(int modeNumber) {
		currentMode = modeNumber;
	}
	
	public static int getCurrentMode() {
		return currentMode;
	}
	
	public static int getMinLaneSpeed(int modeNumber) {
		if(modeNumber == 0) return 2;
		else if(modeNumber == 1) return 3;
		else if(modeNumber == 2) return 4;
		else return 6;
	}
	
	public static int getMaxLaneSpeed(int modeNumber) {
		if(modeNumber == 0) return 4;
		else if(modeNumber == 1) return 6;
		else if(modeNumber == 2) return 10;
		else return 14;
	}
	
	private void updateCurrentModeButton() { // Highlight the current mode button
		if(getCurrentMode() == 0) {
			this.easyButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#EDEBE3"), new CornerRadii(5), null)));
			this.mediumButton.setBackground(null);
			this.hardButton.setBackground(null);
			this.nightmareButton.setBackground(null);
		}
		else if(getCurrentMode() == 1) {
			this.easyButton.setBackground(null);
			this.mediumButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#EDEBE3"), new CornerRadii(5), null)));
			this.hardButton.setBackground(null);
			this.nightmareButton.setBackground(null);
		}
		else if(getCurrentMode() == 2) {
			this.easyButton.setBackground(null);
			this.mediumButton.setBackground(null);
			this.hardButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#EDEBE3"), new CornerRadii(5), null)));
			this.nightmareButton.setBackground(null);
		}
		else {
			this.easyButton.setBackground(null);
			this.mediumButton.setBackground(null);
			this.hardButton.setBackground(null);
			this.nightmareButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#EDEBE3"), new CornerRadii(5), null)));
		}
	}
	
	private void initializeEasyButton() {
		this.easyButton = new Label("Peaceful");
		this.easyButton.getStyleClass().add("gameModeButton");
		this.easyButton.setAlignment(Pos.CENTER);
		this.easyButton.setBackground(null);
		this.easyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	        setCurrentMode(0);
	        updateCurrentModeButton();
	    });
	}

	private void initializeMediumButton() {
		this.mediumButton = new Label("Medium");
		this.mediumButton.getStyleClass().add("gameModeButton");
		this.mediumButton.setAlignment(Pos.CENTER);
		this.mediumButton.setBackground(null);
		this.mediumButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	        setCurrentMode(1);
	        updateCurrentModeButton();
	    });
	}

	private void initializeHardButton() {
		this.hardButton = new Label("Hard");
		this.hardButton.getStyleClass().add("gameModeButton");
		this.hardButton.setAlignment(Pos.CENTER);
		this.hardButton.setBackground(null);
		this.hardButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	        setCurrentMode(2);
	        updateCurrentModeButton();
	    });
	}

	private void initializeNightmareButton() {
		this.nightmareButton = new Label("Nightmare");
		this.nightmareButton.getStyleClass().add("gameModeButton");
		this.nightmareButton.setAlignment(Pos.CENTER);
		this.nightmareButton.setBackground(null);
		this.nightmareButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	        setCurrentMode(3);
	        updateCurrentModeButton();
	    });
	}

	private void initializeHeader() {
		this.header = new Text("Setting");
		this.header.setFill(Color.WHITE);
		this.header.setFont(Font.font("Impact", FontWeight.BOLD, 60));
	}

	private void initializeHomeButton() {
		this.homeButton = new Button("Back to Home");
		this.homeButton.setId("homeButton");
		this.homeButton.getStyleClass().add("homeButton");
	}
	
	public Button getHomeButton() {
		return homeButton;
	}

}
