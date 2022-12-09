/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Guilherme
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Todo;

public class PrincipalController {

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnNovo;

    @FXML
    private TableColumn<Todo, String> colDesc;

    @FXML
    private TableColumn<Todo, String> colSum;
    //<Classe, Tipo do Atributo>

    @FXML
    private TableView<Todo> tabTodos;

    @FXML
    void handleBtnExcluir(ActionEvent event) {

    }

    @FXML
    void handleBtnNovo(ActionEvent event) {

    }

    @FXML
    void handleEditCell(ActionEvent event) {

    }

    @FXML
    void initialize() {
        

    }

}
