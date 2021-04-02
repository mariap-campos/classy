/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import dao.AdminDAO;
import dao.ClassyDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import modelo.Classy;

/**
 *
 * @author Maria Paula
 */
public class CadastrarClassy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        Classy classy = new Classy();
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = df.parse("2021-04-20");
        java.sql.Date dataInicio = new java.sql.Date(date.getTime());
        
        classy.setNome("Maria");
        classy.setNome_instituicao("áLISé");
        classy.setData_inicio(dataInicio);
        classy.setData_final(dataInicio);
        classy.setMaterias("ééé");
        classy.setId_admin(4);
        
        
        //cria classy dao
        ClassyDAO classyDAO = new ClassyDAO();
        classyDAO.cadastrar(classy);
        System.out.println("Cadastrado com sucesso!");
    }
    
}
