/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Aluno;
import util.ConectaBanco;

/**
 *
 * @author Maria Paula
 */
public class AlunoDAO {
    public static final String INSERT = "insert into aluno (nome, rgm, situacao, classy_token) values (?,?,?,?);";
    
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
}
