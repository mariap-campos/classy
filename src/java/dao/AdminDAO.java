/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ClassyDAO.SELECT_ID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Admin;
import modelo.Classy;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class AdminDAO {
    
    public static final String INSERT = "INSERT INTO admin (nome, usuario, senha, email) values (?,?,?,?)";
    public static final String SELECT_ID = "SELECT * from admin where id=?";
    
    public void cadastrar(Admin admin) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getUsuario());
            pstmt.setString(3, admin.getSenha());
            pstmt.setString(4, admin.getEmail());
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
    
    public Admin getId(Admin admin) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement("SELECT * FROM admin WHERE usuario='" + admin.getUsuario() + "';");
            ResultSet resultado = pstmt.executeQuery();
            Admin adminLogado = new Admin();
            while (resultado.next()){
                adminLogado.setId(resultado.getInt("id"));  
            }  
            
            return adminLogado;
            
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
    
    public Admin efetuarLogin(Admin admin) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement("SELECT id, usuario, senha, nome, email FROM admin where usuario='" + admin.getUsuario() + "' and senha='" + admin.getSenha() + "'");
            ResultSet resultado = pstmt.executeQuery();
            Admin adminLogado = new Admin();
            while (resultado.next()){
                adminLogado.setId(resultado.getInt("id"));  
                adminLogado.setUsuario(resultado.getString("usuario")); 
                adminLogado.setSenha(resultado.getString("senha")); 
                adminLogado.setNome(resultado.getString("nome")); 
                adminLogado.setEmail(resultado.getString("email")); 
            }  
            
            return adminLogado;
            
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
    
    public Admin consultarPorId(Admin admin) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_ID);
            comando.setInt(1, admin.getId());
            ResultSet resultado = comando.executeQuery();
            Admin adminResult = new Admin();
            while (resultado.next()){
                adminResult.setId(resultado.getInt("id"));
                adminResult.setNome(resultado.getString("nome"));
                adminResult.setEmail(resultado.getString("email"));

            }  
            
            return adminResult;
          
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
