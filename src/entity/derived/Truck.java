package entity.derived;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utils.Utilities;

public class Truck extends Vehicle {

	public Truck(int positionX, int positionY, int direction) {
		super(positionX, positionY, direction);
		this.initializeHitBox();
		this.setImageView();
	}

	public void setImageView() {
		this.imageViews = new ArrayList<ImageView>();
		int randomCar = Utilities.random.nextInt(Utilities.truckSkinsFromLeft.size());
		if (this.direction == 0) {
			this.imageViews.add(setImageForTruck(0, randomCar, 0));
			this.imageViews.add(setImageForTruck(0, randomCar, 1));
		} else {
			this.imageViews.add(setImageForTruck(1, randomCar, 0));
			this.imageViews.add(setImageForTruck(1, randomCar, 1));
		}
	}

	private ImageView setImageForTruck(int direction, int randomCar, int number) {
		ImageView image1;
		if (direction == 0) {
			image1 = new ImageView(Utilities.truckSkinsFromLeft.get(randomCar).get(number));
		} else {
			image1 = new ImageView(Utilities.truckSkinsFromRight.get(randomCar).get(number));
		}
		image1.setFitHeight(50);
		image1.setFitWidth(150);
		return image1;
	}

	@Override
	public void initializeHitBox() {
		this.hitBox = new Pane();
		this.hitBox.setPrefHeight(50);
		this.hitBox.setPrefWidth(150);
	}

	@Override
	public Pane getHitBox() {
		return this.hitBox;
	}

}