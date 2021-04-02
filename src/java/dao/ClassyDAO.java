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
import modelo.Classy;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class ClassyDAO {
    
    public static final String INSERT = "INSERT INTO classy (nome, nome_instituicao, data_inicio, data_final, materias, id_admin) values (?,?,?,?,?,?)";
    public static final String SELECT = "SELECT * from classy where id_admin=?";
    public static final String SELECT_ID = "SELECT * from classy where token=?";
    public static final String UPDATE = "UPDATE classy SET nome = ?, nome_instituicao = ?, materias = ?, data_inicio = ?, data_final = ? WHERE token=?";

    public void cadastrar(Classy classy) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, classy.getNome());
            pstmt.setString(2, classy.getNome_instituicao());
            pstmt.setDate(3, classy.getData_inicio());
            pstmt.setDate(4, classy.getData_final());
            pstmt.setString(5, classy.getMaterias());
            pstmt.setInt(6, classy.getId_admin());

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
    
    public ArrayList<Classy> consultarTodos(Classy classy) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT);
            comando.setInt(1, classy.getId_admin());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Classy> todosClassy = new ArrayList<Classy>();
            while (resultado.next()){
                Classy p = new Classy();
                p.setToken(resultado.getInt("token"));
                p.setNome(resultado.getString("nome"));
                p.setNome_instituicao(resultado.getString("nome_instituicao"));
                p.setData_inicio(resultado.getDate("data_inicio"));
                p.setData_final(resultado.getDate("data_final"));
                p.setMaterias(resultado.getString("materias"));
                p.setId_admin(resultado.getInt("id_admin"));
                todosClassy.add(p);     
            }
            
            return todosClassy;
          
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
    
    public Classy consultarPorId(Classy classy) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_ID);
            comando.setInt(1, classy.getToken());
            ResultSet resultado = comando.executeQuery();
            Classy classyResult = new Classy();
            while (resultado.next()){
                classyResult.setToken(resultado.getInt("token"));
                classyResult.setNome(resultado.getString("nome"));
                classyResult.setNome_instituicao(resultado.getString("nome_instituicao"));
                classyResult.setData_inicio(resultado.getDate("data_inicio"));  
                classyResult.setData_final(resultado.getDate("data_final")); 
                classyResult.setMaterias(resultado.getString("materias")); 
                classyResult.setId_admin(resultado.getInt("id_admin"));
            }  
            
            return classyResult;
          
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
    
    public void atualizar(Classy classy) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, classy.getNome());
            pstmt.setString(2, classy.getNome_instituicao());
            pstmt.setString(3, classy.getMaterias());
            pstmt.setDate(4, classy.getData_inicio());
            pstmt.setDate(5, classy.getData_final());
            pstmt.setInt(6, classy.getToken());

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
}
