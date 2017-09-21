/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Sala;
import domain.Usuario;
import exception.BusinessException;
import exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public interface ManterUsuario {
    public Usuario criarUsuario(Usuario u) throws BusinessException, PersistenceException;
    public Usuario alterarUsuario(Usuario u) throws BusinessException, PersistenceException;
    public Usuario getUsuarioByNome(String nome) throws BusinessException, PersistenceException;
    public ArrayList<Usuario> getAllByRoom(Sala s) throws BusinessException, PersistenceException;
}
