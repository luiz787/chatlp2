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
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Luiz
 */
public class Client extends Application {
    private Stage primaryStage; 
    
    private BorderPane rootLayout;
    
    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UAI CHAT");
        initRootLayout();
        showChat();
    }
    
    public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("../View/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /* public void showChat(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("../view/chatView.fxml"));
            AnchorPane Login = (AnchorPane) loader.load();
            ChatController controller = loader.getController();
            controller.setRun(this);
        } catch (IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    private void showChat() {
         try{
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(Client.class.getResource("../View/chatView.fxml"));
            AnchorPane chatView = (AnchorPane) loader.load();
            
            rootLayout.setCenter(chatView);
            
            ChatController controller = loader.getController();
            controller.setRun(this);
        
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
