package br.com.senac.trabalho.java.fx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDataBase {
    private Connection conn = null;

    public synchronized Connection getConexao() throws ClassNotFoundException, SQLException {
        if(conn == null){
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/trabalho", "postgres", "postgres");
        }

        return conn;
    }
}
