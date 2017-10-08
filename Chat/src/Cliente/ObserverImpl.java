/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import DAOImpl.MensagemDAOImpl;
import DAOImpl.SalaDAOImpl;
import DAOImpl.UsuarioDAOImpl;
import controller.ChatController;
import controller.LoginController;
import domain.Sala;
import exception.BusinessException;
import exception.PersistenceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
public class ObserverImpl extends Observer {
    ManterSala manterSala;
    ManterMensagem manterMensagem;
    ManterUsuario manterUsuario;
    Sala s;

    public ObserverImpl(ChatController subject) {
        this.subject = subject;
        s = LoginController.usuario.getSala();
        this.subject.attach(this);
        manterSala = new ManterSalaImpl(new SalaDAOImpl());
        manterMensagem = new ManterMensagemImpl(new MensagemDAOImpl());
        manterUsuario = new ManterUsuarioImpl(new UsuarioDAOImpl());
    }

    @Override
    public void update() {
        try {
            subject.atualizarMensagens(FXCollections.observableArrayList(manterMensagem.getAllBySala(s)));
            subject.atualizarSalas(FXCollections.observableArrayList(manterSala.getAll()));
            subject.atualizarUsuarios(FXCollections.observableArrayList(manterUsuario.getAllByRoom(s)));
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(ObserverImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
