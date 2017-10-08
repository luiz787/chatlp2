/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceimpl;

import DAO.SalaDAO;
import domain.Sala;
import exception.BusinessException;
import exception.PersistenceException;
import java.util.ArrayList;
import service.ManterSala;

/**
 *
 * @author Luiz
 */
public class ManterSalaImpl implements ManterSala {
    private final SalaDAO salaDAO;
    
    public ManterSalaImpl(SalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }

    @Override
    public Sala getSalaByNome(String nome) throws BusinessException, PersistenceException {
        if (nome.isEmpty()){
            throw new BusinessException("Nenhum nome de sala informado.");
        }
        return salaDAO.getSalaByNome(nome);
    }

    @Override
    public boolean createSala(Sala s) throws BusinessException, PersistenceException {
        if (s==null){
            throw new BusinessException("Nenhuma sala informada.");
        }
        if (s.getNome().isEmpty()){
            throw new BusinessException("A sala deve ter um nome.");
        }
        return salaDAO.createSala(s);
    }

    @Override
    public ArrayList<Sala> getAll() throws PersistenceException {
        return salaDAO.getAll();
    }
    
}
