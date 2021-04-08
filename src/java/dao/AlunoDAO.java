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
import modelo.Aluno;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class AlunoDAO {
    public static final String INSERT = "insert into aluno (nome, rgm, situacao, classy_token) values (?,?,?,?);";
    public static final String SELECT = "SELECT * from aluno where classy_token=?";
    public static final String DELETE = "DELETE FROM aluno WHERE id=?";
    
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
}
