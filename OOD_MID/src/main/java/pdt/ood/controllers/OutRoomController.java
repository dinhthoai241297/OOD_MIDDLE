package pdt.ood.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pdt.ood.models.AAdmin;
import pdt.ood.models.ALecturer;
import pdt.ood.models.MYCONS;
import pdt.ood.models.MyData;
import pdt.ood.models.Room;
import pdt.ood.models.User;

public class OutRoomController implements Initializable {

	private User user;
	private MyData data;
	
	@FXML private TableView<Room> tv_room;
	@FXML private TableColumn<Room, String> tc_id_room;
	@FXML private TableColumn<Room, String> tc_name_room;
	@FXML private TableColumn<Room, String> tc_type_room;
	@FXML private TableColumn<Room, User> tc_owner_room;
	//

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tc_id_room.setCellValueFactory(new PropertyValueFactory<>("id"));
		tc_name_room.setCellValueFactory(new PropertyValueFactory<>("name"));
		tc_type_room.setCellValueFactory(new PropertyValueFactory<>("type"));
		tc_owner_room.setCellValueFactory(new PropertyValueFactory<>("creator"));
	}

	public void setInit(User m) {
		user = m;
		this.data = LogRegController.getData();
		loadRoom();
	}

	public void delRoom() {
		Room r = tv_room.getSelectionModel().getSelectedItem();
		data.getListRoom().remove(r);
		for (User u : r.getListUser()) {
			u.setState(MYCONS.STATE_OUTROOM);
			u.getUI().loadMainUI();
		}
		updateUIOutRoomAll();
	}

	public void joinRoom() {
		Room r = tv_room.getSelectionModel().getSelectedItem();
		if (user.joinRoom(r)) {
			user.setState(MYCONS.STATE_INROOM);
			user.getUI().loadInRoomUI(user, r);
			for (User u : r.getListUser()) {
				u.getUI().updateUser(this.user.getName() + MYCONS.JOIN_ROOM);
			}
		} else {
			// không có quyền vào phòng
		}
	}

	private void updateUIOutRoomAll() {
		for (User m : data.getListUser()) {
			if (m.getState() == MYCONS.STATE_OUTROOM) {
			}
		}
	} // cập nhật giao diện ngoài phòng khi có thay đổi
	
	public void loadRoom() {
		ObservableList<Room> list = FXCollections.observableArrayList(data.getListRoom());
		tv_room.setItems(list);
		System.out.println("Load list room finished");
	}
}
