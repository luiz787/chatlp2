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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
public class ChatController implements Initializable   {

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
    private TableColumn<Sala, String> listaSalas;
    @FXML
    private TableColumn<Usuario, String> listaUsuariosSala;
    @FXML
    private Button criarSala;
    @FXML
    private Button enviarMensagem;
    @FXML
    private Button sairSala;
    private LoginController infoLogin;
    // ATENÇÃO: PARA ACESSAR USUARIO LOGADO USAR infoLogin.usuario
    private Client run;
    private Proxy proxy = new ProxyImpl(this);
    private Sala sala;

    ManterUsuario manterusuario = new ManterUsuarioImpl(new UsuarioDAOImpl());
    ManterSala mantersala = new ManterSalaImpl(new SalaDAOImpl());
    ManterMensagem mantermensagem = new ManterMensagemImpl(new MensagemDAOImpl());
    @FXML
    private ListView<String> listaMensagens;
    
    public void setRun(Client run) {
        this.run = run;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            atualizarSalas(FXCollections.observableArrayList(mantersala.getAll()));
            
        } catch (PersistenceException ex ) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    public void criarSala(ActionEvent event) throws PersistenceException {
        Sala novaSala = new Sala();
        novaSala.setNome(procuraSala.getText());
        LoginController.usuario.setSala(novaSala);
        novaSala.addUsuario(LoginController.usuario);
        proxy.criarSala(novaSala);
        proxy.sairSala(LoginController.usuario);
        proxy.entrarSala(novaSala, LoginController.usuario);
        sala = novaSala;
        
    }

    @FXML
    public void entrarSala(ActionEvent event) throws PersistenceException {
        System.out.println("Entrar sala");
        SalaDAO salaDAO = new SalaDAOImpl();
        sala = salaDAO.getSalaByNome(procuraSala.getText());
        System.out.println(sala == null ? "sala n existe" : "existe");
        LoginController.usuario.setSala(sala);
        proxy.entrarSala(sala, LoginController.usuario);
    }

    @FXML
    public void enviarMensagem(ActionEvent event) throws PersistenceException {
        System.out.println("Envia msg");
        String msg = textoEscrito.getText();
        Mensagem m = new Mensagem();
        m.setConteudo(msg);
        m.setSala(sala);
        m.setAutor(LoginController.usuario);
        proxy.enviarMensagem(m);
       
    }

    @FXML
    public void sairSala(ActionEvent event) {
        System.out.println("Sair da sala");
        LoginController.usuario.setSala(null);
        proxy.sairSala(LoginController.usuario);
        sala = null;
      
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
           
        ObservableList<String> lista = FXCollections.observableArrayList();
        for (int i=0; i<listMensagem.size(); i++){
            lista.add(listMensagem.get(i).getAutor().getNome()+" disse: " + listMensagem.get(i).getConteudo());
        }
        
        listaMensagens.setItems(lista);
    }
    
    
        
        
    
    
}
