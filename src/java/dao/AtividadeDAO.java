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
    public static final String SELECT = "SELECT * from atividade where token_classy=?";
    
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
                p.setData_entrega(resultado.getDate("data_entrega"));
                p.setMateria(resultado.getString("materia"));
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
}
