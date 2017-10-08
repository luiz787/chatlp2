/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Cliente.Client;
import Cliente.Proxy;
import Cliente.ProxyImpl;
import DAO.UsuarioDAO;
import DAOImpl.UsuarioDAOImpl;
import domain.Usuario;
import exception.BusinessException;
import exception.PersistenceException;
import java.io.IOException;
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
import javafx.scene.layout.Pane;
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
    private Button botaoEntrar;
    private Client run;
    public static Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
            Proxy proxy = new ProxyImpl();
            String nomeUsuario = nom_user.getText();
            this.usuario = new Usuario();
            usuario.setNome(nomeUsuario);
            if (nomeUsuario.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Falha ao logar");
                alert.setHeaderText("erro");
                alert.setContentText("O usuário não pode ser vazio");
                alert.showAndWait();
            } else {
                proxy.criarUsuario(usuario);
                showChat();
            }
           /* ManterUsuario manterusuario = new ManterUsuarioImpl(new UsuarioDAOImpl());
            String nomeUsuario = nom_user.getText();
            this.usuario = new Usuario();
            usuario.setNome(nomeUsuario);
            if (nomeUsuario.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Falha ao logar");
                alert.setHeaderText("erro");
                alert.setContentText("O usuário não pode ser vazio");
                alert.showAndWait();
            } else {
                manterusuario.criarUsuario(usuario);
                showChat();
                //passar usuario atual para prox tela

            }*/
           
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showChat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("../View/chatView.fxml"));
            AnchorPane telaChat = (AnchorPane) loader.load();
            run.getRootLayout().setCenter(telaChat);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
