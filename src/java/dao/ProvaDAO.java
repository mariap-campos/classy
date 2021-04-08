/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Prova;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class ProvaDAO {
    public static final String INSERT = "INSERT INTO prova (nome, materia, data, token_classy) values (?,?,?,?)";
    public static final String SELECT = "SELECT * from prova where token_classy=? order by data asc";
    
    public void cadastrar(Prova prova) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, prova.getNome());
            pstmt.setString(2, prova.getMateria());
            pstmt.setDate(3, prova.getData());
            pstmt.setInt(4, prova.getToken_classy());

            pstmt.execute();   
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            try {
                conexao.close();
            } catch (SQLException el) {
                throw new RuntimeException(el);
            }
        }
    } 
    
        public ArrayList<Prova> consultarTodos(Prova prova) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT);
            comando.setInt(1, prova.getToken_classy());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Prova> todosProva = new ArrayList<Prova>();
            while (resultado.next()){
                Prova p = new Prova();
                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setMateria(resultado.getString("materia"));
                p.setData(resultado.getDate("data"));
                p.setToken_classy(resultado.getInt("token_classy"));
                todosProva.add(p);     
            }
            
            return todosProva;
          
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            try {
                conexao.close();
            } catch (SQLException el) {
                throw new RuntimeException(el);
            }
        }
      
    }
}
