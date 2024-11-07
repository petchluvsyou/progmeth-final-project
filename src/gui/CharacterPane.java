package gui;

import java.io.File;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utils.Utilities;
// This class create Pane when clicked Character Button in HomePane
public class CharacterPane extends VBox {
	private Button homeButton;
	private FlowPane characterList;
	private Text header;
	private Text selectedCharacterText;
	private static ArrayList<ArrayList<String>> skinList;
	private ArrayList<Boolean> boughtSkins;
	private static int currentSkinNumber;
	private static Text moneyText;

	public CharacterPane() {
		setSkinList();
		this.setPrefHeight(750);
		this.setPrefWidth(600);
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(15));
		this.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 222), null, null)));
		initializeHeader();
		initializeSelectedCharacterText();
		initializeMoneyText();
		updateMoneyText();
		this.initializeBoughtSkins();
		this.initializeCharacterList();
		this.initializeHomeButton();
		this.updateSelectedCharacterText();
		this.getChildren().addAll(this.header, this.selectedCharacterText, this.characterList, moneyText,
				this.homeButton);
	}

	private void initializeMoneyText() {
		moneyText = new Text("Owned money: " + Utilities.money);
	}

	public static void updateMoneyText() {
		moneyText.setText("Owned money: " + Utilities.money);
	}

	private void initializeBoughtSkins() {
		this.boughtSkins = new ArrayList<>();
		for (int i = 0; i < skinList.size(); i++) {
			if(skinList.get(i).get(5).equals("Free")) this.boughtSkins.add(true);
			else this.boughtSkins.add(false);
		}
	}

	private void addSkin(String skinFacingW, String skinFacingA, String skinFacingS, String skinFacingD,
			String skinName, String skinPrice) {
		ArrayList<String> skin = new ArrayList<>();
		skin.add(skinFacingW);
		skin.add(skinFacingA);
		skin.add(skinFacingS);
		skin.add(skinFacingD);
		skin.add(skinName);
		skin.add(skinPrice);
		skinList.add(skin);
	}

	private void setSkinList() {
		skinList = new ArrayList<>();
		addSkin("src/skins/poonW.png", "src/skins/poonA.png", "src/skins/poonS.png", "src/skins/poonD.png", "Bag",
				"Free");
		addSkin("src/skins/DudeA.png", "src/skins/DudeA.png", "src/skins/DudeA.png", "src/skins/DudeA.png", "Original",
				"Free");
		addSkin("src/skins/SkinCs_2.png", "src/skins/skinca_2.png", "src/skins/skinCw_2.png", "src/skins/skinCd_2.png",
				"bro", "10");
		addSkin("src/skins/MilosW.png", "src/skins/MilosA.png", "src/skins/MilosS.png", "src/skins/MilosD.png", "P'Toh",
				"10");
		addSkin("src/skins/Obstacle9.gif", "src/skins/Obstacle9.gif", "src/skins/Obstacle9.gif",
				"src/skins/Obstacle9.gif", "Happi", "20");
		addSkin("src/skins/SkinAmoungUsw.png", "src/skins/SkinAmoungUsa.png", "src/skins/SkinAmoungUss.png", "src/skins/SkinAmoungUsd.png",
				"AMOGUS", "20");
		addSkin("src/skins/Player.jpeg", "src/skins/Player.jpeg", "src/skins/Player.jpeg", "src/skins/Player.jpeg",
				"???", "100");
	}

	public static String getSkinList(int skinIndex, int skinFace) {
		return skinList.get(skinIndex).get(skinFace);
	}

	private void initializeHeader() {
		this.header = new Text("Choose Your\nCharacter");
		this.header.setFill(Color.BLACK);
		this.header.setTextAlignment(TextAlignment.CENTER);
		this.header.setFont(Font.font("Impact", FontWeight.BOLD, 60));
	}

	private void initializeSelectedCharacterText() {
		this.selectedCharacterText = new Text("Selected Character: " + skinList.get(getCurrentSkinNumber()).get(4));
	}

	private void updateSelectedCharacterText() { // Show the currently selected character and highlight its box
		this.selectedCharacterText.setText("Selected Character: " + skinList.get(getCurrentSkinNumber()).get(4));
		this.selectedCharacterText.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		int i = 0;
		for (Node characterBox : this.characterList.getChildren()) {
			if (i == getCurrentSkinNumber()) {
				((VBox) characterBox).setBorder(new Border(
						new BorderStroke(Color.rgb(73, 69, 19), null, CornerRadii.EMPTY, null, new Insets(10))));
				((VBox) characterBox)
						.setBackground(new Background(new BackgroundFill(Color.rgb(255, 187, 198), null, null)));

			} else {
				((VBox) characterBox).setBorder(new Border(new BorderStroke(Color.rgb(200, 200, 222),
						BorderStrokeStyle.SOLID, CornerRadii.EMPTY, null, new Insets(10))));
				((VBox) characterBox).setBackground(null);
			}
			i++;
		}
	}

	private void initializeCharacterList() {
		this.characterList = new FlowPane();
		this.characterList.setAlignment(Pos.CENTER);
		this.characterList.setPadding(new Insets(20, 0, 20, 0));
		for (int i = 0; i < skinList.size(); i++) {
			this.characterList.getChildren().addAll(createCharacterBox(skinList.get(i).get(2), i));
		}
	}

	private VBox createCharacterBox(String filePath, int skinNumber) { // Create a new character box
		String characterName = skinList.get(skinNumber).get(4);
		VBox characterBox = new VBox();
		Image characterImage = new Image(new File(filePath).toURI().toString());
		ImageView imageView = new ImageView(characterImage);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		characterBox.setAlignment(Pos.CENTER);
		characterBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			handleCharacterBoxClick(characterBox, skinNumber);
		});
		characterBox.getChildren().addAll(imageView, new Text(characterName),
				new Text(skinList.get(skinNumber).get(5)));
		return characterBox;
	}

	private void handleCharacterBoxClick(VBox characterBox, int skinNumber) {
		if (this.boughtSkins.get(skinNumber)) {
			setCurrentSkinNumber(skinNumber);
			updateSelectedCharacterText();
		} else if (Utilities.money >= Integer.parseInt(skinList.get(skinNumber).get(5))) {
			Utilities.money -= Integer.parseInt(skinList.get(skinNumber).get(5));
			updateMoneyText();
			this.boughtSkins.set(skinNumber, true);
			if (characterBox.getChildren() != null) {
				characterBox.getChildren().remove(2);
				characterBox.getChildren().add(new Text("Owned"));
			}
			setCurrentSkinNumber(skinNumber);
			updateSelectedCharacterText();
		}
	}

	public void initializeHomeButton() {
		this.homeButton = new Button("Back to Home");
		this.homeButton.setId("homeButton");
		this.homeButton.getStyleClass().add("homeButton");
	}

	public Button getHomeButton() {
		return homeButton;
	}

	public static int getCurrentSkinNumber() {
		return CharacterPane.currentSkinNumber;
	}

	public static void setCurrentSkinNumber(int currentSkinNumber) {
		CharacterPane.currentSkinNumber = currentSkinNumber;
	}

}