package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Utilities {
	public static Random random = new Random();
	public static int totalLaneCount = 0;
	public static ArrayList<ArrayList<Image>> carSkinsFromRight;
	public static ArrayList<ArrayList<Image>> carSkinsFromLeft;
	public static ArrayList<ArrayList<Image>> truckSkinsFromRight;
	public static ArrayList<ArrayList<Image>> truckSkinsFromLeft;
	public static boolean moveUpAble = true;
	public static boolean moveDownAble = true;
	public static boolean moveRightAble = true;
	public static boolean moveLeftAble = true;
	public static int highScore = 0;
	public static ArrayList<Image> obstacleSkins;
	public static int money = 0;

	public static void resetMovable() {
		Utilities.moveLeftAble = true;
		Utilities.moveRightAble = true;
		Utilities.moveUpAble = true;
		Utilities.moveDownAble = true;
	}

	public static void loadVehicleSkin() {
		carSkinsFromRight = new ArrayList<ArrayList<Image>>();
		carSkinsFromLeft = new ArrayList<ArrayList<Image>>();
		truckSkinsFromRight = new ArrayList<ArrayList<Image>>();
		truckSkinsFromLeft = new ArrayList<ArrayList<Image>>();

		Image skin1;
		Image skin2;
		String[] rightCarSkins = { "src/skins/carSkinR1_1.png", "src/skins/carSkinR1_2.png",
				"src/skins/carSkinR2_1.png", "src/skins/carSkinR2_2.png", "src/skins/carSkinR3_1.png",
				"src/skins/carSkinR3_2.png" };
		String[] leftCarSkins = { "src/skins/carSkinL1_1.png", "src/skins/carSkinL1_2.png", "src/skins/carSkinL2_1.png",
				"src/skins/carSkinL2_2.png", "src/skins/carSkinL3_1.png", "src/skins/carSkinL3_2.png" };
		String[] rightTruckSkins = { "src/skins/truckSkinR1_1.png", "src/skins/truckSkinR1_2.png",
				"src/skins/truckSkinR2_1.png", "src/skins/truckSkinR2_2.png", "src/skins/truckSkinR3_1.png",
				"src/skins/truckSkinR3_2.png" };
		String[] leftTruckSkins = { "src/skins/truckSkinL1_1.png", "src/skins/truckSkinL1_2.png",
				"src/skins/truckSkinL2_1.png", "src/skins/truckSkinL2_2.png", "src/skins/truckSkinL3_1.png",
				"src/skins/truckSkinL3_2.png" };

		for (int i = 0; i < rightCarSkins.length / 2; i++) {
			ArrayList<Image> skins1 = new ArrayList<>();
			skin1 = new Image(new File(rightCarSkins[2 * i]).toURI().toString());
			skin2 = new Image(new File(rightCarSkins[2 * i + 1]).toURI().toString());
			skins1.add(skin1);
			skins1.add(skin2);
			carSkinsFromRight.add(skins1);
		}
		for (int i = 0; i < leftCarSkins.length / 2; i++) {
			ArrayList<Image> skins1 = new ArrayList<>();
			skin1 = new Image(new File(leftCarSkins[2 * i]).toURI().toString());
			skin2 = new Image(new File(leftCarSkins[2 * i + 1]).toURI().toString());
			skins1.add(skin1);
			skins1.add(skin2);
			carSkinsFromLeft.add(skins1);
		}
		for (int i = 0; i < leftTruckSkins.length / 2; i++) {
			ArrayList<Image> skins1 = new ArrayList<>();
			skin1 = new Image(new File(leftTruckSkins[2 * i]).toURI().toString());
			skin2 = new Image(new File(leftTruckSkins[2 * i + 1]).toURI().toString());
			skins1.add(skin1);
			skins1.add(skin2);
			truckSkinsFromLeft.add(skins1);
		}
		for (int i = 0; i < rightTruckSkins.length / 2; i++) {
			ArrayList<Image> skins1 = new ArrayList<>();
			skin1 = new Image(new File(rightTruckSkins[2 * i]).toURI().toString());
			skin2 = new Image(new File(rightTruckSkins[2 * i + 1]).toURI().toString());
			skins1.add(skin1);
			skins1.add(skin2);
			truckSkinsFromRight.add(skins1);
		}
	}

	public static void loadObstacleSkin() {
		obstacleSkins = new ArrayList<>();
		String[] obstacleImages = { "src/skins/Obstacle4.gif", "src/skins/Obstacle1.gif", "src/skins/Obstacle2.png",
				"src/skins/Obstacle3.png", "src/skins/Obstacle5.gif", "src/skins/Obstacle6.gif",
				"src/skins/Obstacle9.gif" };
		for (String imagePath : obstacleImages) {
			obstacleSkins.add(new Image(new File(imagePath).toURI().toString()));
		}

	}

	public static void playCoinSound() {
		String filePath = "src/coin.mp3";
		Media coinMedia = new Media(new File(filePath).toURI().toString());
		MediaPlayer coinSound = new MediaPlayer(coinMedia);
		coinSound.play();
	}
}