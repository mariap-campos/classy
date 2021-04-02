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
public class AdminLogin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Admin cliente = new Admin();
        
        cliente.setUsuario("mashpaula");
        cliente.setSenha("123");
        
        
        
        //cria cliente dao
        AdminDAO clienteDAO = new AdminDAO();
        Admin adminLogado = clienteDAO.efetuarLogin(cliente);
        
        if (cliente.getUsuario() == adminLogado.getUsuario() && cliente.getSenha() == adminLogado.getSenha()) {
                    System.out.println("Login Efetuado!");
                } else {
                    System.out.println("Usuário Incorreto ou não existe!");
                }
    }
    
}
