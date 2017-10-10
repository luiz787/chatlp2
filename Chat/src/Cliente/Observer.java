/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import controller.ChatController;
import domain.Mensagem;
import domain.Sala;
import domain.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;

/**
 *
 * @author aluno
 */
public class Observer implements Runnable  {

    private Socket socket;
    private ChatController chat;

    
    public Observer(Socket socket, ChatController chat) {
        this.socket = socket;
        this.chat = chat;
    }
    
    
    
    
    @Override
    public void run() {
       while(true){
           ObjectInputStream entrada;
           try {
               entrada = new ObjectInputStream (socket.getInputStream());
               int aux = entrada.readInt(); //aux define qual objeto vaiser atualizado
               Platform.runLater(()-> {
               switch (aux) {
                       case 1:
                        try {   
                            ArrayList<Mensagem>  mensagens = (ArrayList<Mensagem>) entrada.readObject();
                            chat.atualizarMensagens(FXCollections.observableArrayList(mensagens));
                            break;
                        } catch (IOException |ClassNotFoundException ex) {
                            Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                            
                        case 2:
                         try {    
                            ArrayList<Sala>  salas = (ArrayList<Sala>) entrada.readObject();
                            chat.atualizarSalas(FXCollections.observableArrayList(salas));
                            break;
                        } catch (IOException |ClassNotFoundException ex) {
                            Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                        case 3:
                        try {
                            ArrayList<Usuario>  usuarios = (ArrayList<Usuario>) entrada.readObject();
                            chat.atualizarUsuarios(FXCollections.observableArrayList(usuarios));
                            break;
                        } catch (IOException |ClassNotFoundException ex) {
                            Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                        } 
               }
               });  
           } catch (IOException ex) {
               Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
           } 
           
       }
    }
    
}
