/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import DAO.UsuarioDAO;
import domain.Sala;
import domain.Usuario;
import exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario criarUsuario(Usuario u) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario alterarUsuario(Usuario u) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario getUsuarioByNome(String nome) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Usuario> getAllByRoomName(String nomeSala) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
