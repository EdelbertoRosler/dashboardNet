/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Controller.DashController;
import Controller.LoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author 05220260
 */
public class Dashboard extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Dashboard.class.getResource("../view/login-view.fxml"));
        BorderPane rootLayout = (BorderPane) loader.load();
        
        LoginController controller = loader.getController();
        controller.setMain(this);
        
        Scene scene = new Scene(rootLayout);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }
    
    public void mostraTelaFinancas() throws IOException{
        // Load root layout from fxml file.
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Dashboard.class.getResource("../view/dash-view.fxml"));
        BorderPane rootLayout = (BorderPane) loader.load();
        
        Stage stageDash = new Stage();
        
        Scene scene = new Scene(rootLayout);
        
        DashController dashboardController = loader.getController();
        dashboardController.setStage(stageDash);
        
        stageDash.setScene(scene);
        
        stageDash.showAndWait();        
        
    }
    
}
