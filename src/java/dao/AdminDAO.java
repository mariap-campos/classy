/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.AdminDAO.UPDATE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Admin;
import modelo.Admin;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class AdminDAO {
    
    public static final String INSERT = "INSERT INTO admin (nome, usuario, senha, email,imagem) values (?,?,?,?,?)";
    public static final String SELECT_ID = "SELECT * from admin where id=?";
    public static final String SELECT_USER = "SELECT * from admin where usuario=?";
    public static final String UPDATE_PASSWORD = "UPDATE admin set senha=? where usuario=?";
    public static final String UPDATE = "UPDATE admin SET nome = ?, usuario = ?, imagem = ?, email = ? WHERE id=?";
    
    public void cadastrar(Admin admin) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getUsuario());
            pstmt.setString(3, admin.getSenha());
            pstmt.setString(4, admin.getEmail());
            pstmt.setString(5, admin.getImagem());
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
    
    public boolean getUsuario(Admin admin) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_USER);
            pstmt.setString(1, admin.getUsuario());
            ResultSet resultado = pstmt.executeQuery();
            
            if (resultado.next() == false) { 
                return false;
            } else { return true;}
                
            
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
            PreparedStatement pstmt = conexao.prepareStatement("SELECT id, usuario, senha, nome, email, imagem FROM admin where usuario='" + admin.getUsuario() + "' and senha='" + admin.getSenha() + "'");
            ResultSet resultado = pstmt.executeQuery();
            Admin adminLogado = new Admin();
            while (resultado.next()){
                adminLogado.setId(resultado.getInt("id"));  
                adminLogado.setUsuario(resultado.getString("usuario")); 
                adminLogado.setSenha(resultado.getString("senha")); 
                adminLogado.setNome(resultado.getString("nome")); 
                adminLogado.setEmail(resultado.getString("email")); 
                adminLogado.setImagem(resultado.getString("imagem")); 
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
                adminResult.setUsuario(resultado.getString("usuario"));
                adminResult.setEmail(resultado.getString("email"));
                adminResult.setImagem(resultado.getString("imagem"));

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
    
    public void updatePassword(Admin admin) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_PASSWORD);
            pstmt.setString(1, admin.getSenha());
            pstmt.setString(2, admin.getUsuario());

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
    
    public void atualizar(Admin admin) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getUsuario());
            pstmt.setString(3, admin.getImagem());
            pstmt.setString(4, admin.getEmail());
            pstmt.setInt(5, admin.getId());

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
