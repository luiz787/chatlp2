/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Cliente.Client;
import DAO.UsuarioDAO;
import DAOImpl.UsuarioDAOImpl;
import domain.Usuario;
import exception.BusinessException;
import exception.PersistenceException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import service.ManterUsuario;
import serviceimpl.ManterUsuarioImpl;

/**
 * FXML Controller class
 *
 * @author Pós Graduação
 */
public class LoginController implements Initializable {

    @FXML
    private TextField nom_user;
    @FXML
    private Button butao_esntrar;
    
    private Client run;
    
    private BorderPane rootLayout;
    
    private Stage primaryStage;
    
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    /**
     * Initializes the controller class.
     */
    public void setRun(Client run) {
        this.run = run;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logar(ActionEvent event) {
        try {
        UsuarioDAO dao = new UsuarioDAOImpl();
        ManterUsuario manterusuario = new ManterUsuarioImpl(dao);
        String nome = nom_user.getText();
        usuario.setNome(nome);
        
        if (nome.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Falha ao logar");
            alert.setHeaderText("erro");
            alert.setContentText("O usuário não pode ser vazio");
            alert.showAndWait(); 
        }
        else{
                manterusuario.criarUsuario(usuario);
                //CHAMAR OUTRA TELA
            
            }
        }    catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    

