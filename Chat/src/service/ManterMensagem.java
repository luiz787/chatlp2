/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Mensagem;
import domain.Sala;
import exception.BusinessException;
import exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public interface ManterMensagem {
    public Mensagem getMensagemById(Long id) throws BusinessException, PersistenceException;
    public Long createMensagem(Mensagem m) throws BusinessException, PersistenceException;
    public ArrayList<Mensagem> getAllBySala(Sala s) throws BusinessException, PersistenceException;
}
