/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import DAO.UsuarioDAO;
import domain.Usuario;
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
public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario criarUsuario(Usuario usu) throws PersistenceException {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "insert into Usuario (nom_usuario,nom_sala) values(?,?) ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usu.getNome());
            if (usu.getSala() != null) {
                pstmt.setString(2, usu.getSala().getNome());
            } else {
                pstmt.setNull(2, java.sql.Types.NULL);
            }

            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
            return usu;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MensagemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Usuario alterarUsuario(Usuario usu) throws PersistenceException {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "UPDATE Usuario SET nom_sala=? WHERE nom_usuario=? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usu.getSala().getNome());
            pstmt.setString(2, usu.getNome());

            int rs = pstmt.executeUpdate();

            SalaDAOImpl salaDAOImpl = new SalaDAOImpl();
            pstmt.close();
            connection.close();
            return usu;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MensagemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public Usuario getUsuarioByNome(String nome) throws PersistenceException {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT * FROM Usuario WHERE nom_usuario=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            SalaDAOImpl salaDAOImpl = new SalaDAOImpl();
            Usuario u = new Usuario();
            if (rs.next()) {
                u.setNome(rs.getString("nom_usuario"));
                u.setSala(salaDAOImpl.getSalaByNome(rs.getString("nom_sala")));
            }
            rs.close();
            pstmt.close();
            connection.close();
            return u;
        } catch (SQLException | ClassNotFoundException | PersistenceException ex) {
            Logger.getLogger(MensagemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Usuario> getAllByRoomName(String nomeSala) throws PersistenceException {
        try {
            Connection connection = JDBCManterConexao.getInstancia().getConexao();
            String sql = "SELECT DISTINCT * FROM Usuario WHERE nom_sala = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nomeSala);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Usuario> listAll = new ArrayList<>();
            SalaDAOImpl salaDAOImpl = new SalaDAOImpl();
            if (rs.next()) {
                do {
                    Usuario u = new Usuario();
                    u.setNome(rs.getString("nom_usuario"));
                    u.setSala(salaDAOImpl.getSalaByNome(rs.getString("nom_sala")));
                    listAll.add(u);
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
