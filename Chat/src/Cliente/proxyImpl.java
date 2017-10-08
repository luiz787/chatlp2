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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author F43L
 */
public class ProxyImpl implements Proxy {

    String IPServidor = "localhost";
    int PortaServidor = 2230;
    int metodo;
    ChatController chatController;
    
    public ProxyImpl(){}
    
    public ProxyImpl(ChatController c){
        this.chatController = c;
    }

    @Override
    public void enviarMensagem(Mensagem m) {
        try {
            metodo = 1;
            Socket c = new Socket(IPServidor, PortaServidor);
            System.out.println("estabelece conexao com servidor");
            ObjectOutputStream saida = new ObjectOutputStream(c.getOutputStream());
            saida.writeInt(metodo);
            saida.flush();
            saida.writeObject(m); //envia mensagem
            saida.flush();
            c.close();
        } catch (Exception e) {
            System.out.println("O seguinte problema ocorreu : \n" + e.toString());
        }
    }

    @Override
    public void criarUsuario(Usuario u) {
        try {
            metodo = 2;
            Socket c = new Socket(IPServidor, PortaServidor);
            System.out.println("estabelece conexao com servidor");
            ObjectOutputStream saida = new ObjectOutputStream(c.getOutputStream());
            saida.writeInt(metodo);
            saida.flush();
            saida.writeObject(u); //envia usuario
            saida.flush();
            c.close();
        } catch (Exception e) {
            System.out.println("O seguinte problema ocorreu : \n" + e.toString());
        }
    }

    @Override
    public void sairSala(Usuario u) {
        try {
            metodo = 3;
            Socket c = new Socket(IPServidor, PortaServidor);
            System.out.println("estabelece conexao com servidor");
            ObjectOutputStream saida = new ObjectOutputStream(c.getOutputStream());
            saida.writeInt(metodo);
            saida.flush();
            saida.writeObject(u); //envia usuario que vai sair da sala
            saida.flush();
            c.close();
        } catch (Exception e) {
            System.out.println("O seguinte problema ocorreu : \n" + e.toString());
        }
    }

    @Override
    public void criarSala(Sala s) {
        try {
            metodo = 4;
            Socket c = new Socket(IPServidor, PortaServidor);
            System.out.println("estabelece conexao com servidor");
            ObjectOutputStream saida = new ObjectOutputStream(c.getOutputStream());
            saida.writeInt(metodo);
            saida.flush();
            saida.writeObject(s); //envia sala
            saida.flush();
            c.close();
        } catch (Exception e) {
            System.out.println("O seguinte problema ocorreu : \n" + e.toString());
        }
    }

    @Override
    public void entrarSala(Sala s, Usuario u) {
        try {
            metodo = 5;
            Socket c = new Socket(IPServidor, PortaServidor);
            System.out.println("estabelece conexao com servidor");
            ObjectOutputStream saida = new ObjectOutputStream(c.getOutputStream());
            saida.writeInt(metodo);
            saida.flush();
            saida.writeObject(u); //envia sala
            saida.flush();
            saida.writeObject(s); //envia sala
            saida.flush();
            c.close();
        } catch (Exception e) {
            System.out.println("O seguinte problema ocorreu : \n" + e.toString());
        }
    }

}
