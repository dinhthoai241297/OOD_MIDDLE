package pdt.ood.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import pdt.ood.models.MYCONS;
import pdt.ood.models.Room;
import pdt.ood.models.User;

public class InRoomController implements Initializable{
	
	private User user;
	private Room room;
	private int countMes;
	
	@FXML
	private JFXButton btn_send;
	@FXML
	private JFXButton btn_kick;
	@FXML
	private JFXButton btn_out;
	@FXML
	private Label lb_room;
	@FXML
	private JFXButton btn_dis_chat;
	@FXML
	private GridPane gp_notice;
	@FXML
	private ListView<User> lv_user;
	@FXML
	private ScrollPane sc_notice;
	@FXML
	private ScrollPane sc_mes;
	@FXML
	private TextField tf_mes;
	@FXML
	private ScrollPane sc_user;
	@FXML
	private JFXButton btn_en_chat;
	@FXML
	private Label lb_owner;
	@FXML
	private GridPane gp_mes;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tf_mes.setOnKeyReleased(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				send();
			}
		});
	}
	
	public void setInit(User user, Room room) {
		this.user = user;
		this.room = room;
		loadUI();
	}
	
	public void loadUI() {
		lb_room.setText("Tên phòng: " + room.getName());
		lb_owner.setText("Chủ phòng: " + room.getCreator().getName());
		loadListUser();
		
		if (!room.checkWrite(user)) {
			tf_mes.setDisable(true);
			btn_send.setDisable(true);
		}

		if (!room.checkFun(user)) {
			btn_dis_chat.setDisable(true);
			btn_en_chat.setDisable(true);
			btn_kick.setDisable(true);
		}
	}

	public void send() {
		updateMesAll(room, user.getName() + ": " + tf_mes.getText());
		tf_mes.setText("");
	}

	private void updateMesAll(Room room, String mes) {
		for (User u : room.getListUser()) {
			u.getUI().updateMes(mes);
		}
	}
	
	public void updateMes(String mes) {
		Label lb = new Label(mes);
		lb.setWrapText(true);
		gp_mes.addRow(countMes++, lb);
		sc_mes.vvalueProperty().bind(gp_mes.heightProperty());
	}

	private void updateUserAll(String notice, Room r) {
		for (User u : room.getListUser()) {
			u.getUI().updateUser(notice);
		}
	}

	public void leaveRoom() {
		user.leaveRoom(room);
		user.setState(MYCONS.STATE_OUTROOM);
		user.getUI().loadMainUI();
		updateUserAll(user.getName() + MYCONS.LEAVE_ROOM, room);
	}

	public void disChat() {
		User u = lv_user.getSelectionModel().getSelectedItem();
		if (u != null) {
			u.getUI().setAChat(true);
		}
	}

	public void enChat() {
		User u = lv_user.getSelectionModel().getSelectedItem();
		if (u != null) {
			u.getUI().setAChat(false);
		}
	}

	public void kick() {
		User bekick = lv_user.getSelectionModel().getSelectedItem();
		boolean check = user.kick(bekick, room);
		if (check) {
			bekick.getUI().loadMainUI();
			updateUserAll(bekick.getName() + MYCONS.LEAVE_ROOM, room);
		} else {
			// kick user fail
		}
	}
	
	public void setAChat(boolean ac) {
		btn_send.setDisable(ac);
		tf_mes.setDisable(ac);
	}

	public void updateUser(String notice) {
		Label lb = new Label(notice);
		lb.setWrapText(true);
		gp_notice.addRow(countMes++, lb);
		sc_notice.vvalueProperty().bind(gp_notice.heightProperty());
		loadListUser();
	}
	
	private void loadListUser() {
		lv_user.getItems().clear();
		ObservableList<User> list = FXCollections.observableArrayList(room.getListUser());
		lv_user.getItems().addAll(list);
	}
}
