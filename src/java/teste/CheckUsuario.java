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
public class CheckUsuario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Admin admin = new Admin();
        
        admin.setUsuario("asa121");
        
        AdminDAO adao = new AdminDAO();
        System.out.println(adao.getUsuario(admin));

    }
    
}
