package pdt.ood.models;

public class MYCONS {
	
	public final static int STATE_INROOM = 1;
	public final static int STATE_OUTROOM = 0;
	public final static int STATE_LOGOUT = -1;
	
	
	public final static String ERR_WRONG = "sai tên đăng nhập hoặc mật khẩu, vui lòng kiểm tra lại!";
	public final static String ERR_NULL_FIELD = "Không được bỏ trống trường nào!";
	public final static String ERR_USER_EXISTED = "tài khoản này đã tồn tại!";
	public final static String MES_CREATE_SUCCESS = "tạo tài khoản thành công.";
	
	public final static String JOIN_ROOM = " vừa vào phòng";
	public final static String LEAVE_ROOM = " vừa rời phòng";
	
	public final static String TYPE_CLASSROOM = "classRoom";
	public final static String TYPE_CHATROOM = "chatRoom";
	public final static String TYPE_BOARDROOM = "boardRoom";
	public final static String CREATEROOM_SUCCESS = "Tạo phòng hoàn tất";
	public final static String CREATEROOM_FAIL = "Bạn không có quyền tạo loại phòng này";
	
	public final static String SCENE_ADMIN = "/pdt/ood/views/MainAdmin.fxml";
	public final static String SCENE_NORMAL = "/pdt/ood/views/MainNormal.fxml";
	public final static String SCENE_INROOM = "/pdt/ood/views/InRoomScene.fxml";
	public final static String SCENE_OUTROOM = "/pdt/ood/views/OutRoomScene.fxml";
	public final static String SCENE_LOGREG = "/pdt/ood/views/LogRegScene.fxml";
	
	public final static String IMG_LOGREG = "/pdt/ood/img/img-login.png";
	
}
