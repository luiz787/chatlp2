/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Cliente.Client;
import DAO.MensagemDAO;
import DAO.SalaDAO;
import DAO.UsuarioDAO;
import DAOImpl.MensagemDAOImpl;
import DAOImpl.SalaDAOImpl;
import DAOImpl.UsuarioDAOImpl;
import domain.Mensagem;
import domain.Sala;
import domain.Usuario;
import exception.PersistenceException;
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
    private LoginController infoLogin;
    // ATENÇÃO: PARA ACESSAR USUARIO LOGADO USAR infoLogin.usuario
    private Client run;

    public void setRun(Client run) {
        this.run = run;
    }

    @FXML
    public void criarSala(ActionEvent event) throws PersistenceException {
        System.out.println("Teste com sucesso");
        Sala novaSala = new Sala();
        novaSala.setNome(procuraSala.getText());
        //currentUser.setSala(novaSala);
        //novaSala.addUsuario(currentUser);
        Usuario atu = new Usuario();
        atu.setNome("Luiz");
        atu.setSala(novaSala);
        novaSala.addUsuario(atu);
        SalaDAO salaDAO = new SalaDAOImpl();
        salaDAO.createSala(novaSala);
        System.out.println("Nome da sala: " + salaDAO.getSalaByNome(procuraSala.getText()).getNome());
    }

    @FXML
    public void entrarSala(ActionEvent event) throws PersistenceException {
        System.out.println("Entrar sala");
        SalaDAO salaDAO = new SalaDAOImpl();
        Sala s = salaDAO.getSalaByNome(procuraSala.getText());
        System.out.println(s == null ? "existe" : "nao existe");
        //currentUser.setSala(s);
    }

    @FXML
    public void enviarMensagem(ActionEvent event) throws PersistenceException {
        System.out.println("Envia msg");
        String msg = textoEscrito.getText();
        Mensagem m = new Mensagem();
        //m.setAutor(currentUser);
        //m.setSala(currentUser.getSala());
        m.setConteudo(msg);
        MensagemDAO mensagemDAO = new MensagemDAOImpl();
        Long newId = mensagemDAO.createMensagem(m);
        m.setId(newId);
        //observer olha esse método para atualizar lista de mensagens POR SALA
    }

    @FXML
    public void sairSala(ActionEvent event) {
        System.out.println("Sair da sala");
        System.out.println("usuario  "+ infoLogin.usuario.getNome());
        SalaDAO salaDAO = new SalaDAOImpl();
        //salaDAO vai ter que ter metodos q atualiza os usuários da sala
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        //currentUser.setSala(null); // sala pode ser NULL? se não, o botao sair não existe.
        //usuarioDAO.alterarUsuario(currentUser);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
