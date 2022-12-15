package Controller;

import service.Usuarioservice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField password;

    @FXML
    private Button sendLogin;

    @FXML
    private Button cancelLogin;

    @FXML
    private TextField userId;

    private Usuarioservice usuarioservice;

    public LoginController() {
        this.usuarioservice = new Usuarioservice();
    }

    @FXML
    void handleSendLogin(ActionEvent event) {
        System.out.println(userId.getText() +"  "+ password.getText());
        usuarioservice.getUser(userId.getText(), password.getText());
    }

    @FXML
    void e8e8e8(ActionEvent event) {

    }

    @FXML
    void handleCancelLogin(ActionEvent event) {

    }

}
