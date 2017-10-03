/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import controller.ChatController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Luiz
 */
public class Client extends Application {
    private Stage primaryStage; 
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UAI CHAT");
        showChat();
    }
    
    public void showChat(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("../view/chatView.fxml"));
            AnchorPane Login = (AnchorPane) loader.load();
            ChatController controller = loader.getController();
            controller.setRun(this);
        } catch (IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
