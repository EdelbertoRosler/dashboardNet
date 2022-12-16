package Controller;

import app.Dashboard;
import java.io.IOException;
import service.Usuarioservice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private Button sendLogin;

    @FXML
    private Button cancelLogin;

    @FXML
    private TextField userId;
    
    private Dashboard main;

    private Usuarioservice usuarioservice;

    public LoginController() {
        this.usuarioservice = new Usuarioservice();
    }

    @FXML
    void handleSendLogin(ActionEvent event) throws IOException {
        System.out.println(userId.getText() +"  "+ password.getText());
        boolean logado = usuarioservice.getUser(userId.getText(), password.getText());
        if(logado){
            Stage stage = (Stage) sendLogin.getScene().getWindow();
            stage.close();
            main.mostraTelaFinancas();
            
        } else{
            password.setStyle("-fx-text-box-border: #ff0000; -fx-focus-color: #ff0000;");
            userId.setStyle("-fx-text-box-border: #ff0000; -fx-focus-color: #ff0000;");
            userId.setText("Usuário ou senha incorretos");
        }
    }

    @FXML
    void e8e8e8(ActionEvent event) {

    }

    @FXML
    void handleCancelLogin(ActionEvent event) {
        Stage stage = (Stage) cancelLogin.getScene().getWindow();
        stage.close();
    }
    
    public void setMain(Dashboard mainApp){
        this.main = mainApp;
    }

}
