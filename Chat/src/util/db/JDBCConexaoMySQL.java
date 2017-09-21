/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Luiz
 */
public class JDBCConexaoMySQL implements JDBCFabricaConexao {
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/chat";
    private final static String USUARIO = "root";
    private final static String SENHA = "";

    public JDBCConexaoMySQL() {
    }

    @Override
    public Connection getConexao() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
