package gui;

import java.io.File;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RootPane extends StackPane {
	private static HomePane homePane;
	private static PlayPane playPane;
	private static HowToPlayPane howToPlayPane;
	private static CharacterPane characterPane;
	private static SettingPane settingPane;
	private static MediaPlayer backgroundSound;
	
	public RootPane() {
		this.setPrefHeight(750);
		this.setPrefWidth(600);
		homePane = new HomePane();
		playPane = new PlayPane();
		howToPlayPane = new HowToPlayPane();
		characterPane = new CharacterPane();
		settingPane = new SettingPane();
		this.getChildren().addAll(homePane,playPane,howToPlayPane,characterPane,settingPane);
		homePane.toFront();
		setChangeToPlayPane();
		setChangeToCharacterPane();
		setChangeToHowToPlayPane();
		setChangeToHomePane();
		setChangeToSettingPane();
		initializeBackgroundSound();
	}
	
	private void initializeBackgroundSound() {
		String filePath = "src/BackgroundSound.mp3";
        Media media = new Media(new File(filePath).toURI().toString());
        backgroundSound = new MediaPlayer(media);
        backgroundSound.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundSound.play();
	}

	public static HomePane getWelcomePane() {
		return homePane;
	}

	public static PlayPane getPlayPane() {
		return playPane;
	}

	public static HowToPlayPane getHowToPlayPane() {
		return howToPlayPane;
	}

	public static CharacterPane getCharacterPane() {
		return characterPane;
	}
	public static SettingPane getSettingPane() {
		return settingPane;
	}
	
	public void setChangeToPlayPane() {
	    homePane.getPlayButton().setOnAction(event -> {
	    	this.getChildren().remove(playPane);
		    playPane = new PlayPane();
		    playPane.initializePlayer();
		    this.getChildren().add(playPane);
		    playPane.toFront();
		    setChangeToHomePane();
	    });
	}
	public void setChangeToCharacterPane() {
		homePane.getCharacterButton().setOnAction(event -> {
			characterPane.toFront();
		});
	}
	public void setChangeToHowToPlayPane() {
		homePane.getHowToPlayButton().setOnAction(event -> {
			howToPlayPane.toFront();
		});
	}
	public void setChangeToSettingPane() {
		homePane.getSettingButton().setOnAction(event -> {
			settingPane.toFront();
		});
	}
	public void setChangeToHomePane() {
		playPane.getHomeButton().setOnAction(event ->{
			homePane.toFront();
		});
		howToPlayPane.getHomeButton().setOnAction(event ->{
			homePane.toFront();
		});
		characterPane.getHomeButton().setOnAction(event ->{
			homePane.toFront();
		});
		settingPane.getHomeButton().setOnAction(event ->{
			homePane.toFront();
		});
	}
}