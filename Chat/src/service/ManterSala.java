/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Sala;
import exception.BusinessException;
import exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public interface ManterSala {
    public Sala getSalaByNome(String nome) throws BusinessException, PersistenceException;
    public Long createSala(Sala s) throws BusinessException, PersistenceException;
    public ArrayList<Sala> getAll() throws PersistenceException;
}
