/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DAOImpl.MensagemDAOImpl;
import DAOImpl.SalaDAOImpl;
import DAOImpl.UsuarioDAOImpl;
import domain.Mensagem;
import domain.Sala;
import domain.Usuario;
import java.io.ObjectInputStream;
import java.net.Socket;
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
public class Adapter implements Runnable {

    private Socket p;

    public Adapter(Socket p) {
        this.p = p;
    }

    public void setP(Socket p) {
        this.p = p;
    }

    @Override
    public void run() {
        try {
            System.out.println("cliente: " + p.toString());
            ObjectInputStream entrada = new ObjectInputStream(p.getInputStream());
            int operacao = entrada.readInt(); //espera bloqueada.
            switch (operacao) {
                case 1: {//enviar msg
                    Mensagem msg = (Mensagem) entrada.readObject();
                    ManterMensagem manterMensagem = new ManterMensagemImpl(new MensagemDAOImpl());
                    Long id = manterMensagem.createMensagem(msg);
                    //notify() para atualizar lista de mensagens da sala
                break;
                }
                case 2: {//criar user
                    Usuario u = (Usuario) entrada.readObject();
                    ManterUsuario manterUsuario = new ManterUsuarioImpl(new UsuarioDAOImpl());
                    manterUsuario.criarUsuario(u);
                break;
                }
                case 3: {//sair sala
                    Usuario u = (Usuario) entrada.readObject();
                    ManterUsuario manterUsuario = new ManterUsuarioImpl(new UsuarioDAOImpl());
                    manterUsuario.alterarUsuario(u);
                    //notify() para atualizar lista de usuários da sala
                break;
                }
                case 4: {//criar sala
                    Sala s = (Sala) entrada.readObject();
                    ManterSala manterSala = new ManterSalaImpl(new SalaDAOImpl());
                    manterSala.createSala(s);
                    //notify() para atualizar lista de salas
                break;
                }
                case 5: {//entrar sala
                    Usuario u = (Usuario) entrada.readObject();
                    Sala s = (Sala) entrada.readObject();
                    ManterUsuario manterUsuario = new ManterUsuarioImpl(new UsuarioDAOImpl());
                    manterUsuario.alterarUsuario(u);
                    ManterSala manterSala = new ManterSalaImpl(new SalaDAOImpl());
                    //notify() para atualizar lista de usuários na sala
                break;
                }
            }
            p.close();
        } catch (Exception e) {
            System.out.println("O seguinte problema ocorreu:\n" + e.toString());
        }
    }

}
