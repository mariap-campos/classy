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
import modelo.Forum;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class ForumDAO {
    public static final String INSERT = "insert into forum (postagem, assunto, admin_id, classy_token) values (?,?,?,?);";
    public static final String SELECT = "select f.id, f.postagem, f.assunto, f.data_postagem, f.admin_id, f.classy_token, a.nome from forum as f join admin as a on f.admin_id = a.id where classy_token =? order by data_postagem asc";
    public static final String DELETE = "DELETE FROM forum WHERE id=?";
    
    public void postar(Forum forum) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, forum.getPostagem());
            pstmt.setString(2, forum.getAssunto());
            pstmt.setInt(3, forum.getAdmin_id());
            pstmt.setInt(4, forum.getClassy_token());

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
    
    public ArrayList<Forum> consultarTodos(Forum forum) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT);
            comando.setInt(1, forum.getClassy_token());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Forum> todosForum = new ArrayList<Forum>();
            while (resultado.next()){
                Forum p = new Forum();
                p.setId(resultado.getInt("id"));
                p.setPostagem(resultado.getString("postagem"));
                p.setAssunto(resultado.getString("assunto"));
                p.setData_postagem(resultado.getDate("data_postagem"));
                p.setAdmin_id(resultado.getInt("admin_id"));
                p.setClassy_token(resultado.getInt("classy_token"));
                p.setNome(resultado.getString("nome"));
                todosForum.add(p);     
            }
            
            return todosForum;
          
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
    
    public void apagar(Forum forum) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setInt(1, forum.getId());
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
