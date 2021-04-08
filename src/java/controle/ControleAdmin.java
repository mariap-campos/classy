/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Admin;
import modelo.Classy;
import static javax.swing.JOptionPane.showMessageDialog;
import util.ConverteDate;

/**
 *
 * @author Maria Paula
 */
@WebServlet(name = "ControleAdmin", urlPatterns = {"/ControleAdmin"})
public class ControleAdmin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String acao = request.getParameter("acao");
            if ("Cadastrar".equals(acao)) {
                modelo.Admin admin = new modelo.Admin();
                admin.setUsuario(request.getParameter("campoUsuario"));
                admin.setNome(request.getParameter("campoNome"));
                admin.setSenha(request.getParameter("campoSenha"));
                admin.setEmail(request.getParameter("campoEmail"));
                
                AdminDAO adao = new AdminDAO();
                adao.cadastrar(admin);
                Admin adminId = new Admin();
                adminId = adao.getId(admin);
                
                modelo.Classy classy = new modelo.Classy();
                classy.setNome(request.getParameter("campoClassy"));
                classy.setNome_instituicao(request.getParameter("campoInsti"));
                classy.setId_admin(adminId.getId());
                
                ConverteDate conversor = new ConverteDate();
                Date data1 = conversor.getDate(request.getParameter("campoInicio"));
                Date data2 = conversor.getDate(request.getParameter("campoFinal"));
               
                classy.setData_inicio(data1);
                classy.setData_final(data2);
                
                classy.setMaterias(request.getParameter("campoMaterias"));
                
                dao.ClassyDAO cdao = new dao.ClassyDAO();
                cdao.cadastrar(classy);
                System.out.println("Admin cadastrado com Sucesso!");
                
                request.setAttribute("title", "Seu cadastro foi realizado com sucesso!");
                request.setAttribute("mensagem", "Voltando a página inicial para que você possa efetuar o login!");
                request.setAttribute("tipo", "Cadastrar");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            }  else if ("Entrar".equals(acao)) {
                modelo.Admin admin = new modelo.Admin();
                admin.setUsuario(request.getParameter("campoUsuario"));
                admin.setSenha(request.getParameter("campoSenha"));
                
                AdminDAO adao = new AdminDAO(); 
                Admin adminLogado = adao.efetuarLogin(admin);     
                
                if (admin.getUsuario().equals(adminLogado.getUsuario()) && admin.getSenha().equals(adminLogado.getSenha())) {
                    
                    Admin adminId = new Admin();
                    adminId = adao.getId(adminLogado);
                    
                    Classy classy = new Classy();
                    classy.setId_admin(adminId.getId());
                
                    dao.ClassyDAO cdao = new dao.ClassyDAO();
                    ArrayList<Classy> todosClassys = new ArrayList<Classy>();
                    todosClassys = cdao.consultarTodos(classy);
                    
                    request.setAttribute("classys", todosClassys);
                    request.setAttribute("admin", adminLogado);
                    request.getRequestDispatcher("listarClassy.jsp").forward(request, response);
                } else {
                    request.setAttribute("title", "Usuário não existe ou está incorreto!");
                    request.setAttribute("mensagem", "Por favor, tente novamente.");
                    request.setAttribute("tipo", "Login");
                    request.getRequestDispatcher("error.jsp").forward(request, response);;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("erro", e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
