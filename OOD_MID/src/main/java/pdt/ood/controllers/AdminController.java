package pdt.ood.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pdt.ood.models.AAdmin;
import pdt.ood.models.ALecturer;
import pdt.ood.models.AStudent;
import pdt.ood.models.Authority;
import pdt.ood.models.Creator;
import pdt.ood.models.MYCONS;
import pdt.ood.models.User;

public class AdminController extends Controller {

	@FXML
	private JFXButton btn_show_addU;
	@FXML
	private AnchorPane ac_sta;
	@FXML
	private JFXButton btn_search_user;
	@FXML
	private JFXButton btn_refresh_r;
	@FXML
	private JFXButton btn_sta;
	@FXML
	private JFXButton btn_search_r;
	@FXML
	private JFXButton btn_room;
	@FXML
	private JFXButton btn_add_r;
	@FXML
	private JFXButton btn_refresh_user;
	@FXML
	private JFXButton btn_user;
	@FXML
	private AnchorPane ac_user;
	@FXML
	private TableView<User> tv_user;
	@FXML
	private TableColumn<User, String> tc_id_user;
	@FXML
	private TableColumn<User, FlowPane> tc_function_user;
	@FXML
	private TableColumn<User, String> tc_username_user;
	@FXML
	private TableColumn<User, String> tc_name_user;
	@FXML
	private TableColumn<User, String> tc_password_user;
	@FXML
	private TableColumn<User, String> tc_authority_user;
	@FXML
	private JFXButton btn_close_addU;
	@FXML
	private Label lb_mes_user;
	@FXML
	private JFXTextField txt_reg_name;
	@FXML
	private JFXTextField txt_reg_username;
	@FXML
	private JFXPasswordField txt_reg_password;
	@FXML
	private JFXButton btn_add_user;
	@FXML
	private AnchorPane ac_add_user;
	@FXML
	private JFXButton btn_close_addr;
	@FXML
	private JFXComboBox<Authority> filter_user;
	@FXML
	private JFXTextField search_user;
	@FXML
	private JFXButton btn_del_user;
	@FXML
	private JFXComboBox<String> cb_authority;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		data = LogRegController.getData();
		tv_user.setEditable(true);

		ObservableList<String> list = FXCollections.observableArrayList(MYCONS.TYPE_CHATROOM, MYCONS.TYPE_CLASSROOM,
				MYCONS.TYPE_BOARDROOM);
		cb_room.setValue(MYCONS.TYPE_CHATROOM);
		cb_room.setItems(list);
		
		ObservableList<String> list1 = FXCollections.observableArrayList("Admin", "Lecturer", "Student");
		cb_authority.setValue("Student");
		cb_authority.setItems(list1);

		tc_id_user.setCellValueFactory(new PropertyValueFactory<>("id"));
		tc_name_user.setCellValueFactory(new PropertyValueFactory<>("name"));
		tc_username_user.setCellValueFactory(new PropertyValueFactory<>("username"));
		tc_password_user.setCellValueFactory(new PropertyValueFactory<>("password"));
		tc_authority_user.setCellValueFactory(new PropertyValueFactory<>("authority"));
		tc_name_user.setCellFactory(TextFieldTableCell.<User>forTableColumn());
		tc_name_user.setOnEditCommit((CellEditEvent<User, String> e) -> {
			TablePosition<User, String> pos = e.getTablePosition();
			String newName = e.getNewValue();
			System.out.println("New name: " + newName);
			int row = pos.getRow();
			User u = e.getTableView().getItems().get(row);
			u.setName(newName);
		});
		loadListUser();
	}

	public void setInit(User u, Stage stage) {
		this.u = u;
		this.stage = stage;
		btn_logout.setText(u.getName());
	}

	public void loadSta() {
		resetACP(0);
	}

	public void loadMem() {
		resetACP(1);
	}

	public void loadRoom() {
		loadListRoom();
		resetACP(2);
	}

	public void resetACP(int x) {
		TranslateTransition ttS = new TranslateTransition(Duration.millis(300), ac_sta);
		TranslateTransition ttR = new TranslateTransition(Duration.millis(300), ac_room);
		TranslateTransition ttM = new TranslateTransition(Duration.millis(300), ac_user);
		ttS.setToX(0);
		ttR.setToX(0);
		ttM.setToX(0);
		ParallelTransition pt = new ParallelTransition();
		TranslateTransition tMain = new TranslateTransition(Duration.millis(300), ac_main);
		TranslateTransition tmp = new TranslateTransition(Duration.millis(300));
		if (x == 0) {
			tmp.setNode(ac_sta);
			tmp.setToX(70);
			tMain.setToY(0);
		} else if (x == 1) {
			tmp.setNode(ac_user);
			tmp.setToX(70);
			tMain.setToY(-500);
		} else {
			tmp.setNode(ac_room);
			tmp.setToX(70);
			tMain.setToY(-1000);
		}
		pt.getChildren().addAll(ttS, ttR, ttM, tMain, tmp);
		pt.play();
	}

	public void loadListUser() {
		ObservableList<User> list = FXCollections.observableArrayList(data.getListUser());
		tv_user.setItems(list);
		System.out.println("Load list user finished");
	}

	public void closeAddUser() {
		lb_mes_user.setText("");
		TranslateTransition tl = new TranslateTransition(Duration.millis(500), ac_add_user);
		tl.setToX(275);
		tl.play();
	}

	public void showAddUser() {
		TranslateTransition tl = new TranslateTransition(Duration.millis(500), ac_add_user);
		tl.setToX(0);
		tl.play();
	}

	public void register() {
		lb_mes_user.setText("");
		String name = txt_reg_name.getText();
		String username = txt_reg_username.getText();
		String password = txt_reg_password.getText();
		String au = cb_authority.getValue();
		String mes = "";

		if (data.checkExited(username)) {
			mes = MYCONS.ERR_USER_EXISTED;
		} else if ("".equalsIgnoreCase(username) || "".equalsIgnoreCase(name) || "".equalsIgnoreCase(password)) {
			mes = MYCONS.ERR_NULL_FIELD;
		} else {
			User u = Creator.createUser(name, username, password, au);
			data.addUser(u);
			mes = MYCONS.MES_CREATE_SUCCESS;
			txt_reg_name.clear();
			txt_reg_username.clear();
			txt_reg_password.clear();
			System.out.println("create User success");
		}
		lb_mes_user.setText(mes);
	}

}
