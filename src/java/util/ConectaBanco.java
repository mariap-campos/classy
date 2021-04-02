/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Maria Paula
 */
public class ConectaBanco {
    public static Connection getConexao() {
    Connection conexao = null;
    try {
        Class.forName("org.postgresql.Driver");
        conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/classy", "postgres", "Ma160600");
    } catch (ClassNotFoundException ex) {
        throw new RuntimeException(ex);
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
    return conexao;
        
    } 
}
