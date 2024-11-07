package gui;

import java.util.ArrayList;
import entity.derived.Car;
import entity.derived.Obstacle;
import entity.derived.Truck;
import entity.derived.Vehicle;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import utils.Utilities;
// This class create lanes in PlaySection
public class Lane extends Pane {
	private int index;
	private int positionX;
	private int positionY;
	private int laneSpeed;
	private int type;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Obstacle> obstacles;
	private int changeCarSkinTimer;

	public Lane() {
		this.positionX = 0;
		this.changeCarSkinTimer = 0;
		this.setIndex();
		this.setPrefHeight(50);
		this.setPrefWidth(450);
		this.setPositionY(this.index);
		this.setType();
		this.setLaneSpeed();
		this.setBorder(null);
		this.initializeVehicles();
		this.placeVehicles();
		this.initializeObstacles();
		this.placeObstacles();
		this.setBackgroundImage();
	}

	private void setIndex() {
		this.index = Utilities.totalLaneCount;
		Utilities.totalLaneCount++;
	}

	public void updateLane() { // This method control vehicles in the lane
		this.positionY ++;
		this.changeCarSkinTimer++;
		for (Vehicle vehicle : this.vehicles) {
			vehicle.move(this.laneSpeed);
			if (this.laneSpeed > 0 && vehicle.getPositionX() > this.getWidth()) {
				this.getChildren().remove(vehicle.getHitBox());
				this.getChildren().remove(vehicle.getImageView().get(0));
				this.getChildren().remove(vehicle.getImageView().get(1));
			} else if (this.laneSpeed < 0 && vehicle.getPositionX() < -150) {
				this.getChildren().remove(vehicle.getHitBox());
				this.getChildren().remove(vehicle.getImageView().get(0));
				this.getChildren().remove(vehicle.getImageView().get(1));
			} else {
				vehicle.getImageView().get(0).setLayoutX(vehicle.getPositionX());
				vehicle.getImageView().get(1).setLayoutX(vehicle.getPositionX());
				if (this.changeCarSkinTimer % 5 == 0) {
					this.changeCarSkinTimer = 0;
					if (vehicle.getImageView().get(0).isVisible()) {
						vehicle.getImageView().get(1).setVisible(true);
						vehicle.getImageView().get(0).setVisible(false);
					} else {
						vehicle.getImageView().get(1).setVisible(false);
						vehicle.getImageView().get(0).setVisible(true);
					}
				}

			}

		}
		for (Obstacle obstacle : this.obstacles) {
			if (obstacle.isCollectable() && obstacle.getCollected()) {
				this.getChildren().remove(obstacle.getHitBox());
				this.getChildren().remove(obstacle.getImageView());
			}
		}

	}

	public int getPositionX() {
		return this.positionX;
	}

	public int getPositionY() {
		return this.positionY;
	}

	private void setPositionY(int index) {
		if (index <= 10) {
			this.positionY = 500 - 25 - this.index * 50 - 25;
		} else {
			this.positionY = -99;
		}
	}

	public int getIndex() {
		return this.index;
	}

	private void setLaneSpeed() {
		if (this.type >= 1 && this.type < 4) {
			int direction = Utilities.random.nextInt(2);
			int speed = Utilities.random.nextInt(SettingPane.getMinLaneSpeed(SettingPane.getCurrentMode()),
					SettingPane.getMaxLaneSpeed(SettingPane.getCurrentMode()));
			if (direction == 0) {
				this.laneSpeed = -speed;
			} else
				this.laneSpeed = speed;

		} else
			this.laneSpeed = 0;

	}

	private void setBackgroundImage() { // This method set each lane background
		if (this.type >= 1 && this.type < 4) {
			Image image = new Image("skins/road.png");
			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			this.setBackground(new Background(backgroundImage));
		} else if (this.index == 2) {
			Image image = new Image("skins/specialGrass.png");
			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			this.setBackground(new Background(backgroundImage));
		} else {
			Image image = new Image("skins/grass.png");
			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			this.setBackground(new Background(backgroundImage));
		}
	}

	private void setType() {
		if (this.index < 4)
			this.type = 0;
		else
			this.type = Utilities.random.nextInt(1, 5);
	}

	private void initializeVehicles() { // This method randomly choose car and truck and place them in different positions
		this.vehicles = new ArrayList<Vehicle>();
		int random = Utilities.random.nextInt(2, 8);
		if (this.laneSpeed < 0) {
			for (int i = 0; i <= 10; i++) {
				if (Utilities.random.nextBoolean())
					this.vehicles.add(new Truck(450 + 150 * i * random, 0, 1));
				else
					this.vehicles.add(new Car(450 + 150 * i * random, 0, 1));
			}
		} else if (this.laneSpeed > 0) {
			for (int i = 0; i <= 10; i++) {
				if (Utilities.random.nextBoolean())
					this.vehicles.add(new Truck(-150 - 150 * i * random, 0, 0));
				else
					this.vehicles.add(new Car(-100 - 150 * i * random, 0, 0));
			}
		}
	}

	private void placeVehicles() {
		for (Vehicle vehicle : this.vehicles) {
			vehicle.getHitBox().setLayoutX(vehicle.getPositionX());
			vehicle.getHitBox().setLayoutY(0);
			vehicle.getImageView().get(0).setLayoutX(vehicle.getPositionX());
			vehicle.getImageView().get(1).setLayoutX(vehicle.getPositionX());
			vehicle.getImageView().get(0).setLayoutY(0);
			vehicle.getImageView().get(1).setLayoutY(0);
			vehicle.getImageView().get(0).setVisible(false);
			this.getChildren().addAll(vehicle.getHitBox(), vehicle.getImageView().get(0),
					vehicle.getImageView().get(1));
		}
	}

	private void placeObstacles() {
		for (Obstacle obstacle : this.obstacles) {
			obstacle.getHitBox().setLayoutX(obstacle.getPositionX());
			obstacle.getHitBox().setLayoutY(obstacle.getPositionY());
			obstacle.getImageView().setLayoutX(obstacle.getPositionX());
			obstacle.getImageView().setLayoutY(0);
			this.getChildren().addAll(obstacle.getHitBox(), obstacle.getImageView());
		}
	}

	private void initializeObstacles() {
		this.obstacles = new ArrayList<Obstacle>();
		if (this.type == 4) {
			int numberOfObstacle = Utilities.random.nextInt(1, 5);
			if (Utilities.random.nextBoolean()) {
				int distanceX = 0;
				for (int i = 0; i < numberOfObstacle; i++) {
					this.obstacles.add(new Obstacle(distanceX, 0, Utilities.random.nextBoolean()));
					distanceX += Utilities.random.nextInt(1, 5) * 50;
				}
			} else {
				int distanceX = 450;
				for (int i = 0; i < numberOfObstacle; i++) {
					this.obstacles.add(new Obstacle(distanceX, 0, Utilities.random.nextBoolean()));
					distanceX -= Utilities.random.nextInt(1, 5) * 50;
				}
			}
		}
	}

	public ArrayList<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public int getType() {
		return this.type;
	}

	public ArrayList<Obstacle> getObstacles() {
		return this.obstacles;
	}

}