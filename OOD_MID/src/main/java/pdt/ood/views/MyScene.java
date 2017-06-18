package pdt.ood.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pdt.ood.controllers.AdminController;
import pdt.ood.controllers.Controller;
import pdt.ood.controllers.InRoomController;
import pdt.ood.controllers.NormalController;
import pdt.ood.models.MYCONS;
import pdt.ood.models.Room;
import pdt.ood.models.User;

public class MyScene {
	private Stage stage;
	private User user;
	private Controller mctrl;
	private InRoomController ictrl;
	private FXMLLoader loader;
	private String src;
	
	public MyScene(User user, String src) {
		this.src = src;
		System.out.println(src);
		this.user = user;
		try {
			loader = new FXMLLoader(getClass().getResource(src));
			Parent root = (Parent) loader.load();
			mctrl = (src.equalsIgnoreCase(MYCONS.SCENE_ADMIN)) ? (AdminController) loader.getController() : (NormalController) loader.getController();
			stage = new Stage();
			mctrl.setInit(user, stage);
			stage.setTitle("DashBoard");
			stage.setScene(new Scene(root, 990,490));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMainUI() {
		try {
			loader = new FXMLLoader(getClass().getResource(src));
			Parent root = (Parent) loader.load();
			mctrl = (src.equalsIgnoreCase(MYCONS.SCENE_ADMIN)) ? (AdminController) loader.getController() : (NormalController) loader.getController();
			mctrl.setInit(user, stage);
			stage.setScene(new Scene(root, 1000, 500));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadInRoomUI(User u, Room room) {
		try {
			loader = new FXMLLoader(getClass().getResource(MYCONS.SCENE_INROOM));
			Parent root = (Parent) loader.load();
			ictrl = (InRoomController) loader.getController();
			ictrl.setInit(u, room);
			stage.setScene(new Scene(root, 1000, 500));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateMes(String mes) {
		ictrl.updateMes(mes);
	}
	
	public void updateUser(String notice) {
		ictrl.updateUser(notice);
	}

	public void setAChat(boolean b) {
		ictrl.setAChat(b);
	}
	
}
