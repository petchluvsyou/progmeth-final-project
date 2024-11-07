package application;

import gui.RootPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utils.Utilities;

public class Main extends Application {
	private static Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Utilities.loadVehicleSkin();
		Utilities.loadObstacleSkin();
		StackPane root = new RootPane();
		scene = new Scene(root);
		scene.getStylesheets().add("stylesheet.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("Crozzy Furry");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Scene getScene() {
		return scene;
	}
}