/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.AtividadeDAO.SELECT_ID;
import static dao.AtividadeDAO.UPDATE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Aluno;
import modelo.Atividade;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class AlunoDAO {
    public static final String INSERT = "insert into aluno (nome, rgm, situacao, classy_token) values (?,?,?,?);";
    public static final String SELECT = "SELECT * from aluno where classy_token=?";
    public static final String SELECT_ID = "SELECT * from aluno where id=?";
    public static final String DELETE = "DELETE FROM aluno WHERE id=?";
    public static final String UPDATE = "UPDATE aluno SET nome = ?, rgm = ?, situacao = ? WHERE id=?";
    
    public void cadastrar(Aluno aluno) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getRgm());
            pstmt.setString(3, "Regular");
            pstmt.setInt(4, aluno.getClassy_token());

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
    
    public ArrayList<Aluno> consultarTodos(Aluno aluno) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT);
            comando.setInt(1, aluno.getClassy_token());
            ResultSet resultado = comando.executeQuery();
            
            ArrayList<Aluno> todosAluno = new ArrayList<Aluno>();
            while (resultado.next()){
                Aluno p = new Aluno();
                p.setId(resultado.getInt("id"));
                p.setNome(resultado.getString("nome"));
                p.setRgm(resultado.getString("rgm"));
                p.setSituacao(resultado.getString("situacao"));
                p.setClassy_token(resultado.getInt("classy_token"));
                todosAluno.add(p);     
            }
            
            return todosAluno;
          
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
        
    public void apagar(Aluno aluno) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setInt(1, aluno.getId());
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
    
    public Aluno consultarPorId(Aluno aluno) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement comando = conexao.prepareStatement(SELECT_ID);
            comando.setInt(1, aluno.getId());
            ResultSet resultado = comando.executeQuery();
            Aluno alunoResult = new Aluno();
            while (resultado.next()){
                alunoResult.setId(resultado.getInt("id"));
                alunoResult.setNome(resultado.getString("nome"));
                alunoResult.setRgm(resultado.getString("rgm")); 
                alunoResult.setSituacao(resultado.getString("situacao")); 
                alunoResult.setClassy_token(resultado.getInt("classy_token")); 
            }  
            
            return alunoResult;
          
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
    
    public void atualizar(Aluno aluno) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getRgm());
            pstmt.setString(3, aluno.getSituacao());
            pstmt.setInt(4, aluno.getId());

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
