package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
//This class create Pane when clicked How to Play Button in HomePane
public class HowToPlayPane extends VBox {
	private Button homeButton;
	private VBox instructionBox;
	private Text header;
	
	public HowToPlayPane() {
		this.setPrefHeight(750);
		this.setPrefWidth(600);
		this.setSpacing(20);
		this.setPadding(new Insets(40));
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill(Color.rgb(51,51,51),null,null)));
		initializeHeader();
		initializeInstructionBox();
		initializeHomeButton();
		this.getChildren().addAll(header, instructionBox, this.homeButton);
	}
	
	private void initializeHeader() {
		this.header = new Text("How To Play");
		this.header.setFill(Color.WHITE);
		this.header.setFont(Font.font("Impact", FontWeight.BOLD, 60));
	}

	private void initializeHomeButton() {
		this.homeButton = new Button("Back to Home");
		this.homeButton.setId("homeButton");
		this.homeButton.getStyleClass().add("homeButton");
	}
	
	private void initializeInstructionBox() { // Create the instruction box (the box with white background)
		this.instructionBox = new VBox();
		this.instructionBox.setPrefHeight(400);
		this.instructionBox.setPrefWidth(520);
		this.instructionBox.setMaxWidth(520);
		this.instructionBox.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
		Label instructionHead = new Label("A step-by-step guide on how to play the game Crozzy Furry:");
		instructionHead.setFont(Font.font("Courier New", FontWeight.BOLD, 13));
		instructionHead.setPadding(new Insets(0,0,15,0));
		instructionHead.setId("instructionHeader");
		Label instructionText = new Label("1. Objective:\n"
				+ "The objective of Crozzy Road is to guide your character safely across a busy road, avoiding obstacles and vehicles, to reach the other side without getting hit.\n"
				+"\n"
				+ "2. Character Selection:\n"
				+ "Start by selecting a character from the available options. Each character has its own unique appearance. Collect coins in the game to buy locked character.\n"
				+"\n"
				+ "3. Controls:\n"
				+ "Crozzy Road is played using WASD keys.\n"
				+ "     press W to move your character upward\n"
				+ "     press A to move your character to the left\n"
				+ "     press S to move your character downward\n"
				+ "     press D to move your character to the right\n"
				+"\n"
				+ "4. Game Over:\n"
				+ "If your character gets hit by a vehicle or hits the bottom end of the screen, it's game over. Your score will be displayed, and you'll have the option to play again and improve your performance.");
		instructionText.setMaxWidth(460);
		instructionText.setPadding(new Insets(0,0,0,20));
		instructionText.setWrapText(true);
		instructionText.setId("instructionText");
		this.instructionBox.setPadding(new Insets(20));
		this.instructionBox.getChildren().addAll(instructionHead, instructionText);
	}
	
	public Button getHomeButton() {
		return homeButton;
	}

}
