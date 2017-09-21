/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.Mensagem;
import domain.Sala;
import exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public interface MensagemDAO {
    public Mensagem getMensagemById(Long id) throws PersistenceException;
    public Long createMensagem(Mensagem m) throws PersistenceException;
    public ArrayList<Mensagem> getAllBySala(Sala s) throws PersistenceException;
}
