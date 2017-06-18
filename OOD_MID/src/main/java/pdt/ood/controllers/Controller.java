package pdt.ood.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pdt.ood.models.Creator;
import pdt.ood.models.MYCONS;
import pdt.ood.models.MyData;
import pdt.ood.models.Room;
import pdt.ood.models.User;

public class Controller implements Initializable {
	protected Stage stage;
	protected User u;
	protected MyData data;
	private OutRoomController octrl;
	
	@FXML
	protected JFXButton btn_logout;
	@FXML
	private JFXButton btn_add_room;
	@FXML
	private JFXButton btn_join;
	@FXML
	private JFXButton btn_del_room;
	@FXML
	private Label lb_mes_room;
	@FXML
	private AnchorPane ac_add_room;
	@FXML
	private JFXTextField txt_addr_name;
	@FXML
	private JFXPasswordField txt_addr_password;
	@FXML
	protected JFXComboBox<String> cb_room;
	@FXML
	private AnchorPane ac_tv_room;
	@FXML
	private JFXComboBox<Room> filter_room;
	@FXML
	private JFXTextField search_room;
	@FXML
	protected AnchorPane ac_room;
	@FXML
	protected AnchorPane ac_main;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public void setInit(User u, Stage stage) {
		this.stage = stage;
		this.u = u;
		btn_logout.setText(u.getName());
		loadListRoom();
	}
	

	public void showAddRoom() {
		TranslateTransition tl = new TranslateTransition(Duration.millis(500), ac_add_room);
		tl.setToX(0);
		tl.play();
	}

	public void closeAddRoom() {
		lb_mes_room.setText("");
		TranslateTransition tl = new TranslateTransition(Duration.millis(500), ac_add_room);
		tl.setToX(275);
		tl.play();
	}
	
	public void addRoom() {
		String name = txt_addr_name.getText();
		String password = txt_addr_password.getText();
		String type = cb_room.getSelectionModel().getSelectedItem();
		Room r = Creator.createRoom(u, name, password, type);
		String mes = "";
		if (r != null) {
			data.addRoom(r);
			mes = MYCONS.CREATEROOM_SUCCESS;
		} else {
			mes = MYCONS.CREATEROOM_FAIL;
		}
		lb_mes_room.setText(mes);
	}

	public void loadListRoom() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(MYCONS.SCENE_OUTROOM));
			ac_tv_room.getChildren().addAll((AnchorPane) loader.load());
			octrl = (OutRoomController) loader.getController();
			octrl.setInit(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void joinRoom() {
		octrl.joinRoom();
	}
	
	public void logout() {
		u.setState(MYCONS.STATE_LOGOUT);
		stage.hide();
	}
}
