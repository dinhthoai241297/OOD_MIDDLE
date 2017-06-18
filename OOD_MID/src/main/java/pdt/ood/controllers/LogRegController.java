package pdt.ood.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pdt.ood.models.AAdmin;
import pdt.ood.models.AStudent;
import pdt.ood.models.Creator;
import pdt.ood.models.MYCONS;
import pdt.ood.models.MyData;
import pdt.ood.models.User;

public class LogRegController implements Initializable {
	
	private static MyData data;
	
	@FXML
	private AnchorPane ap_main;
	@FXML
	private Label lb_app;
	@FXML
	private JFXPasswordField txt_Log_Password;
	@FXML
	private Label lb_pdt;
	@FXML
	private JFXTextField txt_Log_Username;
	@FXML
	private JFXButton btn_Log;
	@FXML
	private JFXButton btn_LoadLog;
	@FXML
	private Label lb_Mes_Log;
	@FXML
	private JFXTextField txt_Reg_Name;
	@FXML
	private JFXTextField txt_Reg_Username;
	@FXML
	private Rectangle sp_rec;
	@FXML
	private JFXPasswordField txt_Reg_Password;
	@FXML
	private JFXButton btn_Reg;
	@FXML
	private JFXButton btn_LoadReg;
	@FXML
	private Label lb_Mes_Reg;

	public void initialize(URL arg0, ResourceBundle arg1) {
		data = new MyData();
		System.out.println("xin chào");
		
		ParallelTransition pt = new ParallelTransition();
		FadeTransition fd = new FadeTransition(Duration.millis(2000), lb_app);
		fd.setDelay(Duration.millis(500));
		fd.setToValue(1.0f);
		fd.play();
		
		txt_Log_Password.setOnKeyReleased(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				login();
			}
		});
		
		txt_Reg_Password.setOnKeyReleased(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				register();
			}
		});

		TranslateTransition tl = new TranslateTransition(Duration.millis(700), lb_pdt);
		tl.setToX(275);
		tl.setToY(0);
		pt.setDelay(Duration.millis(700));
		pt.getChildren().addAll(fd, tl);
		pt.play();
	}

	public void loadUIReg() {
		System.err.println("Load UI register");
		lb_Mes_Reg.setText("");
		txt_Reg_Name.setText("");
		txt_Reg_Username.setText("");
		txt_Reg_Password.setText("");
		TranslateTransition trans = new TranslateTransition();
		TranslateTransition transRec = new TranslateTransition(Duration.millis(200), sp_rec);
		trans.setDuration(Duration.millis(200));
		trans.setNode(ap_main);
		trans.setToX(-350);
		trans.setToY(0);
		transRec.setToX(175);
		transRec.setToY(0);
		trans.play();
		transRec.play();
	}

	public void loadUILog() {
		System.out.println("Load UI login");
		lb_Mes_Log.setText("");
		txt_Log_Username.setText("");
		txt_Log_Password.setText("");
		TranslateTransition trans = new TranslateTransition();
		TranslateTransition transRec = new TranslateTransition(Duration.millis(200), sp_rec);
		trans.setDuration(Duration.millis(200));
		trans.setNode(ap_main);
		trans.setToX(0);
		trans.setToY(0);
		transRec.setToX(0);
		transRec.setToY(0);
		trans.play();
		transRec.play();
	}
	
	public void login() {
		lb_Mes_Log.setText("");
		String username = txt_Log_Username.getText();
		String password = txt_Log_Password.getText();
		User u = data.checkValid(username, password);
		if (u != null) {
			u.setState(MYCONS.STATE_OUTROOM);
			Creator.loadUI(u);
			txt_Log_Username.setText("");
			txt_Log_Password.setText("");
			System.out.println(username + " logged: just now!");
			return;
		}
		String mes = MYCONS.ERR_WRONG;
		lb_Mes_Log.setText(mes);
	}
	
	public void register() {
		lb_Mes_Reg.setText("");
		String name = txt_Reg_Name.getText();
		String username = txt_Reg_Username.getText();
		String password = txt_Reg_Password.getText();
		String mes = "";
		
		if (data.checkExited(username)) {
			mes = MYCONS.ERR_USER_EXISTED;
		} else if ("".equalsIgnoreCase(username) || "".equalsIgnoreCase(name) || "".equalsIgnoreCase(password)) {
			mes = MYCONS.ERR_NULL_FIELD;
		} else {
			User u = new User(name, username, password, new AStudent());
			data.addUser(u);
			mes = MYCONS.MES_CREATE_SUCCESS;
			txt_Reg_Name.clear();
			txt_Reg_Username.clear();
			txt_Reg_Password.clear();
			System.out.println("create User success");
		}
		lb_Mes_Reg.setText(mes);
	}
	
	public static MyData getData() {
		return data;
	}
}
