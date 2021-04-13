/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ProvaDAO.SELECT_Sub;
import static dao.ProvaDAO.UPDATE;
import static dao.ProvaDAO.SELECT_ID;
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
    public static final String SELECT_LIMIT = "SELECT * from prova where token_classy=? order by data asc limit 2";
    public static final String DELETE = "DELETE FROM prova WHERE id=?";
    public static final String SELECT_ID = "SELECT * from prova where id=?";
    public static final String UPDATE = "UPDATE prova SET nome = ?, materia = ?, data = ? WHERE id=?";
    public static final String SELECT_Sub = "SELECT * from prova where materia = ? and token_classy=? order by data desc";
    
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
        
    public ArrayList<Prova> consultarHome(Prova prova) {
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
        
    public void apagar(Prova prova) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setInt(1, prova.getId());
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
    
    public Prova consultarPorId(Prova prova) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_ID);
            comando.setInt(1, prova.getId());
            ResultSet resultado = comando.executeQuery();
            Prova provaResult = new Prova();
            while (resultado.next()){
                provaResult.setId(resultado.getInt("id"));
                provaResult.setNome(resultado.getString("nome"));
                provaResult.setData(resultado.getDate("data")); 
                provaResult.setMateria(resultado.getString("materia")); 
                provaResult.setToken_classy(resultado.getInt("token_classy")); 
            }  
            
            return provaResult;
          
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
    
    public void atualizar(Prova prova) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, prova.getNome());
            pstmt.setString(2, prova.getMateria());
            pstmt.setDate(3, prova.getData());
            pstmt.setInt(4, prova.getId());

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
    
    public ArrayList<Prova> consultarPorMateria(Prova prova) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_Sub);
            comando.setString(1, prova.getMateria());
            comando.setInt(2, prova.getToken_classy());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Prova> todosprova = new ArrayList<Prova>();
            while (resultado.next()){
                Prova p = new Prova();
                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setMateria(resultado.getString("materia"));
                p.setData(resultado.getDate("data"));
                p.setToken_classy(resultado.getInt("token_classy"));
                todosprova.add(p);     
            }
            
            return todosprova;
          
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
