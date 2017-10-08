/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Luiz
 */
public class Server {
    public static void main(String[] args){
        try {
            ServerSocket s = new ServerSocket(2230);
            while (true){
                System.out.println("aguardando conexao...");
                Socket p = s.accept();
                Adapter m = new Adapter(p);
                new Thread(m).start();
            }
        } catch (Exception e) {
            System.out.println("O seguinte problema ocorreu:\n"+e.toString());
        }
    }
}
