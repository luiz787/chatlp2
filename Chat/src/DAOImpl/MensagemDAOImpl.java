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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.db.JDBCManterConexao;

/**
 *
 * @author Luiz
 */
public class MensagemDAOImpl implements MensagemDAO {

    @Override
    public Mensagem getMensagemById(Long id) throws PersistenceException {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT * FROM Mensagem WHERE cod_mensagem=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            Mensagem msg = null;
            UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
            SalaDAOImpl salaDAOImpl = new SalaDAOImpl();
            if (rs.next()) {
                msg = new Mensagem();
                msg.setId(rs.getLong("cod_mensagem"));
                msg.setAutor(usuarioDAOImpl.getUsuarioByNome(rs.getString("nom_autor")));
                msg.setSala(salaDAOImpl.getSalaByNome(rs.getString("nom_sala")));
                msg.setConteudo(rs.getString("des_conteudo"));        
            }
            rs.close();
            pstmt.close();
            connection.close();
            return msg;
        } catch (SQLException | ClassNotFoundException | PersistenceException ex) {
            Logger.getLogger(MensagemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Long createMensagem(Mensagem m) throws PersistenceException {
        try {
            if (m == null) {
                throw new PersistenceException("Mensagem não pode ser nula.");
            }
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "INSERT INTO `Mensagem` ("
                    + "`nom_autor`,"
                    + " `nom_sala`,"
                    + " `des_conteudo`,"
                    + "VALUES (?, ?, ?)";
                    
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, m.getAutor().getNome());
            pstmt.setString(2, m.getSala().getNome());
            pstmt.setString(3, m.getConteudo());
            System.out.println(sql); 
            pstmt.executeUpdate();
            ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID() FROM Mensagem");
            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
                m.setId(id);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return id;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MensagemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Mensagem> getAllBySala(Sala s) throws PersistenceException {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT * FROM Mensagem WHERE nom_sala = ? ORDER BY cod_mensagem;";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, s.getNome());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Mensagem> listAll = new ArrayList<>();
            UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
            SalaDAOImpl salaDAOImpl = new SalaDAOImpl();
            if (rs.next()) {
                do {
                    Mensagem m = new Mensagem();
                    m.setId(rs.getLong("cod_mensagem"));
                    m.setAutor(usuarioDAOImpl.getUsuarioByNome(rs.getString("nom_usuario")));
                    m.setSala(salaDAOImpl.getSalaByNome(rs.getString("nom_sala")));
                    m.setConteudo(rs.getString("des_conteudo"));
                    listAll.add(m);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            connection.close();
            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MensagemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException(ex.getMessage());
        }
    }
    
}
