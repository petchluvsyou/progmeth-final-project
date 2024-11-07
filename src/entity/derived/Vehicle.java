package entity.derived;

import java.util.ArrayList;
import entity.base.Entity;
import entity.base.Moveable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Vehicle extends Entity implements Moveable {
	protected Pane hitBox;
	protected ArrayList<ImageView> imageViews;
	protected int direction;

	public Vehicle(int positionX, int positionY, int direction) {
		super(positionX, positionY);
		this.setDirection(direction);
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public abstract void setImageView();

	public ArrayList<ImageView> getImageView() {
		return this.imageViews;
	}

	@Override
	public void move(int speed) {
		this.setPositionX(this.getPositionX() + speed);
	}
}