/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Cliente.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Luiz
 */
public class ChatController implements Initializable {
    @FXML
    private Button botaoEntrar;
    @FXML
    private TextField procuraSala;
    @FXML
    private TextArea conteudoSala;
    @FXML
    private TextArea textoEscrito;
    @FXML
    private TableColumn listaSalas;
    @FXML
    private TableColumn listaUsuariosSala;
    @FXML
    private Button criarSala;
    @FXML
    private Button enviarMensagem;
    @FXML
    private Button sairSala;
    
    private Client run;

    public void setRun(Client run) {
        this.run = run;
    }
    
    @FXML
    public void testeClick(ActionEvent event){
        System.out.println("Teste com sucesso");
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
    
}
