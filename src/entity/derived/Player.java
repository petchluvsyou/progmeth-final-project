package entity.derived;

import java.io.File;
import application.Main;
import entity.base.Entity;
import entity.base.Facing;
import entity.base.Moveable;
import gui.PlayPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utils.Utilities;

public class Player extends Entity implements Moveable {
	private Facing facing;
	private boolean isMovingUp;
	private boolean isMovingDown;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private int score;
	private int maxScore;
	private Pane hitBox;

	public Player() {
		super();
		this.setFacing(Facing.W);
		this.isMovingUp = false;
		this.isMovingDown = false;
		this.isMovingLeft = false;
		this.isMovingRight = false;
		initializeHitBox();
		setScore(0);
		setMaxScore(0);

	}

	public Player(int positionX, int positionY) {
		super(positionX, positionY);
		this.setFacing(Facing.W);
		this.isMovingUp = false;
		this.isMovingDown = false;
		this.isMovingLeft = false;
		this.isMovingRight = false;
		initializeHitBox();
		setScore(0);
		setMaxScore(0);
	}

	public void moveKeyPress() {
		Main.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			handleKeyPress(event.getCode());
		});

		Main.getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
			handleKeyRelease(event.getCode());
		});
	}

	public void move(int speed) {
		this.setPositionY(this.getPositionY() + speed);
	}

	public void playJumpingSound() {
		String filePath = "src/JumpingSound.mp3";
		Media jumpingMedia = new Media(new File(filePath).toURI().toString());
		MediaPlayer jumpingSound = new MediaPlayer(jumpingMedia);
		jumpingSound.play();
	}

	private void handleKeyPress(KeyCode keyCode) {
		boolean isMovingAll = isMovingUp || isMovingDown || isMovingLeft || isMovingRight;
		if (keyCode == KeyCode.W) {
			if (!isMovingAll && getPositionY() - 50 >= 0 && PlayPane.getStartedStatus() && Utilities.moveUpAble
					&& !PlayPane.getIsGameOver() && PlayPane.getPlayer().equals(this)) {
				playJumpingSound();
				score += 1;
				Utilities.resetMovable();
				if (score > maxScore)
					maxScore = score;
				facing = Facing.W;
				setPositionY(getPositionY() - 50);
			}
			isMovingUp = true;
		} else if (keyCode == KeyCode.S) {
			if (!isMovingAll && getPositionY() + 50 < 500 && PlayPane.getStartedStatus() && Utilities.moveDownAble
					&& !PlayPane.getIsGameOver() && PlayPane.getPlayer().equals(this)) {
				playJumpingSound();
				score -= 1;
				facing = Facing.S;
				Utilities.resetMovable();
				setPositionY(getPositionY() + 50);
			}
			isMovingDown = true;
		} else if (keyCode == KeyCode.A) {
			if (!isMovingAll && getPositionX() - 50 >= 0 && PlayPane.getStartedStatus() && Utilities.moveLeftAble
					&& !PlayPane.getIsGameOver() && PlayPane.getPlayer().equals(this)) {
				playJumpingSound();
				facing = Facing.A;
				Utilities.resetMovable();
				setPositionX(getPositionX() - 50);
			}
			isMovingLeft = true;
		} else if (keyCode == KeyCode.D) {
			if (!isMovingAll && getPositionX() + 50 < 450 && PlayPane.getStartedStatus() && Utilities.moveRightAble
					&& !PlayPane.getIsGameOver() && PlayPane.getPlayer().equals(this)) {
				playJumpingSound();
				facing = Facing.D;
				Utilities.resetMovable();
				setPositionX(getPositionX() + 50);
			}
			isMovingRight = true;
		}
	}

	private void handleKeyRelease(KeyCode keyCode) {
		if (keyCode == KeyCode.W) {
			isMovingUp = false;
		} else if (keyCode == KeyCode.S) {
			isMovingDown = false;
		} else if (keyCode == KeyCode.A) {
			isMovingLeft = false;
		} else if (keyCode == KeyCode.D) {
			isMovingRight = false;
		}
	}

	public void initializeHitBox() {
		this.hitBox = new Pane();
		this.hitBox.setPrefHeight(2);
		this.hitBox.setPrefWidth(30);
	}

	public Pane getHitBox() {
		return this.hitBox;
	}

	@Override
	public void setPositionX(int positionX) {
		if (positionX < 450 && positionX >= 0)
			this.positionX = positionX;
		else
			this.positionX = 200;
	}

	public void setPositionY(int positionY) {
		if (positionY < 500 && positionY >= 0)
			this.positionY = positionY;
		else
			this.positionY = 500;
	}

	public Facing getFacing() {
		return this.facing;
	}

	public void setFacing(Facing facing) {
		this.facing = facing;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
}