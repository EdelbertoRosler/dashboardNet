package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DashController {

    @FXML
    private Button cancel;

    @FXML
    private Label currentBalance;

    @FXML
    private TextField fieldDescription;

    @FXML
    private TableView<?> movements;

    @FXML
    private Label expectedBalance;

    @FXML
    private RadioButton salary;

    @FXML
    private RadioButton expense;

    @FXML
    private ComboBox<?> category;

    @FXML
    private TextField fieldValue;

    @FXML
    private Button send;
    
    private Stage stage;

    @FXML
    void radioSalary(ActionEvent event) {

    }

    @FXML
    void radioExpense(ActionEvent event) {

    }

    @FXML
    void ff1313(ActionEvent event) {

    }

    @FXML
    void handleValue(ActionEvent event) {

    }

    @FXML
    void handleSend(ActionEvent event) {

    }

    @FXML
    void handleCancel(ActionEvent event) {

    }

    @FXML
    void handleDescription(ActionEvent event) {

    }

    @FXML
    void handleCategory(ActionEvent event) {

    }
    
    public void setStage(Stage stageDash) {
        this.stage = stageDash;
    }


}
