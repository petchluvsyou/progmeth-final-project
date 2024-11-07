package entity.base;

import javafx.scene.layout.Pane;
// Base class for every entity
public abstract class Entity {
	protected int positionX;
	protected int positionY;
	protected Pane hitBox;

	public Entity() {
		this.setPositionX(200);
		this.setPositionY(300);
	}

	public Entity(int positionX, int positionY) {
		this.setPositionX(positionX);
		this.setPositionY(positionY);
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPositionX() {
		return this.positionX;
	}

	public int getPositionY() {
		return this.positionY;
	}

	public abstract void initializeHitBox();

	public abstract Pane getHitBox();
}
