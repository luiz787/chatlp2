/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.Sala;
import exception.PersistenceException;
import java.util.ArrayList;

/**
 *
 * @author Luiz
 */
public interface SalaDAO {
    public Sala getSalaByNome(String nome) throws PersistenceException;
    public boolean createSala(Sala s) throws PersistenceException;
    public ArrayList<Sala> getAll() throws PersistenceException;
}
