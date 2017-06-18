package pdt.ood.views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pdt.ood.controllers.AdminController;
import pdt.ood.models.User;

public class MainAdmin extends Application {
	
	private Stage stage;
	private User u;

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/pdt/ood/views/Dash.fxml"));
			stage.setTitle("DashBoard");
			stage.setScene(new Scene(root, 990,490));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public MainAdmin(User u) {
		this.u = u;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pdt/ood/views/MainAdmin.fxml"));
			Parent root = (Parent) loader.load();
			AdminController dc = (AdminController) loader.getController();
			stage = new Stage();
			dc.setInit(u, stage);
			stage.setTitle("DashBoard");
			stage.setScene(new Scene(root, 990,490));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MainAdmin(Stage stage, User u) {
		this.u = u;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pdt/ood/views/MainAdmin.fxml"));
			Parent root = (Parent) loader.load();
			AdminController dc = (AdminController) loader.getController();
			dc.setInit(u, stage);
			stage.setTitle("DashBoard");
			stage.setScene(new Scene(root, 990,490));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
