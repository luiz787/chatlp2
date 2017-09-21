/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceimpl;


import DAO.UsuarioDAO;
import domain.Sala;
import domain.Usuario;
import exception.BusinessException;
import exception.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import service.ManterUsuario;

/**
 *
 * @author Luiz
 */
public class ManterUsuarioImpl implements ManterUsuario {
    private final UsuarioDAO usuarioDAO;
    
    public ManterUsuarioImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Usuario criarUsuario(Usuario u) throws BusinessException, PersistenceException {
        if (u==null){
            throw new BusinessException("Nenhum usuário informado");
        }
        if (u.getNome().isEmpty()){
            throw new BusinessException("O usuário deve possuir um nome.");
        }
        return usuarioDAO.criarUsuario(u);
    }

    @Override
    public Usuario alterarUsuario(Usuario u) throws BusinessException, PersistenceException {
        if (u==null){
            throw new BusinessException("Nenhum usuário informado");
        }
        if (u.getNome().isEmpty()){
            throw new BusinessException("O usuário deve possuir um nome.");
        }
        return usuarioDAO.alterarUsuario(u);
    }

    @Override
    public Usuario getUsuarioByNome(String nome) throws BusinessException, PersistenceException {
        if (nome.isEmpty()){
            throw new BusinessException("Nenhum nome informado.");
        }
        return usuarioDAO.getUsuarioByNome(nome);
    }

    @Override
    public ArrayList<Usuario> getAllByRoom(Sala s) throws BusinessException, PersistenceException {
        if (s==null){
            throw new BusinessException("Nenhuma sala informada.");
        }
        if (s.getNome().isEmpty()){
            throw new BusinessException("A sala deve possuir um nome.");
        }
        return usuarioDAO.getAllByRoom(s);
    }
    
}
