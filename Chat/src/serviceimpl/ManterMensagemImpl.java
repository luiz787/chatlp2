/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceimpl;

import DAO.MensagemDAO;
import domain.Mensagem;
import domain.Sala;
import exception.BusinessException;
import exception.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import service.ManterMensagem;

/**
 *
 * @author Luiz
 */
public class ManterMensagemImpl implements ManterMensagem {
    private final MensagemDAO mensagemDAO;
    
    public ManterMensagemImpl(MensagemDAO mensagemDAO) {
        this.mensagemDAO = mensagemDAO;
    }

    @Override
    public Mensagem getMensagemById(Long id) throws BusinessException, PersistenceException {
        if (id==null){
            throw new BusinessException("Id não pode ser nulo.");
        }
        return mensagemDAO.getMensagemById(id);
    }

    @Override
    public Long createMensagem(Mensagem m) throws BusinessException, PersistenceException {
        List<String> errMsgList = new ArrayList<>();
        if (m == null) {
            throw new BusinessException("Nenhuma mensagem informada.");
        }
        if ((m.getConteudo()== null) || (m.getConteudo().isEmpty())) {
            errMsgList.add("O conteúdo da mensagem nao pode ser nulo.");
        }
        if (m.getAutor()== null) {
            errMsgList.add("A mensagem deve possuir um autor.");
        }
        if (m.getSala()==null) {
            errMsgList.add("A mensagem deve ter uma sala destino.");
        }
        if (!errMsgList.isEmpty()) {
            String errMsg = "";
            for (String msg : errMsgList) {
                errMsg += (msg + "\n");
            }
            throw new BusinessException(errMsg);
        }
        return mensagemDAO.createMensagem(m);
    }

    @Override
    public ArrayList<Mensagem> getAllBySala(Sala s) throws BusinessException, PersistenceException {
        if (s == null){
            throw new BusinessException("nenhuma sala informada.");
        }
        return mensagemDAO.getAllBySala(s);
    }
    
}
