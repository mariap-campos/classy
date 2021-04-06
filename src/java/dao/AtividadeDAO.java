/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Atividade;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class AtividadeDAO {
    public static final String INSERT = "INSERT INTO atividade (nome, descricao, professor, data_entrega, materia, token_classy) values (?,?,?,?,?,?)";
     
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
}
