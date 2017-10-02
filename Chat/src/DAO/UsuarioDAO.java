/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.Sala;
import domain.Usuario;
import exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public interface UsuarioDAO {
    public Usuario criarUsuario(Usuario u) throws PersistenceException;
    public Usuario alterarUsuario(Usuario u) throws PersistenceException;
    public Usuario getUsuarioByNome(String nome) throws PersistenceException;
    public ArrayList<Usuario> getAllByRoomName(String nomeSala) throws PersistenceException;
}
