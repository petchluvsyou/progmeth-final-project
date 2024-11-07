package gui;

import java.io.File;
import java.util.ArrayList;
import entity.base.Facing;
import entity.derived.Obstacle;
import entity.derived.Player;
import entity.derived.Vehicle;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.Utilities;
//This class create Pane when clicked Play button in HomePane
public class PlayPane extends BorderPane {
	private ArrayList<Lane> lanes;
	private Button homeButton;
	private Pane playSection;
	private static boolean isGameOver;
	private ImageView playerSkin;
	private static Player player;
	private static Image[] skinImages;
	private HBox topSection;
	private Text scoreText;
	private Text highScoreText;
	private VBox bottomSection;
	private Text youDiedText;
	private Text moneyText;
	private ImageView gameOverImageView;
	private static boolean startedStatus;
	private Button startGameButton;

	public PlayPane() {
		this.setPrefHeight(750);
		this.setPrefWidth(600);
		isGameOver = false;
		startedStatus = false;
		skinImages = new Image[4];
		this.setBackground(new Background(new BackgroundFill(Color.valueOf("#EDEBE3"), null, null)));
		Utilities.totalLaneCount = 0;
		this.lanes = new ArrayList<Lane>();
		this.initializeTopSection();
		this.initializeLanes();
		this.initializeBottomSection();
		this.initializePlaySection();
		this.setTop(this.topSection);
		this.setCenter(this.playSection);
		this.setBottom(this.bottomSection);

	}

	private void initializeBottomSection() {
	    this.bottomSection = new VBox(); 
	    this.bottomSection.setAlignment(Pos.TOP_CENTER);
	    this.bottomSection.setPrefHeight(140);
	    this.bottomSection.setPrefWidth(this.getWidth());
	    this.bottomSection.setSpacing(10);
	    this.initializeStartGameButton();
	    this.initializeYouDiedText();
	    this.initializeHomeButton();
	    this.bottomSection.getChildren().addAll(startGameButton, youDiedText, homeButton);
	    
	}

	private void initializeYouDiedText() {
		this.youDiedText = new Text("You died. Press the Button to go back to game menu.");
		this.youDiedText.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
		this.youDiedText.setFill(Color.RED);
		this.youDiedText.setVisible(false);
	}

	private void initializeStartGameButton() {
		this.startGameButton = new Button("Start");
		this.startGameButton.setOnAction(event -> {
			this.startGame();
			this.homeButton.setVisible(false);
			this.startGameButton.setVisible(false);
		    });
		this.startGameButton.setPrefWidth(160);
	}

	private void initializeTopSection() {
		this.topSection = new HBox();
		this.topSection.setAlignment(Pos.BOTTOM_CENTER);
		this.topSection.setSpacing(30);
		this.topSection.setPrefHeight(40);
		this.initializeScoreSection();
		this.initializeMoneyText();
		this.initializeHighScoreSection();
		this.topSection.getChildren().addAll(scoreText, highScoreText,moneyText);
	}

	private void initializeMoneyText() {
		// TODO Auto-generated method stub
		this.moneyText = new Text("|   Money: "+ Integer.toString(Utilities.money));
		this.moneyText.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
	}

	private void initializeHighScoreSection() {
		this.highScoreText = new Text("|   High Score: "+Utilities.highScore);
		this.highScoreText.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
	}

	private void initializeScoreSection() {
		this.scoreText = new Text("Score: 0");
		this.scoreText.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
	}

	private void initializeHomeButton() {
		this.homeButton = new Button("Back to Home");
		this.homeButton.setId("homeButton");
		this.homeButton.getStyleClass().add("homeButton");
	}

	public Button getHomeButton() {
		return this.homeButton;
	}

	private void initializeLanes() {
		for (int i = 0; i <= 10; i++) {
			this.lanes.add(new Lane());
		}
	}

	public static boolean getIsGameOver() {
		return(isGameOver);
	}
	
	public static void setIsGameOver(boolean status) {
		isGameOver = status;
	}

	private void checkCollide() {//this method check if player collided with any obstacle or vehicle
		isGameOver = false;
		if (player.getPositionY() == 500)
			isGameOver = true;
		Bounds playerBound = player.getHitBox().localToScene(player.getHitBox().getBoundsInLocal());//creating bounds for player hit box
		for (Lane lane : this.lanes) {
			if (lane.getIndex() - 3 - player.getScore() == 0) {// check if player and the lane is in the same y position
				if (lane.getType() <= 3 && lane.getType() >= 1) {
					for (Vehicle vehicle : lane.getVehicles()) {
						if (vehicle.getPositionX() > 0 && vehicle.getPositionX() < 450) {
							Bounds vehicleBound = vehicle.getHitBox()
									.localToScene(vehicle.getHitBox().getBoundsInLocal());
							if (playerBound.intersects(vehicleBound)) {//check if player bounds collide with vehicle bounds
								isGameOver = true;
								break;
							}
						}
					}
				} else if (lane.getType() == 4) {// check if lane is grass
					for (Obstacle obstacle : lane.getObstacles()) {
						if (!obstacle.isCollectable()) {//check if the obstacle is collectible or not
							if (obstacle.getPositionX() + 50 == player.getPositionX()) {
								Utilities.moveLeftAble = false;
							} else if (obstacle.getPositionX() - 50 == player.getPositionX()) {
								Utilities.moveRightAble = false;
							}
						}else {//if collectible check if player collected the crown
							Bounds obstacleBound = obstacle.getHitBox().localToScene(obstacle.getHitBox().getBoundsInLocal());
							if(playerBound.intersects(obstacleBound)) {
								Utilities.money++;
								obstacle.setCollected();
								Utilities.playCoinSound();
								moneyText.setText("|   Money: "+ Integer.toString(Utilities.money));
							}
						}
					}
				}

			} else if (isGameOver)
				break;
			else if (Math.abs(lane.getIndex() - 3 - player.getScore()) == 1 && lane.getType() == 4) {// check if lane is grass and is either above or below player
				if (lane.getIndex() - 3 > player.getScore()) {// check if lane is above player
					for (Obstacle obstacle : lane.getObstacles()) {
						if (obstacle.getPositionX() == player.getPositionX() && !obstacle.isCollectable()) {
							Utilities.moveUpAble = false;
							break;
						}
					}
				} else {// check if lane is below player
					for (Obstacle obstacle : lane.getObstacles()) {
						if (obstacle.getPositionX() == player.getPositionX() && !obstacle.isCollectable()) {
							Utilities.moveDownAble = false;
						}
					}
				}
			}

		}

	}
	
	private void initializePlaySection() {
		this.playSection = new Pane();
		this.playSection.setMaxHeight(500);
		this.playSection.setMaxWidth(450);
		this.playSection.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		for (Lane lane : this.lanes) {
			placeLane(lane);
		}
		this.playSection.setClip(new Rectangle(this.playSection.getMaxWidth(), this.playSection.getMaxHeight()));
	}

	private void placeLane(Lane lane) {//place Lanes in the playSection
		if (lane.getPositionY() <= 450 && lane.getPositionY() >= 0) {
			lane.setLayoutX(lane.getPositionX());
			lane.setLayoutY(lane.getPositionY());
			this.playSection.getChildren().add(lane);
		}

	}

	private void updateLanes() {//update position of the lanes and their elements
		ArrayList<Lane> LANES = this.lanes;
		Pane playSection = this.playSection;
		for (Lane lane : LANES) {
			Platform.runLater(() -> {
				if (lane.getPositionY() == -50 && !playSection.getChildren().contains(lane)) {//if the most top lane move through the top, create new lane 
					lane.setLayoutY(lane.getPositionY());
					playSection.getChildren().add(lane);
					LANES.add(new Lane());
					player.getHitBox().toFront();
					playerSkin.toFront();
				}
				lane.updateLane();

				if (lane.getPositionY() > 500) {//if the lane has already move out of the playSection remove it
					playSection.getChildren().remove(lane);

				} else {// if lane is in the playSection, update the vehicles' position
					lane.setLayoutY(lane.getPositionY());
					for (Vehicle vehicle : lane.getVehicles()) {
						vehicle.getHitBox().setLayoutX(vehicle.getPositionX());
					}
				}
			});
		}

		this.playSection = playSection;
		this.lanes = LANES;
	}

	private void startGame() {//game loop
		Thread start = new Thread(() -> {
			player.moveKeyPress();
			startedStatus = true;
			while (!isGameOver) {
				this.updateLanes();
				this.updatePlayerAndPlayerSkin(player, this.playerSkin);
				this.updateScore();
				this.checkCollide();
				try {
					Thread.sleep(20);			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.initializeGameOverImage();
			this.playGameOverSound();
			CharacterPane.updateMoneyText();
			this.youDiedText.setVisible(true);
			this.homeButton.setVisible(true);
		});
		start.setDaemon(true);
		start.start();
		while (isGameOver) {
			if (start != null)
				start.interrupt();
		}
	}

	private void updateScore() {
		this.getScoreText().setText("Score: " + player.getMaxScore());
		this.highScoreText.setText("|   High Score: "+Utilities.highScore);
		
		if (Utilities.highScore < player.getMaxScore()) Utilities.highScore = player.getMaxScore();
	}

	private void updatePlayerAndPlayerSkin(Player player, ImageView playerSkin) {
		this.updatePlayerImage();
		player.move(1);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				player.getHitBox().setLayoutX(player.getPositionX() + 10);
				player.getHitBox().setLayoutY(player.getPositionY() + 24);
				playerSkin.setLayoutX(player.getPositionX());
				playerSkin.setLayoutY(player.getPositionY());
			}

		});
	}

	public void initializePlayer() {
		loadPlayerSkin();
		this.playerSkin = new ImageView(skinImages[0]);
		this.playerSkin.setFitHeight(50);
		this.playerSkin.setFitWidth(50);
		player = new Player();
		this.playerSkin.setLayoutX(player.getPositionX());
		this.playerSkin.setLayoutY(player.getPositionY());
		player.getHitBox().setLayoutX(player.getPositionX() + 10);
		player.getHitBox().setLayoutY(player.getPositionY() + 24);
		this.playSection.getChildren().addAll(player.getHitBox(), playerSkin);
	}
	
	private static void loadPlayerSkin() {
		String skinPath = CharacterPane.getSkinList(CharacterPane.getCurrentSkinNumber(), 0);
		skinImages[0] = new Image(new File(skinPath).toURI().toString());
		skinPath = CharacterPane.getSkinList(CharacterPane.getCurrentSkinNumber(), 1);
		skinImages[1] = new Image(new File(skinPath).toURI().toString());
		skinPath = CharacterPane.getSkinList(CharacterPane.getCurrentSkinNumber(), 2);
		skinImages[2] = new Image(new File(skinPath).toURI().toString());
		skinPath = CharacterPane.getSkinList(CharacterPane.getCurrentSkinNumber(), 3);
		skinImages[3] = new Image(new File(skinPath).toURI().toString());
	}

	private void updatePlayerImage() {
		int direction = 0;
		if (player.getFacing().equals(Facing.W))
			direction = 0;
		else if (player.getFacing().equals(Facing.A))
			direction = 1;
		else if (player.getFacing().equals(Facing.S))
			direction = 2;
		else if (player.getFacing().equals(Facing.D))
			direction = 3;
		playerSkin.setImage(skinImages[direction]);
	}
	
	private void playGameOverSound() {
		String filePath = "src/GameOverSound.mp3";
        Media gameOverMedia = new Media(new File(filePath).toURI().toString());
        MediaPlayer gameOverSound = new MediaPlayer(gameOverMedia);
        gameOverSound.play();
	}
	
	private void initializeGameOverImage() {
	    Platform.runLater(() -> {
	        String filePath = "src/gameOverImage.gif";
	        Image gameOverImage = new Image(new File(filePath).toURI().toString());
	        this.gameOverImageView = new ImageView(gameOverImage);
	        this.playSection.getChildren().add(gameOverImageView);
	        this.gameOverImageView.setLayoutX(0);
	        this.gameOverImageView.setLayoutY(0);
	        this.gameOverImageView.setFitWidth(450);
	        this.gameOverImageView.setFitHeight(500);
	    });
	}

	public static Player getPlayer() {
		return player;
	}

	public Text getScoreText() {
		return this.scoreText;
	}
	
	public static boolean getStartedStatus() {
		return startedStatus;
	}
	
}