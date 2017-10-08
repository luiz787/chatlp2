/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import domain.Mensagem;
import domain.Sala;
import domain.Usuario;

/**
 *
 * @author F43L
 */
public interface proxy {
    public void enviarMensagem(Mensagem m);
    public void criarUsuario(Usuario u);
    public void sairSala(Usuario u);
    public void criarSala(Sala s);
    public void entrarSala(Sala s,Usuario u);
}
