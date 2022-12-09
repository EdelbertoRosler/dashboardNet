/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Dashboard.class.getResource("../view/Principal.fxml"));
        BorderPane rootLayout = (BorderPane) loader.load();
        
        Scene scene = new Scene(rootLayout);
        
        stage.setTitle("Exemplo 10/11");
        stage.setScene(scene);
        stage.show();
    }
    
}
