package entity.derived;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import utils.Utilities;

public class Car extends Vehicle {
    public Car(int positionX, int positionY, int direction) {
        super(positionX, positionY, direction);
        this.initializeHitBox();
        this.setImageView();
    }

    public void setImageView() {
        this.imageViews = new ArrayList<ImageView>();
        int randomCar = Utilities.random.nextInt(Utilities.carSkinsFromLeft.size());
        if (this.direction == 0) {
            this.imageViews.add(setImageForCar(0,randomCar,0));
            this.imageViews.add(setImageForCar(0,randomCar,1));
        } else {
            this.imageViews.add(setImageForCar(1,randomCar,0));
            this.imageViews.add(setImageForCar(1,randomCar,1));
        }
    }

    private ImageView setImageForCar(int direction, int randomCar, int number) {
        ImageView image1;
        if (direction == 0) {
            image1 = new ImageView(Utilities.carSkinsFromLeft.get(randomCar).get(number));
        } else {
            image1 = new ImageView(Utilities.carSkinsFromRight.get(randomCar).get(number));
        }
        image1.setFitHeight(50);
        image1.setFitWidth(100);
        return image1;
    }

    @Override
    public void initializeHitBox() {
        this.hitBox = new Pane();
        this.hitBox.setPrefHeight(50);
        this.hitBox.setPrefWidth(100);
    }

    @Override
    public Pane getHitBox() {
        return this.hitBox;
    }

}