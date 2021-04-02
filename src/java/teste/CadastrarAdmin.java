/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import dao.AdminDAO;
import modelo.Admin;

/**
 *
 * @author Maria Paula
 */
public class CadastrarAdmin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Admin cliente = new Admin();
        
        cliente.setNome("Maria");
        cliente.setSenha("123");
        cliente.setUsuario("123");
        cliente.setEmail("123");
        
        
        
        //cria cliente dao
        AdminDAO clienteDAO = new AdminDAO();
        clienteDAO.cadastrar(cliente);
        System.out.println("Cadastrado com sucesso!");
    }
    
}
