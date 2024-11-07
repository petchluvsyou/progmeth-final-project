package entity.derived;

import entity.base.Entity;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utils.Utilities;

public class Obstacle extends Entity {

	protected ImageView imageView;
	private boolean collectable;
	private boolean collected;

	public Obstacle(int positionX, int positionY, boolean collectable) {
		super(positionX, positionY);
		this.collectable = collectable;
		this.initializeHitBox();
		this.setImageView();
		this.collected = false;

	}

	@Override
	public void initializeHitBox() {
		this.hitBox = new Pane();
		this.hitBox.setPrefHeight(50);
		this.hitBox.setPrefWidth(50);
	}

	public void setImageView() {
		this.imageView = new ImageView(
				Utilities.obstacleSkins.get(Utilities.random.nextInt(1, Utilities.obstacleSkins.size())));
		if (this.collectable) {
			this.imageView = new ImageView(Utilities.obstacleSkins.get(0));
		}
		this.imageView.setFitHeight(50);
		this.imageView.setFitWidth(50);
	}

	public ImageView getImageView() {
		return this.imageView;
	}

	@Override
	public Pane getHitBox() {
		return this.hitBox;
	}

	public boolean isCollectable() {
		return this.collectable;
	}

	public void setCollected() {
		this.collected = true;
	}

	public boolean getCollected() {
		return this.collected;
	}
}