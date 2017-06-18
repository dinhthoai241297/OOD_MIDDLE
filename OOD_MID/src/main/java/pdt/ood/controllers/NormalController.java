package pdt.ood.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import pdt.ood.models.MYCONS;

public class NormalController extends Controller {
	
	@FXML
	private AnchorPane ac_profile;
	@FXML
	private JFXTextField tf_name;
	@FXML
	private JFXTextField tf_phone;
	@FXML
	private JFXTextField tf_email;
	@FXML
	private JFXPasswordField pf_pw_old;
	@FXML
	private JFXPasswordField pf_pw_new;
	@FXML
	private JFXPasswordField pf_pw_new_conf;
	@FXML
	private JFXButton btn_update_pw;
	@FXML
	private JFXButton btn_update_profile;
	@FXML
	private JFXButton btn_profile;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		data = LogRegController.getData();
		
		ObservableList<String> list = FXCollections.observableArrayList(MYCONS.TYPE_CHATROOM, MYCONS.TYPE_CLASSROOM,
				MYCONS.TYPE_BOARDROOM);
		cb_room.setValue(MYCONS.TYPE_CHATROOM);
		cb_room.setItems(list);
	}
	
	public void loadProfile() {
		resetACP(1);
	}
	
	public void loadRoom() {
		loadListRoom();
		resetACP(0);
	}
	
	private void resetACP(int x) {
		TranslateTransition ttR = new TranslateTransition(Duration.millis(300), ac_room);
		TranslateTransition ttP = new TranslateTransition(Duration.millis(300), ac_profile);
		ttR.setToX(0);
		ttP.setToX(0);
		ParallelTransition pt = new ParallelTransition();
		TranslateTransition tMain = new TranslateTransition(Duration.millis(300), ac_main);
		TranslateTransition tmp = new TranslateTransition(Duration.millis(300));
		if (x == 0) {
			tmp.setNode(ac_room);
			tmp.setToX(70);
			tMain.setToY(0);
		} else if (x == 1) {
			tmp.setNode(ac_profile);
			tmp.setToX(70);
			tMain.setToY(-500);
		}
		pt.getChildren().addAll(ttR, ttP, tMain, tmp);
		pt.play();
	}
	
	public void updatePW() {
		
	}
	
	public void updateProfile() {
		
	}
}
