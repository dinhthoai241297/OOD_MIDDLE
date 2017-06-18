package pdt.ood.views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pdt.ood.models.MYCONS;

public class LogRegScene extends Application {

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(MYCONS.SCENE_LOGREG));
			stage.getIcons().add(new Image(MYCONS.IMG_LOGREG));
			stage.setTitle("pdt.ood.middle");
			stage.setResizable(false);
			stage.setScene(new Scene(root, 340, 540));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
