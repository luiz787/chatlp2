/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Cliente.Client;
import Cliente.Proxy;
import Cliente.ProxyImpl;
import DAO.MensagemDAO;
import DAO.SalaDAO;
import DAO.UsuarioDAO;
import DAOImpl.MensagemDAOImpl;
import DAOImpl.SalaDAOImpl;
import DAOImpl.UsuarioDAOImpl;
import domain.Mensagem;
import domain.Sala;
import domain.Usuario;
import exception.BusinessException;
import exception.PersistenceException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ManterMensagem;
import service.ManterSala;
import service.ManterUsuario;
import serviceimpl.ManterMensagemImpl;
import serviceimpl.ManterSalaImpl;
import serviceimpl.ManterUsuarioImpl;

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
    private TextArea textoEscrito;
    @FXML
    private TableView<Sala> tabSalas;
    @FXML
    private TableView<Usuario> tabUsuarios;
    @FXML
    private TableView<Mensagem> tabMensagem;
    @FXML
    private TableColumn<Mensagem, String> tabAutor;
    @FXML
    private TableColumn<Mensagem, String> tabConteudo;
    @FXML
    private TableColumn<Sala, String> listaSalas;
    @FXML
    private TableColumn<Usuario, String> listaUsuariosSala;
    @FXML
    private Button criarSala;
    @FXML
    private Button enviarMensagem;
    @FXML
    private Button sairSala;

    private LoginController infoLogin;// ATENÇÃO: PARA ACESSAR USUARIO LOGADO USAR infoLogin.usuario
    private Client run;
    private Proxy proxy = new ProxyImpl();
    private Sala sala;
    ManterUsuario manterUsuario = new ManterUsuarioImpl(new UsuarioDAOImpl());
    ManterSala manterSala = new ManterSalaImpl(new SalaDAOImpl());
    ManterMensagem manterMensagem = new ManterMensagemImpl(new MensagemDAOImpl());

    public void setRun(Client run) {
        this.run = run;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            atualizarSalas(FXCollections.observableArrayList(manterSala.getAll()));
        } catch (PersistenceException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void criarSala(ActionEvent event) throws PersistenceException, InterruptedException, BusinessException {
        Sala novaSala = new Sala();
        novaSala.setNome(procuraSala.getText());
        LoginController.usuario.setSala(novaSala);
        proxy.criarSala(novaSala);
        proxy.sairSala(LoginController.usuario);
        proxy.entrarSala(novaSala, LoginController.usuario);
        sala = novaSala;
        Thread.sleep(100);
        atualizarSalas(FXCollections.observableArrayList(manterSala.getAll()));
        atualizarUsuarios(FXCollections.observableArrayList(manterUsuario.getAllByRoom(sala)));
    }

    @FXML
    public void entrarSala(ActionEvent event) throws PersistenceException, BusinessException, InterruptedException {
        System.out.println("Entrar sala");
        SalaDAO salaDAO = new SalaDAOImpl();
        sala = salaDAO.getSalaByNome(procuraSala.getText());
        System.out.println(sala == null ? "sala n existe" : "existe");
        LoginController.usuario.setSala(sala);
        proxy.entrarSala(sala, LoginController.usuario);
        Thread.sleep(100);
        atualizarUsuarios(FXCollections.observableArrayList(manterUsuario.getAllByRoom(sala)));
    }

    @FXML
    public void enviarMensagem(ActionEvent event) throws PersistenceException, BusinessException, InterruptedException {
        System.out.println("Envia msg");
        String msg = textoEscrito.getText();
        Mensagem m = new Mensagem();
        m.setConteudo(msg);
        m.setSala(sala);
        m.setAutor(LoginController.usuario);
        proxy.enviarMensagem(m);
        Thread.sleep(100);
        atualizarMensagens(FXCollections.observableArrayList(manterMensagem.getAllBySala(sala)));
    }

    @FXML
    public void sairSala(ActionEvent event) {
        System.out.println("Sair da sala");
        LoginController.usuario.setSala(null);
        proxy.sairSala(LoginController.usuario);
        sala = null;
        atualizarMensagens(FXCollections.observableArrayList((Mensagem) null));
        atualizarUsuarios(FXCollections.observableArrayList((Usuario) null));
    }

    public void atualizarSalas(ObservableList<Sala> listSala) {
        listaSalas.setCellValueFactory(new PropertyValueFactory<Sala, String>("nome"));
        tabSalas.setItems(listSala);
    }

    public void atualizarUsuarios(ObservableList<Usuario> listUsuario) {
        listaUsuariosSala.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
        tabUsuarios.setItems(listUsuario);
    }

    public void atualizarMensagens(ObservableList<Mensagem> listMensagem) {
        tabAutor.setCellValueFactory(new PropertyValueFactory<Mensagem, String>("getAutor().getNome()")); // PROBLEMA AQUI
        tabConteudo.setCellValueFactory(new PropertyValueFactory<Mensagem, String>("conteudo"));
        tabMensagem.setItems(listMensagem);
    }
}
