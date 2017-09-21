/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import DAO.MensagemDAO;
import domain.Mensagem;
import domain.Sala;
import exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public class MensagemDAOImpl implements MensagemDAO {

    @Override
    public Mensagem getMensagemById(Long id) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long createMensagem(Mensagem m) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Mensagem> getAllBySala(Sala s) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
