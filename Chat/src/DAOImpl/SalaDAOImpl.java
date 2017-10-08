/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import DAO.SalaDAO;
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
public class SalaDAOImpl implements SalaDAO {

    @Override
    public Sala getSalaByNome(String nome) throws PersistenceException {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT * FROM sala WHERE nom_sala=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            Sala s = null;
            UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
            //SalaDAOImpl salaDAOImpl = new SalaDAOImpl();
            if (rs.next()) {
                s = new Sala();
                s.setNome(rs.getString("nom_sala"));
                s.setUsuarios(usuarioDAOImpl.getAllByRoomName(nome));   
            }
            rs.close();
            pstmt.close();
            connection.close();
            return s;
        } catch (SQLException | ClassNotFoundException | PersistenceException ex) {
            Logger.getLogger(SalaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public boolean createSala(Sala s) throws PersistenceException {
        try {
            if (s == null) {
                throw new PersistenceException("Sala não pode ser nula.");
            }
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "INSERT INTO sala ("
                    + "nom_sala)"
                    + "VALUES (?)";
            // como guardar os usuários no banco de dados?
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, s.getNome());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SalaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Sala> getAll() throws PersistenceException {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT * FROM sala ORDER BY nom_sala;";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Sala> listAll = new ArrayList<>();
            if (rs.next()) {
                do {
                    Sala s = new Sala();
                    s.setNome(rs.getString("nom_sala"));
                    listAll.add(s);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SalaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException(ex.getMessage());
        }
    }
    
}
