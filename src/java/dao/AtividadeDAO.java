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
import modelo.Atividade;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class AtividadeDAO {
    public static final String INSERT = "INSERT INTO atividade (nome, descricao, professor, data_entrega, materia, token_classy) values (?,?,?,?,?,?)";
    public static final String SELECT = "SELECT * from atividade where token_classy=? order by data_entrega asc";
    public static final String SELECT_LIMIT = "SELECT * from atividade where token_classy=? order by data_entrega asc limit 2";
    public static final String SELECT_ID = "SELECT * from atividade where id=?";
    public static final String SELECT_10 = "SELECT * from atividade where data_entrega between (CURRENT_DATE) and (CURRENT_DATE + 10) and token_classy=? order by data_entrega asc";
    public static final String SELECT_At = "SELECT * from atividade where data_entrega < current_Date and token_classy=? order by data_entrega desc";
    public static final String SELECT_Sub = "SELECT * from atividade where materia = ? and token_classy=? order by data_entrega desc";
    public static final String UPDATE = "UPDATE atividade SET nome = ?, descricao = ?, professor = ?, data_entrega = ?, materia = ? WHERE id=?";
    public static final String DELETE = "DELETE FROM atividade WHERE id=?";
    
    public void cadastrar(Atividade ativ) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, ativ.getNome());
            pstmt.setString(2, ativ.getDescricao());
            pstmt.setString(3, ativ.getProfessor());
            pstmt.setDate(4, ativ.getData_entrega());
            pstmt.setString(5, ativ.getMateria());
            pstmt.setInt(6, ativ.getClassy_token());

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
    
    public ArrayList<Atividade> consultarTodos(Atividade ativ) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT);
            comando.setInt(1, ativ.getClassy_token());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Atividade> todosAtiv = new ArrayList<Atividade>();
            while (resultado.next()){
                Atividade p = new Atividade();
                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setDescricao(resultado.getString("descricao"));
                p.setProfessor(resultado.getString("professor"));
                p.setMateria(resultado.getString("materia"));
                p.setData_entrega(resultado.getDate("data_entrega"));
                p.setClassy_token(resultado.getInt("token_classy"));
                todosAtiv.add(p);     
            }
            
            return todosAtiv;
          
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
    
    public ArrayList<Atividade> consultarHome(Atividade ativ) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_LIMIT);
            comando.setInt(1, ativ.getClassy_token());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Atividade> todosAtiv = new ArrayList<Atividade>();
            while (resultado.next()){
                Atividade p = new Atividade();
                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setDescricao(resultado.getString("descricao"));
                p.setProfessor(resultado.getString("professor"));
                p.setMateria(resultado.getString("materia"));
                p.setData_entrega(resultado.getDate("data_entrega"));
                p.setClassy_token(resultado.getInt("token_classy"));
                todosAtiv.add(p);     
            }
            
            return todosAtiv;
          
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
    
    public ArrayList<Atividade> consultar10(Atividade ativ) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_10);
            comando.setInt(1, ativ.getClassy_token());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Atividade> todosAtiv = new ArrayList<Atividade>();
            while (resultado.next()){
                Atividade p = new Atividade();
                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setDescricao(resultado.getString("descricao"));
                p.setProfessor(resultado.getString("professor"));
                p.setMateria(resultado.getString("materia"));
                p.setData_entrega(resultado.getDate("data_entrega"));
                p.setClassy_token(resultado.getInt("token_classy"));
                todosAtiv.add(p);     
            }
            
            return todosAtiv;
          
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
    
        public ArrayList<Atividade> consultarAtrasadas(Atividade ativ) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_At);
            comando.setInt(1, ativ.getClassy_token());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Atividade> todosAtiv = new ArrayList<Atividade>();
            while (resultado.next()){
                Atividade p = new Atividade();
                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setDescricao(resultado.getString("descricao"));
                p.setProfessor(resultado.getString("professor"));
                p.setMateria(resultado.getString("materia"));
                p.setData_entrega(resultado.getDate("data_entrega"));
                p.setClassy_token(resultado.getInt("token_classy"));
                todosAtiv.add(p);     
            }
            
            return todosAtiv;
          
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
        
    public ArrayList<Atividade> consultarPorMateria(Atividade ativ) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_Sub);
            comando.setString(1, ativ.getMateria());
            comando.setInt(2, ativ.getClassy_token());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Atividade> todosAtiv = new ArrayList<Atividade>();
            while (resultado.next()){
                Atividade p = new Atividade();
                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setDescricao(resultado.getString("descricao"));
                p.setProfessor(resultado.getString("professor"));
                p.setMateria(resultado.getString("materia"));
                p.setData_entrega(resultado.getDate("data_entrega"));
                p.setClassy_token(resultado.getInt("token_classy"));
                todosAtiv.add(p);     
            }
            
            return todosAtiv;
          
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
    
    
    
    public Atividade consultarPorId(Atividade ativ) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_ID);
            comando.setInt(1, ativ.getId());
            ResultSet resultado = comando.executeQuery();
            Atividade ativResult = new Atividade();
            while (resultado.next()){
                ativResult.setId(resultado.getInt("id"));
                ativResult.setNome(resultado.getString("nome"));
                ativResult.setDescricao(resultado.getString("descricao"));
                ativResult.setProfessor(resultado.getString("professor")); 
                ativResult.setData_entrega(resultado.getDate("data_entrega")); 
                ativResult.setMateria(resultado.getString("materia")); 
                ativResult.setClassy_token(resultado.getInt("token_classy")); 
            }  
            
            return ativResult;
          
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
       
    
    public void atualizar(Atividade ativ) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, ativ.getNome());
            pstmt.setString(2, ativ.getDescricao());
            pstmt.setString(3, ativ.getProfessor());
            pstmt.setDate(4, ativ.getData_entrega());
            pstmt.setString(5, ativ.getMateria());
            pstmt.setInt(6, ativ.getId());

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
    
    public void apagar(Atividade atividade) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setInt(1, atividade.getId());
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
