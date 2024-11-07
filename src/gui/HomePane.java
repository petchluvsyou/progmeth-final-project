package gui;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
//This class create the home Pane that is shown when opened the game
public class HomePane extends VBox {
	private Text gameText;
	private Button playButton;
	private Button characterButton;
	private Button howToPlayButton;
	private Button settingButton;
	private VBox buttonSection;
	private VBox gameExample;

	public HomePane() {
		this.setPadding(new Insets(30));
		initializeGameText();
		initializeGameExample();
		initializePlayButton();
		initializeCharacterButton();
		initializeHowToPlayButton();
		initializeSettingButton();
		initializeButtonSection();
		this.setBackground(new Background(new BackgroundFill(Color.valueOf("#A94064"), null, null)));
		this.getChildren().addAll(gameText, buttonSection);
		this.setAlignment(Pos.CENTER);
	}

	private void initializeGameText() {
		this.gameText = new Text("CROZZY FURRY");
		this.gameText.setId("gameText");
		this.gameText.setFont(Font.font("IMPACT", FontWeight.BOLD, 70));
		this.gameText.setFill(Color.WHITE);
		BorderPane.setAlignment(gameText, Pos.CENTER);

	}

	private void initializeGameExample() {
		this.gameExample = new VBox();
		String filePath = "src/gameExample.gif";
		Image exampleVideo = new Image(new File(filePath).toURI().toString());
		ImageView imageView = new ImageView(exampleVideo);
		imageView.setFitHeight(240);
		imageView.setFitWidth(460);
		this.gameExample.setPadding(new Insets(0, 0, 40, 0));
		this.gameExample.getChildren().add(imageView);
		this.gameExample.setAlignment(Pos.CENTER);
	}

	private void initializePlayButton() {
		this.playButton = new Button("Play");
		this.playButton.setId("playButton");
		this.playButton.getStyleClass().add("mainButton");
	}

	private void initializeCharacterButton() {
		this.characterButton = new Button("Change Character");
		this.characterButton.setId("changeCharacterButton");
		this.characterButton.getStyleClass().add("mainButton");
	}

	private void initializeHowToPlayButton() {
		this.howToPlayButton = new Button("How To Play");
		this.howToPlayButton.setId("howToPlayButton");
		this.howToPlayButton.getStyleClass().add("mainButton");
	}

	private void initializeSettingButton() {
		this.settingButton = new Button("Setting");
		this.settingButton.setId("settingButton");
		this.settingButton.getStyleClass().add("mainButton");
	}

	private void initializeButtonSection() {
		this.buttonSection = new VBox();
		this.buttonSection.setSpacing(20);
		this.buttonSection.setAlignment(Pos.CENTER);
		this.buttonSection.setPadding(new Insets(60, 15, 15, 15));
		this.buttonSection.getChildren().addAll(this.gameExample, this.playButton, this.characterButton,
				this.howToPlayButton, this.settingButton);
	}

	public Button getPlayButton() {
		return playButton;
	}

	public Button getCharacterButton() {
		return characterButton;
	}

	public Button getHowToPlayButton() {
		return howToPlayButton;
	}

	public Button getSettingButton() {
		return settingButton;
	}

	public VBox getButtonSection() {
		return buttonSection;
	}

}
